package com.baxi.agrohelper.util;

import java.time.LocalDate;
import java.util.List;

import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFUtil {
	public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	public static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE);
	public static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.GREEN);
	public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public static Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	
	public static void addMetaData(Document document, String title) {
        document.addTitle(title);
        document.addAuthor(System.getProperty("user.name"));
        document.addCreator(System.getProperty("user.name"));
	}
	
	public static void addContent(Document document, Orchard orchard) 
			throws DocumentException {
		
		Paragraph statement = new Paragraph();
		statement.setAlignment(Element.ALIGN_LEFT);
		addEmptyLine(statement, 2);
		
		Paragraph paragraph = new Paragraph("Áttekintés:", subFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		statement.add(paragraph);
		addEmptyLine(statement, 1);
		
		paragraph = new Paragraph("Kert neve:\t" + orchard.getOrchardName());
		statement.add(paragraph);
		paragraph = new Paragraph("Helyrajzi szám:\t" + orchard.getTopographicNumber());
		statement.add(paragraph);
		paragraph = new Paragraph("MEPAR kód:\t" + orchard.getMeparCode());
		statement.add(paragraph);
		paragraph = new Paragraph("Telepítés éve:\t" + orchard.getYearOfPlantation());
		statement.add(paragraph);
		
		PdfPTable table = new PdfPTable(2);

        table.addCell("Összes haszon:");
        
		if(StatementUtil.countProfitForOrchard(orchard) > 0) {
			paragraph = new Paragraph(Double.toString(StatementUtil.countProfitForOrchard(orchard)) +" Ft", greenFont);
			table.addCell(paragraph);
		}
		if(StatementUtil.countProfitForOrchard(orchard) == 0) {
			paragraph = new Paragraph(Double.toString(StatementUtil.countProfitForOrchard(orchard)) + " Ft", blueFont);
			table.addCell(paragraph);
		}
		if(StatementUtil.countProfitForOrchard(orchard) < 0) {
			paragraph = new Paragraph(Double.toString(StatementUtil.countProfitForOrchard(orchard)) + " Ft", redFont);
			table.addCell(paragraph);
		}
		
        table.addCell("Összes bevétel:");
        table.addCell(Double.toString(StatementUtil.countIncomeForOrchard(orchard)) + " Ft");
        table.addCell("Összes kiadás:");
        table.addCell(Double.toString(StatementUtil.countExpensesForOrchard(orchard)) + " Ft");
        table.addCell("Összes gépköltség:");
        table.addCell(Double.toString(StatementUtil.countWorkExpensesForOrchard(orchard)) + " Ft");
        table.addCell("Összes anyagköltség:");
        table.addCell(Double.toString(StatementUtil.countMaterialExpensesForOrchard(orchard)) + " Ft");
        
        statement.add(table);
        
        addEmptyLine(statement, 1);
		paragraph = new Paragraph("Bevételek:", subFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		statement.add(paragraph);
		
		table = new PdfPTable(5);
		PdfPCell c1 = new PdfPCell(new Phrase("Fajta", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Össztermés(kg)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Egységár(Ft/kg)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Hozam(t/ha)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Bevétel(Ft)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        for(Variety variety : orchard.getVarieties()) {
        	
            c1 = new PdfPCell(new Phrase(variety.getVarietyName(), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(variety.getVarietyYield()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(variety.getVarietyPrice()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(variety.getTotalHarvest()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(variety.getTotalIncome()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
        }
        
        statement.add(table);
		
        addEmptyLine(statement, 1);
		paragraph = new Paragraph("Kiadások:", subFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		statement.add(paragraph);
		
		table = new PdfPTable(5);
		c1 = new PdfPCell(new Phrase("Munka megnevezése", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Dátum", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Gépköltség", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Anyagköltség", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Összköltség", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        for(AgWork work : orchard.getWorks()) {
            c1 = new PdfPCell(new Phrase(work.getWorkDesignation(), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(work.getWorkDate().toString(), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(work.getWorkPrice()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(work.getMaterialPrice()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        	
            c1 = new PdfPCell(new Phrase(Double.toString(work.getTotalPrice()), smallNormal));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	table.addCell(c1);
        }
		
        statement.add(table);
        
		document.add(statement);
		
	}
	
	public static void addTitle(Document document, Orchard orchard) 
			throws DocumentException{
		
		Paragraph preface = new Paragraph();
		preface.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(preface, 1);
		
		Paragraph paragraph = new Paragraph("Kimutatás a " + orchard.getOrchardName() + " kertre", catFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		preface.add(paragraph);
		
		paragraph = new Paragraph(LocalDate.now().toString(), smallBold);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		preface.add(paragraph);
		
		addEmptyLine(preface, 3);
		
		document.add(preface);
	}
	
	public static void addTitle(Document document) 
			throws DocumentException{
		
		Paragraph preface = new Paragraph();
		preface.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(preface, 1);
		
		Paragraph paragraph = new Paragraph("Kimutatás üzemi szinten", catFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		preface.add(paragraph);
		
		paragraph = new Paragraph(LocalDate.now().toString(), smallBold);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		preface.add(paragraph);
		
		addEmptyLine(preface, 3);
		
		document.add(preface);
	}
	
	public static void addContent(Document document, List<Orchard> orchardList) 
			throws DocumentException {
		
		Paragraph statement = new Paragraph();
		statement.setAlignment(Element.ALIGN_LEFT);
		addEmptyLine(statement, 2);
		
		Paragraph paragraph = new Paragraph("Áttekintés:", subFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		statement.add(paragraph);
		addEmptyLine(statement, 1);
		
		PdfPTable table = new PdfPTable(2);

        table.addCell("Összes haszon:");
        
        double profit = StatementUtil.countProfitForAllOrchard(orchardList);
        
		if(profit > 0) {
			paragraph = new Paragraph(Double.toString(profit) +" Ft", greenFont);
			table.addCell(paragraph);
		}
		if(profit == 0) {
			paragraph = new Paragraph(Double.toString(profit) + " Ft", blueFont);
			table.addCell(paragraph);
		}
		if(profit < 0) {
			paragraph = new Paragraph(Double.toString(profit) + " Ft", redFont);
			table.addCell(paragraph);
		}
		
        table.addCell("Összes bevétel:");
        table.addCell(Double.toString(StatementUtil.countIncomeForAllOrchard(orchardList)) + " Ft");
        table.addCell("Összes kiadás:");
        table.addCell(Double.toString(StatementUtil.countExpensesForAllOrchard(orchardList)) + " Ft");
        table.addCell("Összes gépköltség:");
        table.addCell(Double.toString(StatementUtil.countWorkExpensesForAllOrchard(orchardList)) + " Ft");
        table.addCell("Összes anyagköltség:");
        table.addCell(Double.toString(StatementUtil.countMaterialExpensesForAllOrchard(orchardList)) + " Ft");
        
        statement.add(table);
        
        addEmptyLine(statement, 1);
		paragraph = new Paragraph("Bevételek:", subFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		statement.add(paragraph);
		
		table = new PdfPTable(6);
		PdfPCell c1 = new PdfPCell(new Phrase("Kert", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Fajta", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Össztermés(kg)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Egységár(Ft/kg)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Hozam(t/ha)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Bevétel(Ft)", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        for(Orchard orchard : orchardList) {
        	for(Variety variety : orchard.getVarieties()) {
            	
                c1 = new PdfPCell(new Phrase(variety.getOrchard().toString(), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(variety.getVarietyName(), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(variety.getVarietyYield()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(variety.getVarietyPrice()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(variety.getTotalHarvest()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(variety.getTotalIncome()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
            }
        }
        
        
        
        statement.add(table);
		
        addEmptyLine(statement, 1);
		paragraph = new Paragraph("Kiadások:", subFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		statement.add(paragraph);
		
		table = new PdfPTable(6);
		c1 = new PdfPCell(new Phrase("Kert", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Munka megnevezése", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Dátum", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Gépköltség", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Anyagköltség", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Összköltség", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        for(Orchard orchard : orchardList) {
        	for(AgWork work : orchard.getWorks()) {
                c1 = new PdfPCell(new Phrase(work.getOrchard().toString(), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
        		
                c1 = new PdfPCell(new Phrase(work.getWorkDesignation(), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(work.getWorkDate().toString(), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(work.getWorkPrice()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(work.getMaterialPrice()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            	
                c1 = new PdfPCell(new Phrase(Double.toString(work.getTotalPrice()), smallNormal));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	table.addCell(c1);
            }
        }
        
        
		
        statement.add(table);
        
		document.add(statement);
		
	}
	
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
}
