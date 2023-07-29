package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.example.demo.db.dbParser;
import javafx.stage.Stage;

public class AttendanceController implements Initializable {
    private String username;

    AttendanceController(String username){
        this.username = username;
    }
    @FXML
    private Label daaa;

    @FXML
    private Label daae;

    @FXML
    private Label daala;

    @FXML
    private Label daale;

    @FXML
    private Label daalper;

    @FXML
    private Label daaper;

    @FXML
    private Label dbmsa;

    @FXML
    private Label dbmse;

    @FXML
    private Label dbmsla;

    @FXML
    private Label dbmsle;

    @FXML
    private Label dbmslper;

    @FXML
    private Label dbmsper;

    @FXML
    private Label java;

    @FXML
    private Label jave;

    @FXML
    private Label javper;

    @FXML
    private Label mata;

    @FXML
    private Label mate;

    @FXML
    private Label matper;

    @FXML
    private Label osa;

    @FXML
    private Label ose;

    @FXML
    private Label osper;

    @FXML
    private Label pea;

    @FXML
    private Label pee;

    @FXML
    private Label peper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbParser db = new dbParser();
        try {
            int[][] DoneTotal = db.getTotalDoneClass();
            mate.setText(String.valueOf(DoneTotal[0][0]));
            daae.setText(String.valueOf(DoneTotal[0][1]));
            dbmse.setText(String.valueOf(DoneTotal[0][2]));
            ose.setText(String.valueOf(DoneTotal[0][3]));
            jave.setText(String.valueOf(DoneTotal[0][4]));
            dbmsle.setText(String.valueOf(DoneTotal[0][5]));
            daale.setText(String.valueOf(DoneTotal[0][6]));
            pee.setText(String.valueOf(DoneTotal[0][7]));

            int[] attend = db.getAttendance(username);
            mata.setText(String.valueOf(attend[0]));
            daaa.setText(String.valueOf(attend[1]));
            dbmsa.setText(String.valueOf(attend[2]));
            osa.setText(String.valueOf(attend[3]));
            java.setText(String.valueOf(attend[4]));
            dbmsla.setText(String.valueOf(attend[5]));
            daala.setText(String.valueOf(attend[6]));
            pea.setText(String.valueOf(attend[7]));

            matper.setText(String.valueOf((int) (((double) attend[0] / DoneTotal[0][0]) * 100)) + "%");
            daaper.setText(String.valueOf((int) (((double) attend[1] / DoneTotal[0][1]) * 100)) + "%");
            dbmsper.setText(String.valueOf((int) (((double) attend[2] / DoneTotal[0][2]) * 100)) + "%");
            osper.setText(String.valueOf((int) (((double) attend[3] / DoneTotal[0][3]) * 100)) + "%");
            javper.setText(String.valueOf((int) (((double) attend[4] / DoneTotal[0][4]) * 100)) + "%");
            dbmslper.setText(String.valueOf((int) (((double) attend[5] / DoneTotal[0][5]) * 100)) + "%");
            daalper.setText(String.valueOf((int) (((double) attend[6] / DoneTotal[0][6]) * 100)) + "%");
            peper.setText(String.valueOf((int) (((double) attend[7] / DoneTotal[0][7]) * 100)) + "%");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnGoClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) mate.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Dashboard.fxml"));

        DashboardController dcontroll = new DashboardController(username);

        fxmlLoader.setController(dcontroll);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnHomeClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) mata.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Dashboard.fxml"));

        DashboardController dcontroll = new DashboardController(username);

        fxmlLoader.setController(dcontroll);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnMarksClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) mata.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("Marks.fxml"));
        MarksController mc = new MarksController(username);
        fxmlLoader.setController(mc);
        Scene scene = new Scene(fxmlLoader.load(), 1396, 723);
        stage.setTitle(username);
        stage.setScene(scene);
        stage.show();
    }
}
