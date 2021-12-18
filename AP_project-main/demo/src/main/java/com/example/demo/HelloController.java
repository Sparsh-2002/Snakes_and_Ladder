package com.example.demo;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.lang.reflect.Array;
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
        String str = Integer.toString(times);
        diceVal.setText(str);
//        System.out.println("for dice value: "+times+" "+ p1.getHasStarted()+" "+p2.getHasStarted());
        if(!p1.getHasStarted() && times==1 && p1.getTurn()){
            p1.setHasStarted();
            roll(p1,times);
            p1.setTurn();
            p2.setTurn();
            return;
        }
//        System.out.println("reached checkpoint 1");
        if(!p2.getHasStarted() && times==1 && p2.getTurn()){
            p2.setHasStarted();
            roll(p2,times);
            p2.setTurn();
            p1.setTurn();
            return;
        }
        if(p1.getTurn() && p1.getHasStarted()){
//            System.out.println("entered checkpoint 3");
            System.out.println(p1.getX());
            roll(p1,times);
        }
        else if(p2.getTurn() && p2.getHasStarted()){
//            System.out.println("entered checkpoint 4");
            roll(p2,times);
        }
        p1.setTurn();
        p2.setTurn();
    }
    public void roll(Player p, int r){
        SequentialTransition movement = new SequentialTransition(p.getImage());
        boolean checker = false;
        for(int i=0;i<r;i++){
//            System.out.println(p.getX()+" "+p.getY()+" "+" "+p.getRightup()+" "+p.getgoRight());
//            TranslateTransition linear = new TranslateTransition();
//            linear.setNode(p.getImage());
//            if(p.getStart()){
//                linear.setDuration(Duration.millis(50));
//                if(p.setX(42.5)) linear.setByX(42.5);
//                else{
//                    checker = true;
//                    continue;
//                }
//                p.setStart(false);
//            }
//            else if(p.getLeftup()){
//                linear.setDuration(Duration.millis(50));
//                if(p.setX(42.5))  linear.setByX(42.5);
//                else{
//                    checker = true;
//                    continue;
//                }
//                p.setLeftup(false);
//            }
//            else if(p.getRightup()){
//                linear.setDuration(Duration.millis(50));
//                if(p.setX(-42.5)) linear.setByX(-42.5);
//                else{
//                    checker = true;
//                    continue;
//                }
//                p.setRightup(false);
//            }
//            else if(p.getX()<42){
//                linear.setDuration(Duration.millis(50));
//                if(p.setY(-60)) linear.setByY(-60);
//                else{
//                    checker = true;
//                    continue;
//                }
//                p.setLeftup(true);
//                p.setgoRight(!p.getgoRight());
//            }
//            else if(p.getX()>382){
//                linear.setDuration(Duration.millis(50));
//                if(p.setY(-60))   linear.setByY(-60);
//                else{
//                    checker = true;
//                    continue;
//                }
//                p.setRightup(true);
//                p.setgoRight(!p.getgoRight());
//            }
//            else if(p.getgoRight()){
//                linear.setDuration(Duration.millis(50));
//                if(p.setX(42.5)) linear.setByX(42.5);
//                else checker = true;
//            }
//            else{
//                linear.setDuration(Duration.millis(50));
//                if(p.setX(-42.5)) linear.setByX(-42.5);
//                else checker = true;
//            }
            TranslateTransition current = p.move();
            if(current==null) {
                checker = true;
                break;
            }
            else movement.getChildren().add(current);

        }
        for(SNL t: Snakes_and_Ladders){
            if(t.getHead()==p.getTile()){
                System.out.println("current "+t.getHead().getX()+" "+t.getHead().getY());
                movement.getChildren().add(t.animate(p));
                System.out.println("After  "+t.getTail().getX()+" "+t.getTail().getY());
                break;
            }
        }
        if(checker==false)
            movement.play();
        System.out.println(r);
        p.setprevMove(r);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double currx=0,curry=0;
        int counter = 1;
        for(int i=0;i<=10;i++){
            for(int j=0;j<9;j++){
                alltiles.add(new Tile(currx,curry, counter));
                if(i%2==1){
                    currx-=42.5;
                }
                else currx+=42.5;
                counter += 1;
            }
            alltiles.add(new Tile(currx,curry, counter));
            counter++;
            curry-=60;
        }
        for(int i=0;i<100;i++){
            Tile current = alltiles.get(i);
            current.setNextTile(alltiles.get(i+1));
        }
//        alltiles.get(99).setNextTile(null);
        for(int i=0;i<100;i++)System.out.println(alltiles.get(i).getId()+" "+alltiles.get(i).getX()+" "+alltiles.get(i).getY()+" "+alltiles.get(i).next.getX()+" "+alltiles.get(i).next.getY());

        Tile t = new Tile(0.0,60.0,0);
        t.setNextTile(alltiles.get(0));
        p1 = new Player(goti1, true,t);
        p2 = new Player(goti2, false,t);
        ArrayList<Tile> A1 = new ArrayList<Tile>();
        A1.add(alltiles.get(27));A1.add(alltiles.get(26));A1.add(alltiles.get(13));A1.add(alltiles.get(12));A1.add(alltiles.get(13));
        ArrayList<Tile> A2 = new ArrayList<Tile>();
        A2.add(alltiles.get(25));A2.add(alltiles.get(24));A2.add(alltiles.get(15));A2.add(alltiles.get(14));A2.add(alltiles.get(15));
        ArrayList<Tile> A3 = new ArrayList<Tile>();
        A3.add(alltiles.get(23));A3.add(alltiles.get(22));A3.add(alltiles.get(17));A3.add(alltiles.get(16));A3.add(alltiles.get(17));
        ArrayList<Tile> A4 = new ArrayList<Tile>();
        A4.add(alltiles.get(54));A4.add(alltiles.get(53));A4.add(alltiles.get(46));A4.add(alltiles.get(45));A4.add(alltiles.get(34));A4.add(alltiles.get(33));
        ArrayList<Tile> A5= new ArrayList<Tile>();
        A5.add(alltiles.get(56));A5.add(alltiles.get(55));A5.add(alltiles.get(44));A5.add(alltiles.get(43));A5.add(alltiles.get(36));A5.add(alltiles.get(35));
        ArrayList<Tile> A6 = new ArrayList<Tile>();
        A6.add(alltiles.get(58));A6.add(alltiles.get(57));A6.add(alltiles.get(42));A6.add(alltiles.get(41));A6.add(alltiles.get(38));A6.add(alltiles.get(37));
        ArrayList<Tile> A7 = new ArrayList<Tile>();
        A7.add(alltiles.get(94));A7.add(alltiles.get(93));A7.add(alltiles.get(86));A7.add(alltiles.get(85));A7.add(alltiles.get(74));A7.add(alltiles.get(73));
        ArrayList<Tile> A8= new ArrayList<Tile>();
        A8.add(alltiles.get(96));A8.add(alltiles.get(95));A8.add(alltiles.get(84));A8.add(alltiles.get(83));A8.add(alltiles.get(76));A8.add(alltiles.get(75));
        ArrayList<Tile> A9 = new ArrayList<Tile>();
        A9.add(alltiles.get(98));A9.add(alltiles.get(97));A9.add(alltiles.get(82));A9.add(alltiles.get(81));A9.add(alltiles.get(78));A9.add(alltiles.get(77));
        ArrayList<Tile> A10 = new ArrayList<Tile>();
        A10.add(alltiles.get(90));A10.add(alltiles.get(89));A10.add(alltiles.get(70));A10.add(alltiles.get(69));A10.add(alltiles.get(50));A10.add(alltiles.get(49));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(4),alltiles.get(16)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(6),alltiles.get(14)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(8),alltiles.get(12)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(27),alltiles.get(13),A1,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(25),alltiles.get(15),A2,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(23),alltiles.get(17),A3,true));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(32),alltiles.get(46)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(34),alltiles.get(44)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(36),alltiles.get(42)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(39),alltiles.get(80)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(54),alltiles.get(33),A4,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(56),alltiles.get(35),A5,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(58),alltiles.get(37),A6,true));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(67),alltiles.get(86)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(65),alltiles.get(84)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(63),alltiles.get(82)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(90),alltiles.get(49),A10,false));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(94),alltiles.get(73),A7,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(96),alltiles.get(75),A8,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(98),alltiles.get(77),A9,true));
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
        Tile current;
        ImageView img;
        private boolean start = true;
        private boolean rightup = false;
        private boolean leftup = false;
        private boolean goright = true;
        private int prevMove;
        private boolean turn;
        private boolean hasStarted = false;
        public Player(ImageView img1, boolean f,Tile current){
            this.img = img1;
            this.turn = f;
            this.current = current;
//            System.out.println("Player completed with turn: "+ f);
//            System.out.println(this.img==null);
        }
        double getX(){
            return this.current.getX();
        }
        double getY(){
            return this.current.getY();
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
        void setHasStarted(){
            this.hasStarted = true;
        }
        boolean getHasStarted(){
            return this.hasStarted;
        }
        TranslateTransition move(){
            TranslateTransition movement = new TranslateTransition();
            if(current.next==null) return null;
            movement.setDuration(Duration.millis(250));
            System.out.println("current location "+current.getX()+" "+current.getY());
            movement.setByX(current.next.getX()-current.getX());
            movement.setByY(current.next.getY()-current.getY());
            current = current.next;
            System.out.println("next location "+current.getX()+" "+current.getY());
            return movement;
        }
        public ImageView getImage() {
            return this.img;
        }
        public Tile getTile() { return this.current;}
        public void setTile(Tile next){
            current = next;
        }

    }
    interface SNL{
        Tile getHead();
        Tile getTail();
        SequentialTransition animate(Player p);
    }
    class Snakes implements SNL{
        Tile head;
        Tile tail;
        ArrayList<Tile> body;
        boolean zigzag;
        public Snakes(Tile H, Tile T,ArrayList<Tile> body, boolean zigzag){
            this.head = H;
            this.tail = T;
            this.body = body;
            this.zigzag = zigzag;
//            for(Tile temp: body) System.out.println(temp.getX()+" "+temp.getY());
        }
        @Override
        public Tile getHead() {
            return this.head;
        }
        @Override
        public Tile getTail() {
            return this.tail;
        }

        @Override
        public SequentialTransition animate(Player p){
            if(zigzag) return zigzagAnimation(p);
            return linearAnimation(p);
        }
        private SequentialTransition linearAnimation(Player p) {
            return null;
        }

        private SequentialTransition zigzagAnimation(Player p) {
            SequentialTransition completemovement = new SequentialTransition(p.getImage());
            System.out.println("Reached zigzag " + body.size());
            for(int i=0;i<body.size()-1;i++){
                TranslateTransition movement = new TranslateTransition();
                movement.setDuration(Duration.millis(500));
                System.out.println("Reached zizag snake: "+body.get(i).getX()+" "+body.get(i).getY());
                movement.setByX(((body.get(i+1).getX())-body.get(i).getX()));
                movement.setByY(((body.get(i+1).getY())-body.get(i).getY()));
                p.setTile(body.get(i+1));
                movement.play();
                completemovement.getChildren().add(movement);
            }
            return completemovement;
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
        public Tile getHead() {
            return this.head;
        }
        @Override
        public Tile getTail() {
            return this.tail;
        }
        @Override
        public SequentialTransition animate(Player p) {
            SequentialTransition completemovemnt = new SequentialTransition(p.getImage());
            TranslateTransition movement = new TranslateTransition();
            movement.setDuration(Duration.millis(1000));
            movement.setByY(tail.getY()-head.getY());
            movement.setByX(tail.getX()-head.getX());
            p.setTile(tail);
            completemovemnt.getChildren().add(movement);
            return completemovemnt;
        }
    }
}