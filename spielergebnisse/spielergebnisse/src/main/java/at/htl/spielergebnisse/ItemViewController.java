/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.spielergebnisse;

import at.htl.spielergebnisse.model.Match;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author elias
 */
public class ItemViewController implements Initializable{
    @FXML
    private ImageView homeImageView;
    @FXML
    private ImageView guestImageView;
    @FXML
    private Label ItemLevel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void setGame(Match item) {
        if(item.getTeam1() != null && item.getTeam2() != null){
            homeImageView.setImage(new Image(item.getTeam1().getIconUrl()));
            String text = item.getTeam1().getName() + " - "
                    + item.getTeam1Score() + ":" + item.getTeam2Score()
                    + " - " + item.getTeam2().getName();

            this.ItemLevel.setText(text);
            guestImageView.setImage(new Image(item.getTeam2().getIconUrl()));
        }
    }
    
}
