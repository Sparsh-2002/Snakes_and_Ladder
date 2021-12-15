package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private ImageView Dice;
    @FXML
    private ImageView img;
    //    @FXML
//    public void roll(ActionEvent e){
//
//    }
    @FXML
    private ImageView goti_1;
    @FXML
    private ImageView goti_2;
    @FXML
    private Label localval;

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
    Player p1 = new Player(goti_1, true);
    Player p2 = new Player(goti_2, false);
    @FXML
    private Label diceVal;
    public void move(ActionEvent e) {
        int times = (int)(Math.random()*6+1);
        if(p1.getTurn()){
            roll(p1,times);
            p1.setTurn();
            p2.setTurn();
        }
        else if(p2.getTurn()){
            roll(p2,times);
            p2.setTurn();
            p1.setTurn();
        }

    }
    public void roll(Player p, int r){
        SequentialTransition movement = new SequentialTransition(p);
        for(int i=0;i<r;i++){
            System.out.println(p.getX()+" "+p.getY()+" "+" "+p.getRightup()+" "+p.getgoRight());
            TranslateTransition linear = new TranslateTransition();
            linear.setNode(p);
            if(p.getStart()){
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
                p.setX(42.5);
//            goti.setX(x+=42.5);
                p.setStart(false);
            }
            else if(p.getLeftup()){
                linear.setDuration(Duration.millis(50));
                linear.setByX(42.5);
                p.setX(42.5);
//            goti.setX(x+=42.5);
                p.setLeftup(false);
            }
            else if(p.getRightup()){
                linear.setDuration(Duration.millis(50));
                linear.setByX(-42.5);
                p.setX(-42.5);
//            goti.setX(x-=42.5);
                p.setRightup(false);
            }
            else if(p.getX()<42){
                linear.setDuration(Duration.millis(50));
                linear.setByY(-60);
                p.setY(-60);
//            goti.setY(y-=60);
                p.setLeftup(true);
                p.setgoRight(!p.getgoRight());
            }
            else if(p.getX()>382){
                linear.setDuration(Duration.millis(50));
                linear.setByY(-60);
                p.setY(-60);
//            goti.setY(y-=60);
                p.setRightup(true);
                p.setgoRight(!p.getgoRight());
            }
            else if(p.getgoRight()){
                linear.setDuration(Duration.millis(50));
                linear.setByX(42.5);
                p.setX(42.5);
//            goti.setX(x+=42.5);
            }
            else{
                linear.setDuration(Duration.millis(50));
                linear.setByX(-42.5);
                p.setX(-42.5);
//            goti.setX(x-=42.5);
            }
            movement.getChildren().add(linear);
        }
        movement.play();
        System.out.println(r);
        String str = Integer.toString(r);
        diceVal.setText(str);
    }
}
class Player extends Node {
    double x;
    double y;
    ImageView img;
    private boolean start = true;
    private boolean rightup = false;
    private boolean leftup = false;
    private boolean goright = true;
    private int prevMove;
    private boolean turn;
    public Player(ImageView img1, boolean f){
        this.img = img1;
        this.turn = f;
    }
    void setX(double x1){
        this.img.setX(this.x += x1);
    }
    void setY(double y1){
        this.img.setY(this.y += y1);
    }
    double getX(){
        return this.x;
    }
    double getY(){
        return this.y;
    }
    void setStart(boolean f){
        this.start = f;
    }
    void setRightup(boolean f){
        this.rightup = f;
    }
    void setLeftup(boolean f){
        this.leftup = f;
    }
    void setgoRight(boolean f){
        this.goright = f;
    }
    void setprevMove(int x){
        this.prevMove = x;
    }
    int getPrevMove(){
        return this.prevMove;
    }
    boolean getStart(){
        return this.start;
    }
    boolean getRightup(){
        return this.rightup;
    }
    boolean getLeftup(){
        return this.leftup;
    }
    boolean getgoRight(){
        return this.goright;
    }
    void setTurn(){
        this.turn =! turn;
    }
    boolean getTurn(){
        return this.turn;
    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
