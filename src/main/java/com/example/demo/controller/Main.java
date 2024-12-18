package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;

	@Override
	public void start(Stage stage) {
		configureStage(stage);
		myController = new Controller(stage);
		myController.launchGame();
	}

	private void configureStage(Stage stage) {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
	}

	public static void main(String[] args) {
		launch();
	}
}