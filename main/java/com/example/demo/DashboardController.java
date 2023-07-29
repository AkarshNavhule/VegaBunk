package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo.db.dbParser;
import com.example.demo.bunk.bunkCalculator;

public class DashboardController implements Initializable {

    private String username;
    DashboardController(String username){
        this.username = username;
    }

    ObservableList<String> SubjectList = FXCollections.observableArrayList("EM4","DAA","DBMS","OS","JAVA","DBMSL","DAAL","PE");

    @FXML
    private TextField ExpPerc;

    @FXML
    private Label StudBranch;

    @FXML
    private Label StudName;
    @FXML
    private ImageView StudPic;

    @FXML
    private Label StudSem;

    @FXML
    private Label USN;

    @FXML
    private ComboBox mainSubjectBox;

    @FXML
    private BarChart<String, Integer> DashBar;

    @FXML
    private PieChart DashPie;
    @FXML
    private Label remaining;
    @FXML
    private Label bunk;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        remaining.setText(" ");
        bunk.setText(" ");
        //ComboBox
        mainSubjectBox.setValue("EM4");
        mainSubjectBox.setItems(SubjectList);

        StudPic.setImage(new Image("img\\Faces 2\\" + username +".png"));
        dbParser db = new dbParser();

        try {
            ResultSet rs;
            //details
            rs = db.getDetails(username);
            StudName.setText(rs.getString(1));
            USN.setText(rs.getString(2));
            StudBranch.setText(rs.getString(3));
            StudSem.setText(rs.getString(4));




            //PieChart
            int[] attendance = db.getAttendance(username);
            int[][] doneTotal = db.getTotalDoneClass();
            int attended = 0;
            int done = 0;
            int total = 0;
            for(int i = 0; i < attendance.length; i++) {
                attended += attendance[i];
                done += doneTotal[0][i];
                total += doneTotal[1][i];
            }

            //BarChart
            int[] marks = db.getMarks(username);
            XYChart.Series<String, Integer> series1 = new XYChart.Series();
            series1.setName("CIE Marks");
            series1.getData().add(new XYChart.Data("EM-IV", marks[0]));
            series1.getData().add(new XYChart.Data("DAA", marks[1]));
            series1.getData().add(new XYChart.Data("DBMS", marks[2]));
            series1.getData().add(new XYChart.Data("OS", marks[3]));
            series1.getData().add(new XYChart.Data("JAVA", marks[4]));
            series1.getData().add(new XYChart.Data("DBMS-L", marks[5]));
            series1.getData().add(new XYChart.Data("DAA-L", marks[6]));
            series1.getData().add(new XYChart.Data("PE", marks[7]));

            DashBar.getData().addAll(series1);
            DashBar.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

            //Calculator
            double present = (double) attended / done * 100;
            double absent = 100.00 - present;
            ObservableList<PieChart.Data> DashPieData = FXCollections.observableArrayList(
                    new PieChart.Data("Present", present),
                    new PieChart.Data("Absent", absent));
            DashPie.setData(DashPieData);


            db.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void btnBunkClicked(ActionEvent event) throws SQLException {
        try {
            dbParser db = new dbParser();
            bunkCalculator bc = new bunkCalculator();

            int exp = Integer.parseInt(ExpPerc.getText());
            String sub = (String) mainSubjectBox.getValue();

            int[] attendance = db.getAttendedSub(sub,username);
            int val = bc.value(exp,attendance[0],attendance[1],attendance[2]);
            if(val == -1) {
                bunk.setText("Not Eligible to miss classes.\nTry lowering your expectations");
            }
            else {
                remaining.setText("Remaining Classes: " + (attendance[2] - attendance[1]) );
                bunk.setText("Number of Classes that can be MISSED: " + val);
            }
        }catch (Exception e){
            e.printStackTrace();
            remaining.setText("Enter Values");
        }


    }

    @FXML
    void btnAttClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) StudName.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Attendance.fxml"));
        AttendanceController ac = new AttendanceController(username);
        fxmlLoader.setController(ac);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnMarksClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) StudName.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Marks.fxml"));
        MarksController mc = new MarksController(username);
        fxmlLoader.setController(mc);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }
}



