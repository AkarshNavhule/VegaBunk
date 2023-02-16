package scenes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.*;

public class loginScene{
	public Scene loadScene(final Stage myStage) {
		//scene1 login
		RadioButton Student = new RadioButton("Student");
		RadioButton Teacher = new RadioButton("Teacher");
		ToggleGroup loginGroup = new ToggleGroup();
		Student.setToggleGroup(loginGroup);
		Teacher.setToggleGroup(loginGroup);
		
		Label loginType = new Label("Select Login Type        ");
		Label userText = new Label("     Username");
		Label password = new Label("     Password");
				
		TextField userInput = new TextField();
		TextField passwordInput = new TextField();
				
		Path imgPath = Paths.get("img","logo.jpg");
		Image logo = new Image(imgPath.toString());
		BackgroundImage logoview = new BackgroundImage(logo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
				Background bGround = new Background(logoview);
		
		Button submit = new Button("Submit");
		
		
		GridPane node1 = new GridPane();
		
		node1.setPadding(new Insets(10, 10, 10, 10)); 
	    node1.setVgap(10); 
	    node1.setHgap(10);       
	    node1.setAlignment(Pos.CENTER);
	    node1.setBackground(bGround); 
	   
	    
	    node1.add(loginType, 0, 0);
		node1.add(Student, 1, 0);
		node1.add(Teacher, 2, 0);
		node1.add(userText,0,2); 
		node1.add(userInput,1,2);
		node1.add(password,0,3);
		node1.add(passwordInput, 1,3);
		node1.add(submit,1,4);
		Scene login = new Scene(node1, 1200, 700);
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				homepage h = new homepage();
				Scene loginNext = h.loadScene();
				myStage.setScene(loginNext);
			}
		});	
		
		return login;
	}
}