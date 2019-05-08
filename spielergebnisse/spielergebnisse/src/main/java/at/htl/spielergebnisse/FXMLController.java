package at.htl.spielergebnisse;

import at.htl.spielergebnisse.model.Match;
import at.htl.spielergebnisse.model.Team;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import at.htl.spielergebnisse.repository.Repo;
import at.htl.spielergebnisse.rest.RestClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

public class FXMLController implements Initializable {

    @FXML
    private ListView<Match> gamelistview;
    @FXML
    private ChoiceBox cbLiga;
    @FXML
    private ChoiceBox cbSaison;
    @FXML
    private TextField tfSpieltag;
    @FXML
    private Label lLiga;
    @FXML
    private Label lSaison;
    @FXML
    private Label lSpieltag;
    @FXML
    private Button btnSearch;

    RestClient rest = new RestClient();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Match> items = FXCollections.observableArrayList(getItemList());
        ObservableList<String> leagues = FXCollections.observableArrayList("1. Bundesliga", "2. Bundesliga");
        ObservableList<String> seasons = FXCollections.observableArrayList("2018","2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010");
        gamelistview.setItems(items);
        cbLiga.setItems(leagues);
        cbSaison.setItems(seasons);
        
        gamelistview.setCellFactory(param -> new ListCell<Match>(){
            
            @Override
            public void updateItem(Match item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                    setGraphic(null);
                } else{
                    try {
                        Parent root = null;
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemView.fxml"));
                        root = (Parent) loader.load();
                        ItemViewController ctrl = loader.getController();
                        ctrl.setGame(item);
                        setGraphic(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            
        });
        
    }   
    
    private List<Match> getItemList() {
        List<Match> list = new LinkedList<Match>();  
        return list;
    } 

    @FXML
    private void search(ActionEvent event) {
        String league = (String) cbLiga.getValue();
        int season = Integer.parseInt(String.valueOf(cbSaison.getValue()));
        int spieltag = Integer.parseInt(tfSpieltag.getText());
        switch (league){
            case "1. Bundesliga":
                league = "bl1";
                break;
            case "2. Bundesliga":
                league = "bl2";
                break;
            default:
                break;
        }
        rest.importTeams(league, season);
        rest.importMatches(league, season, spieltag);
        updateListView();
    }

    private void updateListView(){
        gamelistview.getItems().clear();
        ObservableList<Match> matches = FXCollections.observableArrayList(Repo.getInstance().getMatches());
        gamelistview.setItems(matches);
    }
}
