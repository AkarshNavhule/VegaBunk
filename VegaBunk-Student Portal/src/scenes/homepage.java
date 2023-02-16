package scenes;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class homepage{
	public Scene loadScene() {
		//scene2 student
		Path imgPath = Paths.get("img", "cs013.jpg");
		Image sphoto = new Image(imgPath.toString());
		ImageView sView = new ImageView(sphoto);
		Label name = new Label("NAME");
		Label sem = new Label("SEM-DEPT");
		Label subjectName;
		
		String subjects[] = {"MATHS", "OS", "DBMS", "DAA", "JAVA","WEB"};
		sView.setFitHeight(100);
		sView.setFitWidth(100);
		GridPane node2 = new GridPane();
		node2.setGridLinesVisible(true);
	
		node2.setPadding(new Insets(10, 10, 10, 10)); 
		//node2.setVgap(10); 
//		node2.setHgap(10);       
		
		node2.add(sView,0,0,3,4);
		node2.add(name,4,2);
		node2.add(sem,4,3);
		
		node2.add(new Label("Subjects"), 0, 5,3,3);
		node2.add(new Label("Attendance"), 2, 5,3,3);
		node2.add(new Label("CIE"), 3, 5,3,3);
		for(int i=0; i<6;i++) {
			subjectName = new Label(subjects[i]);
			
			node2.add(subjectName, 0, 3 + 3*i);
			
		}
		
		final Scene loginNext = new Scene(node2,1200,700);
		
		

		return loginNext;
	}
}