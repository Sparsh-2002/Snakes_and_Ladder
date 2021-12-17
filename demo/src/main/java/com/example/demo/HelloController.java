package com.example.demo;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView Dice;
    @FXML
    private ImageView img;
    @FXML
    private ImageView goti1;
    @FXML
    private ImageView goti2;
    @FXML
    private Label localval;
    @FXML
    void findloc(MouseEvent event) {
        localval.setText("X:"+String.valueOf(event.getSceneX())+"Y:"+String.valueOf(event.getSceneY()));
    }
    Player p1,p2;
    @FXML
    private Label diceVal;
    ArrayList<Tile> alltiles = new ArrayList<Tile>();
    ArrayList<SNL> Snakes_and_Ladders = new ArrayList<SNL>();
    public void move(ActionEvent e) {
        int times = (int)(Math.random()*6+1);
        if(times==6){
            int temp = (int)(Math.random()*6+1);
            if(temp==6){
                int temp2 = (int)(Math.random()*6+1);
                if(temp2==6) times=0;
                else times+=temp+temp2;
            }
            else{
                times+=temp;
            }
        }
        if(p1.getTurn()){
            System.out.println(p1.getX());
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
        SequentialTransition movement = new SequentialTransition(p.getImage());
        boolean checker = false;
        for(int i=0;i<r;i++){
            System.out.println(p.getX()+" "+p.getY()+" "+" "+p.getRightup()+" "+p.getgoRight());
            TranslateTransition linear = new TranslateTransition();
            linear.setNode(p.getImage());
            if(p.getStart()){
                linear.setDuration(Duration.millis(50));
                if(p.setX(42.5)) linear.setByX(42.5);
                else{
                    checker = true;
                    continue;
                }
                p.setStart(false);
            }
            else if(p.getLeftup()){
                linear.setDuration(Duration.millis(50));
                if(p.setX(42.5))  linear.setByX(42.5);
                else{
                    checker = true;
                    continue;
                }
                p.setLeftup(false);
            }
            else if(p.getRightup()){
                linear.setDuration(Duration.millis(50));
                if(p.setX(-42.5)) linear.setByX(-42.5);
                else{
                    checker = true;
                    continue;
                }
                p.setRightup(false);
            }
            else if(p.getX()<42){
                linear.setDuration(Duration.millis(50));
                if(p.setY(-60)) linear.setByY(-60);
                else{
                    checker = true;
                    continue;
                }
                p.setLeftup(true);
                p.setgoRight(!p.getgoRight());
            }
            else if(p.getX()>382){
                linear.setDuration(Duration.millis(50));
                if(p.setY(-60))   linear.setByY(-60);
                else{
                    checker = true;
                    continue;
                }
                p.setRightup(true);
                p.setgoRight(!p.getgoRight());
            }
            else if(p.getgoRight()){
                linear.setDuration(Duration.millis(50));
                if(p.setX(42.5)) linear.setByX(42.5);
                else checker = true;
            }
            else{
                linear.setDuration(Duration.millis(50));
                if(p.setX(-42.5)) linear.setByX(-42.5);
                else checker = true;
            }
            movement.getChildren().add(linear);
        }
        if(checker==false)
            movement.play();
        System.out.println(r);
        String str = Integer.toString(r);
        diceVal.setText(str);
        p.setprevMove(r);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        p1 = new Player(goti1, true);
        p2 = new Player(goti2, false);
        double currx=0,curry=0;
        int counter = 1;
        for(int i=0;i<=10;i++){
            for(int j=0;j<=10;j++){
                alltiles.add(new Tile(currx,curry, counter));
                if(j%2==1){
                    currx+=42;
                }
                else currx-=42;
                counter += 1;
            }
            curry-=60;
        }
        for(int i=0;i<100;i++){
            Tile current = alltiles.get(i);
            current.setNextTile(alltiles.get(i+1));
        }
        Snakes_and_Ladders.add(new Ladder(alltiles.get(4),alltiles.get(16)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(6),alltiles.get(14)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(8),alltiles.get(12)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(27),alltiles.get(13)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(25),alltiles.get(15)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(23),alltiles.get(17)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(32),alltiles.get(46)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(34),alltiles.get(44)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(36),alltiles.get(42)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(39),alltiles.get(80)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(54),alltiles.get(33)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(56),alltiles.get(35)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(58),alltiles.get(37)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(67),alltiles.get(86)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(65),alltiles.get(84)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(63),alltiles.get(82)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(90),alltiles.get(49)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(94),alltiles.get(73)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(96),alltiles.get(75)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(98),alltiles.get(77)));
    }
    class Tile{
        private double x,y;
        private Tile next;
        private int id;
        Tile(double x,double y, int i){
            this.x=x;this.y=y;
            this.id = i;
        }
        public void setNextTile(Tile next){
            this.next=next;
        }
        public Tile getNextTile(){
            return this.next;
        }
        public double getX(){
            return this.x;
        }
        public double getY(){
            return this.y;
        }
        public int getId(){
            return this.id;
        }
    }
    class Player{
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
            this.x=0;this.y=0;
//            System.out.println("Player completed with turn: "+ f);
//            System.out.println(this.img==null);
        }
        boolean setX(double x1){
            if(x+x1<0) return false;
            this.x+=x1;
            return true;
        }
        boolean setY(double y1){
            if(y+y1<-541) return false;
            this.y+=y1;
            return true;
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
        public ImageView getImage() {
            return this.img;
        }
    }
    interface SNL{
        void animation();
        Tile getHead();
        Tile getTail();
    }
    class Snakes implements SNL{
        Tile head;
        Tile tail;
        public Snakes(Tile H, Tile T){
            this.head = H;
            this.tail = T;
        }

        @Override
        public void animation() {

        }

        @Override
        public Tile getHead() {
            return this.head;
        }

        @Override
        public Tile getTail() {
            return this.tail;
        }
    }
    class Ladder implements SNL{
        Tile head;
        Tile tail;
        public Ladder(Tile H, Tile T){
            this.head = H;
            this.tail = T;
        }

        @Override
        public void animation() {

        }

        @Override
        public Tile getHead() {
            return this.head;
        }

        @Override
        public Tile getTail() {
            return this.tail;
        }
    }
}



