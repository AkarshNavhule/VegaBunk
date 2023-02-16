package com;

import javafx.application.*;

import javafx.scene.*;
import javafx.stage.*;

import scenes.loginScene;
import db.init;
import db.*;

public class appMain extends Application {
	public static void main(String[] args) {
		new init().connectDB();
		launch(args);
	}

	public void start(final Stage myStage) throws Exception {
		myStage.setTitle("VegaBunk");
		
		loginScene l = new loginScene();
		Scene login = l.loadScene(myStage);
		myStage.setScene(login);
		

		
		myStage.show();
	}
}