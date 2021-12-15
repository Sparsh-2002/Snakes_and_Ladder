package com.example.demo;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ImageView img;
//    @FXML
//    public void roll(ActionEvent e){
//
//    }
    @FXML
    private ImageView goti;
    @FXML
    private Label localval;
    private double x;
    private double y;
    private boolean start = true;
    private boolean rightup = false;
    private boolean leftup = false;
    private boolean goright = true;
    @FXML
    void findloc(MouseEvent event) {
        localval.setText("X:"+String.valueOf(event.getSceneX())+"Y:"+String.valueOf(event.getSceneY()));
    }
//    public static class Pair{
//        private double x,y;
//        Pair(double x,double y){
//            this.x = x;
//            this.y = y;
//        }
//        public double getX(){return this.x;}
//        public double getY(){return this.y;}
//    }
    @FXML
    private Label diceVal;
    public void move(ActionEvent e) {
        int times = (int)(Math.random()*6+1);
        SequentialTransition movement = new SequentialTransition(goti);
        for(int i=0;i<times;i++){
            System.out.println(x+" "+y+" "+" "+rightup+" "+goright);
            TranslateTransition linear = new TranslateTransition();
            linear.setNode(goti);
            if(start){
//            upmovement.setDuration(Duration.millis(500));
//            upmovement.setByX(x+=15);
//            upmovement.setByY(y-=15);
//            System.out.println(x+" "+y);
//            downmovement.setDuration(Duration.millis(500));
//            downmovement.setByX(x+=15);
//            downmovement.setByY(y+=25);
//            System.out.println(x+" "+y);
//            SequentialTransition mover = new SequentialTransition(goti,upmovement,downmovement);
//            mover.play();
                linear.setDuration(Duration.millis(50));
                linear.setByX(42.5);
                x+=42.5;
//            goti.setX(x+=42.5);
                start = false;
            }
            else if(leftup){
                linear.setDuration(Duration.millis(50));
                linear.setByX(42.5);
                x+=42.5;
//            goti.setX(x+=42.5);
                leftup = false;
            }
            else if(rightup){
                linear.setDuration(Duration.millis(50));
                linear.setByX(-42.5);
                x-=42.5;
//            goti.setX(x-=42.5);
                rightup = false;
            }
            else if(x<42){
                linear.setDuration(Duration.millis(50));
                linear.setByY(-60);
                y-=60;
//            goti.setY(y-=60);
                leftup = true;
                goright=!goright;
            }
            else if(x>382){
                linear.setDuration(Duration.millis(50));
                linear.setByY(-60);
                y-=60;
//            goti.setY(y-=60);
                rightup = true;
                goright=!goright;
            }
            else if(goright){
                linear.setDuration(Duration.millis(50));
                linear.setByX(42.5);
                x+=42.5;
//            goti.setX(x+=42.5);
            }
            else{
                linear.setDuration(Duration.millis(50));
                linear.setByX(-42.5);
                x-=42.5;
//            goti.setX(x-=42.5);
            }
            movement.getChildren().add(linear);
        }
        movement.play();
        System.out.println(times);
        String str = Integer.toString(times);
        diceVal.setText(str);
    }

}