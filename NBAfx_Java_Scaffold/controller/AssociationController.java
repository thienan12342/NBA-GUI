package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Association;

public class AssociationController extends Controller<Association> {

    @FXML
    private Button exploreTeamsButton;

    @FXML
    private Button newSeasonButton;

    @FXML
    private Button exitButton;

    @FXML
    private GridPane buttonGrid;
    
    public Association getAssociation(){
        return this.model;
    }
    
    @FXML
    public void exploreTeams() throws IOException {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getAssociation().getTeams(), "/view/ExploreTeamsView.fxml", "Explore Teams", stage);
        } 
    @FXML
    public void newSeason() throws IOException {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getAssociation().getSeason(), "/view/SeasonView.fxml", "Arrange a new season", stage);
    }
    @FXML
    public void exit() {
        Platform.exit();
    }
}
