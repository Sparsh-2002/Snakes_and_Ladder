package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private ImageView img;

    @FXML
    private ImageView goti;
    @FXML
    private Label localval;
    private double x;

    @FXML
    void findloc(MouseEvent event) {
        localval.setText("X:"+String.valueOf(event.getSceneX())+"Y:"+String.valueOf(event.getSceneY()));
    }
    public void move(ActionEvent e){
        goti.setX(x+=40);
    }
}