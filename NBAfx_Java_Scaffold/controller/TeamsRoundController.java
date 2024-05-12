package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

public class TeamsRoundController extends Controller<Season> {

    @SuppressWarnings("rawtypes")
    @FXML
    private TableView roundTv;

    @FXML
    private Label roundLbl;

    @FXML
    private Button addBtn;

    @FXML
    private ListView<Team> teamsLv;

    @FXML
    private Button arrangeBtn;

    @FXML
    private TableColumn<Game, Integer> gameCol;
    @FXML
    private TableColumn<Game, String> team1Col;
    @FXML
    private TableColumn<Game, String> team2Col;

    private boolean canAdd = false;

    public Season getSeason() {
        return this.model;
    }

    @SuppressWarnings("unchecked")
    @FXML
    public void initialize() {
        roundLbl.setText("Round: " + (getSeason().round() + 1));

        teamsLv.setItems(getSeason().getCurrentTeams());
        teamsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        teamsLv.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<?> change) -> {
            if (teamsLv.getSelectionModel().getSelectedItems().size() == 2) {
                addBtn.setDisable(false);
                canAdd = true;
            } else if (teamsLv.getItems().size() == 0) {
                addBtn.setDisable(true);
                arrangeBtn.setDisable(false);
            } else {
                addBtn.setDisable(true);
                canAdd = false;
            }
        });

        if (teamsLv.getItems().size() == 0) arrangeBtn.setDisable(false);

        roundTv.setItems(getSeason().getCurrentSchedule());
        roundTv.getSelectionModel().select(null);

        gameCol.setCellValueFactory(game -> game.getValue().termProperty().asObject());
        team1Col.setCellValueFactory(team1 -> team1.getValue().team1());
        team2Col.setCellValueFactory(team2 -> team2.getValue().team2());
    }

    @FXML
    public void add() {
        if (canAdd) {
            getSeason().addTeams(teamsLv.getSelectionModel().getSelectedItems());
            teamsLv.getSelectionModel().select(-1);
            teamsLv.setItems(getSeason().getCurrentTeams());
        }
    }

    @FXML
    public void arrange() {
        stage.close();
    }

}