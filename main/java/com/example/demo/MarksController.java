package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.demo.db.dbParser;
import javafx.stage.Stage;

import java.sql.*;
public class MarksController implements Initializable {
    private String username;

    MarksController(String username){
        this.username = username;
    }
        @FXML
        private Label daacie;

        @FXML
        private Label daal1;

        @FXML
        private Label daal2;

        @FXML
        private Label daalcie;

        @FXML
        private Label daall1;

        @FXML
        private Label daall2;

        @FXML
        private Label daalm1;

        @FXML
        private Label daalm2;

        @FXML
        private Label daalm3;

        @FXML
        private Label daam1;

        @FXML
        private Label daam2;

        @FXML
        private Label daam3;

        @FXML
        private Label dbmscie;

        @FXML
        private Label dbmsl1;

        @FXML
        private Label dbmsl2;

        @FXML
        private Label dbmslcie;

        @FXML
        private Label dbmsll1;

        @FXML
        private Label dbmsll2;

        @FXML
        private Label dbmslm1;

        @FXML
        private Label dbmslm2;

        @FXML
        private Label dbmslm3;

        @FXML
        private Label dbmsm1;

        @FXML
        private Label dbmsm2;

        @FXML
        private Label dbmsm3;

        @FXML
        private Label javcie;

        @FXML
        private Label javl1;

        @FXML
        private Label javl2;

        @FXML
        private Label javm1;

        @FXML
        private Label javm2;

        @FXML
        private Label javm3;

        @FXML
        private Label matcie;

        @FXML
        private Label matl1;

        @FXML
        private Label matl2;

        @FXML
        private Label matm1;

        @FXML
        private Label matm2;

        @FXML
        private Label matm3;

        @FXML
        private Label oscie;

        @FXML
        private Label osl1;

        @FXML
        private Label osl2;

        @FXML
        private Label osm1;

        @FXML
        private Label osm2;

        @FXML
        private Label osm3;

        @FXML
        private Label pecie;

        @FXML
        private Label pel1;

        @FXML
        private Label pel2;

        @FXML
        private Label pem1;

        @FXML
        private Label pem2;

        @FXML
        private Label pem3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] sub = {"EM4","DAA","DBMS","OS","JAVA","DBMSL","DAAL","PE"};

        ResultSet rs;
        dbParser db = new dbParser();
        try {
            rs = db.getResMarks(username,sub[0]);
            matm1.setText(String.valueOf(rs.getInt(1)));
            matm2.setText(String.valueOf(rs.getInt(2)));
            matm3.setText(String.valueOf(rs.getInt(3)));
            matl1.setText(String.valueOf(rs.getInt(4)));
            matl2.setText(String.valueOf(rs.getInt(5)));

            rs = db.getResMarks(username,sub[1]);
            daam1.setText(String.valueOf(rs.getInt(1)));
            daam2.setText(String.valueOf(rs.getInt(2)));
            daam3.setText(String.valueOf(rs.getInt(3)));
            daal1.setText(String.valueOf(rs.getInt(4)));
            daal2.setText(String.valueOf(rs.getInt(5)));

            rs = db.getResMarks(username,sub[2]);
            dbmsm1.setText(String.valueOf(rs.getInt(1)));
            dbmsm2.setText(String.valueOf(rs.getInt(2)));
            dbmsm3.setText(String.valueOf(rs.getInt(3)));
            dbmsl1.setText(String.valueOf(rs.getInt(4)));
            dbmsl2.setText(String.valueOf(rs.getInt(5)));

            rs = db.getResMarks(username,sub[3]);
            osm1.setText(String.valueOf(rs.getInt(1)));
            osm2.setText(String.valueOf(rs.getInt(2)));
            osm3.setText(String.valueOf(rs.getInt(3)));
            osl1.setText(String.valueOf(rs.getInt(4)));
            osl2.setText(String.valueOf(rs.getInt(5)));


            rs = db.getResMarks(username,sub[4]);
            javm1.setText(String.valueOf(rs.getInt(1)));
            javm2.setText(String.valueOf(rs.getInt(2)));
            javm3.setText(String.valueOf(rs.getInt(3)));
            javl1.setText(String.valueOf(rs.getInt(4)));
            javl2.setText(String.valueOf(rs.getInt(5)));

            rs = db.getResMarks(username,sub[5]);
            dbmslm1.setText(String.valueOf(rs.getInt(1)));
            dbmslm2.setText(String.valueOf(rs.getInt(2)));
            dbmslm3.setText(String.valueOf(rs.getInt(3)));
            dbmsll1.setText(String.valueOf(rs.getInt(4)));
            dbmsll2.setText(String.valueOf(rs.getInt(5)));

            rs = db.getResMarks(username,sub[6]);
            daalm1.setText(String.valueOf(rs.getInt(1)));
            daalm2.setText(String.valueOf(rs.getInt(2)));
            daalm3.setText(String.valueOf(rs.getInt(3)));
            daall1.setText(String.valueOf(rs.getInt(4)));
            daall2.setText(String.valueOf(rs.getInt(5)));

            rs = db.getResMarks(username,sub[7]);
            pem1.setText(String.valueOf(rs.getInt(1)));
            pem2.setText(String.valueOf(rs.getInt(2)));
            pem3.setText(String.valueOf(rs.getInt(3)));
            pel1.setText(String.valueOf(rs.getInt(4)));
            pel2.setText(String.valueOf(rs.getInt(5)));

            int[] marks = db.getMarks(username);
            matcie.setText(String.valueOf(marks[0]));
            daacie.setText(String.valueOf(marks[1]));
            dbmscie.setText(String.valueOf(marks[2]));
            oscie.setText(String.valueOf(marks[3]));
            javcie.setText(String.valueOf(marks[4]));
            dbmslcie.setText(String.valueOf(marks[5]));
            daalcie.setText(String.valueOf(marks[6]));
            pecie.setText(String.valueOf(marks[7]));


        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @FXML
    void btnGoClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) matm1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Dashboard.fxml"));

        DashboardController dcontroll = new DashboardController(username);

        fxmlLoader.setController(dcontroll);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void btnAttClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) matm1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Attendance.fxml"));
        AttendanceController ac = new AttendanceController(username);
        fxmlLoader.setController(ac);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnHomeClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) matm1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Dashboard.fxml"));

        DashboardController dcontroll = new DashboardController(username);

        fxmlLoader.setController(dcontroll);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }


}
