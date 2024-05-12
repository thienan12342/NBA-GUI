package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class ManageTeamController extends Controller<Team> {
    @FXML private TextField teamTf;
    @FXML private TableView<Player> teamTv;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    
    private Parent root;
    
    public Team getThings() {return model;}

    public ObservableList<Player> getStuff() {
        return getThings().getCurrentPlayers();
    }
    
    private Player updatePlayer() {
        return teamTv.getSelectionModel().getSelectedItem();
    }
    
    private void newTeamList() {
        String name = updatePlayer().getName();
        String level = updatePlayer().getLevel();
        Integer ag1 = updatePlayer().getAge();
        Integer ag2 = updatePlayer().getAge();
        getThings().getPlayers().filterList(name, level, ag1, ag2);
    }
    
    private String getPlayerName() {
        return updatePlayer().getName();
    }
    
    private Boolean testValidName(String text) {
        return text.matches("^[A-Z][a-zA-Z ]+$"); 
    }
    
    @FXML
    public void initialize() {
        teamTf.setText(getThings().getName());
        updateButton.disableProperty().bind(Bindings.isEmpty(teamTv.getSelectionModel().getSelectedItems()));
        deleteButton.disableProperty().bind(Bindings.isEmpty(teamTv.getSelectionModel().getSelectedItems()));
        addButton.disableProperty().bind(Bindings.isNotEmpty(teamTv.getSelectionModel().getSelectedItems()));
    }
    
    @FXML
    public void handleAdd(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getThings(), "/view/PlayerUpdateView.fxml", "Adding New Player", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    @FXML
    private void handleUpdate(ActionEvent event) {
        String name = getPlayerName();
        newTeamList();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getThings(), "/view/PlayerUpdateView.fxml", "Updating Player: " + name, stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   @FXML
    private void handleDelete(ActionEvent event) {
        List<Player> stuffCopy = new ArrayList<>(getStuff()); // Create a copy of the list
        for (Player i : stuffCopy) {
            if (i.getName().equals(updatePlayer().getName())) {
            getStuff().remove(i); // Remove from the original list
            }
        }
    }

    
    @FXML
    public void close() {
  
        if (!testValidName(teamTf.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
                root = loader.load();
                ErrorController errorController = loader.getController();
                errorController.generateNameError();
                
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                stage.setTitle("Error!");

                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            getThings().setName(teamTf.getText());
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }
}

  

