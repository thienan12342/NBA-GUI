package controller;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Teams;

public class ExploreTeamsController extends Controller<Teams> {
    @FXML private Button teamsMenuButton;
    @FXML private Button viewPlayersButton;
    @FXML private Button closeButton;

    @FXML 
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public Teams getTeams(){
        return this.model;
    }


    @FXML 
    public void teamsMenu() throws IOException{
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(),"/view/TeamsTable.fxml", "Teams Menu", stage);
    }

    @FXML 
    public void viewPlayers() throws IOException{
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(),"/view/PlayersView.fxml", "View Players", stage);
    }
}

