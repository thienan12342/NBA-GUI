package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Record;
import model.Season;

public class RecordController extends Controller<Season> {
    @FXML private Button closeButton;
    @SuppressWarnings("rawtypes")
    @FXML private TableView roundTv;

    public Season getSeason() {
        return model;
    }

    public ObservableList<Record> getRecord() {
        return getSeason().record();
    }

    @FXML 
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}







