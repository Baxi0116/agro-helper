package com.baxi.agrohelper;

import com.baxi.agrohelper.view.FirstPreloader;
import com.sun.javafx.application.LauncherImpl;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		LauncherImpl.launchApplication(MainApp.class, FirstPreloader.class, args);
	}
	
}
