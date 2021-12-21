package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
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
    private ImageView arrow;
    @FXML
    private Label localval;
    @FXML
    void findloc(MouseEvent event) {
        localval.setText("X:"+String.valueOf(event.getSceneX())+"Y:"+String.valueOf(event.getSceneY()));
    }
    @FXML
    private Button rollButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Rectangle red1;
    @FXML
    private Rectangle red2;
    @FXML
    private Rectangle black1;
    @FXML
    private Rectangle black2;
    @FXML
    private Label Player1;
    @FXML
    private Label Player2;
    public void switchtoScene1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoSceneoriginal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoScene2(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoScene3(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    Player p1,p2;
    @FXML
    private Label diceVal;
    ArrayList<Tile> alltiles = new ArrayList<Tile>();
    ArrayList<SNL> Snakes_and_Ladders = new ArrayList<SNL>();
    public void move(ActionEvent e) throws IOException {
        int times = (int)(Math.random()*6+1);
        String str = Integer.toString(times);
        diceVal.setText(str);
        if(p1.getTurn()){
            p2.black.setOpacity(1);
        }
        if(p2.getTurn()){
            p1.black.setOpacity(1);
        }
//        if(times==1){
//            File f = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/1.png");
//            Dice.setImage(new Image(f.toURI().toString()));
//        }
//        if(times==2){
//            File f = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/2.png");
//            Dice.setImage(new Image(f.toURI().toString()));
//        }
//        if(times==3){
//            File f = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/3.png");
//            Dice.setImage(new Image(f.toURI().toString()));
//        }
//        if(times==4){
//            File f = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/4.png");
//            Dice.setImage(new Image(f.toURI().toString()));
//        }
//        if(times==5){
//            File f = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/5.png");
//            Dice.setImage(new Image(f.toURI().toString()));
//        }
//        if(times==6){
//            File f = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/6.png");
//            Dice.setImage(new Image(f.toURI().toString()));
//        }
//        System.out.println("for dice value: "+times+" "+ p1.getHasStarted()+" "+p2.getHasStarted());
        if(p1.current.getId()==100){
            switchtoScene2(e);
        }
        if(p2.current.getId()==100){
            switchtoScene3(e);
        }
        die.rollDice(p1,p2,times-1);

    }
    public void roll(Player p, int r) throws IOException {
        if(p.current.getId()+r>100){
            return;
        }
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
            SequentialTransition current = p.move();
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
    }
    Image dice1,dice2,dice3,dice4,dice5,dice6;
    Dice die;
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
        p1 = new Player(goti1, true,t,red1, black1, Player1);
        p2 = new Player(goti2, false,t,red2, black2, Player2);
        Snakes_and_Ladders.add(new Ladder(alltiles.get(4),alltiles.get(16)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(6),alltiles.get(14)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(8),alltiles.get(12)));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(27),alltiles.get(13),true,true));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(25),alltiles.get(15),true,true));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(23),alltiles.get(17),true,true));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(32),alltiles.get(46)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(34),alltiles.get(44)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(36),alltiles.get(42)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(39),alltiles.get(80)));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(54),alltiles.get(33),true,false));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(56),alltiles.get(35),true,false));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(58),alltiles.get(37),true,false));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(67),alltiles.get(86)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(65),alltiles.get(84)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(63),alltiles.get(82)));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(90),alltiles.get(49),false,false));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(94),alltiles.get(73),true,false));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(96),alltiles.get(75),true,false));
//        Snakes_and_Ladders.add(new Snakes(alltiles.get(98),alltiles.get(77),true,false));

        File v1 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/1.png");
        File v2 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/2.png");
        File v3 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/3.png");
        File v4 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/4.png");
        File v5 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/5.png");
        File v6 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/6.png");
//        File v1 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/1.png");
//        File v2 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/2.png");
//        File v3 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/3.png");
//        File v4 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/4.png");
//        File v5 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/5.png");
//        File v6 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/6.png");
        die = new Dice(new Image(v1.toURI().toString()),new Image(v2.toURI().toString()),new Image(v3.toURI().toString()),new Image(v4.toURI().toString()),new Image(v5.toURI().toString()),new Image(v6.toURI().toString()));
    }
    class Dice{
        private ArrayList<Image> values = new ArrayList<Image>();
        Dice(Image img1,Image img2,Image img3,Image img4,Image img5,Image img6){
            this.values.add(img1);
            this.values.add(img2);
            this.values.add(img3);
            this.values.add(img4);
            this.values.add(img5);
            this.values.add(img6);
        }
        void rollDice(Player p1, Player p2, int value){
//            for(int i=0;i<6;i++) {
//                SequentialTransition seq = new SequentialTransition(Dice);
//                TranslateTransition stay = new TranslateTransition();
//                stay.setDuration(Duration.millis(500));
//                Dice.setImage(values.get(i));
//                seq.getChildren().add(stay);
//                seq.play();
//                System.out.println("DONE rolling: "+i);
//            }
            Thread thread = new Thread(){
                public void run(){
                    try {
                        for (int i = 0; i < 6; i++) {
                            Dice.setImage(values.get(i%6));
                            Thread.sleep(50);
                            if(i==5){
                                Dice.setImage(values.get(value));
                            }
                        }
                        int times = value+1;
                        Thread.sleep(100);
                        if(!p1.getHasStarted() && times==1 && p1.getTurn()){
                            p1.setHasStarted();
                            roll(p1,times);
                            p1.setTurn();
                            p2.setTurn();
                            if(p1.getTurn()){
                                p1.black.setOpacity(0);
                                p1.red.setOpacity(0.67);
                                p1.P.setTextFill(Paint.valueOf("white"));
                            }
                            else{
                                p1.black.setOpacity(1);
                                p1.red.setOpacity(0);
                                p1.P.setTextFill(Paint.valueOf("black"));
                            }
                            if(p2.getTurn()){
                                p2.black.setOpacity(0);
                                p2.red.setOpacity(0.67);
                                p2.P.setTextFill(Paint.valueOf("white"));
                            }
                            else{
                                p2.black.setOpacity(1);
                                p2.red.setOpacity(0);
                                p2.P.setTextFill(Paint.valueOf("black"));
                            }
                            return;
                        }
//        System.out.println("reached checkpoint 1");
                        if(!p2.getHasStarted() && times==1 && p2.getTurn()){
                            p2.setHasStarted();
                            roll(p2,times);
                            p2.setTurn();
                            p1.setTurn();
                            if(p1.getTurn()){
                                p1.black.setOpacity(0);
                                p1.red.setOpacity(0.67);
                                p1.P.setTextFill(Paint.valueOf("white"));
                            }
                            else{
                                p1.black.setOpacity(1);
                                p1.red.setOpacity(0);
                                p1.P.setTextFill(Paint.valueOf("black"));
                            }
                            if(p2.getTurn()){
                                p2.black.setOpacity(0);
                                p2.red.setOpacity(0.67);
                                p2.P.setTextFill(Paint.valueOf("white"));
                            }
                            else{
                                p2.black.setOpacity(1);
                                p2.red.setOpacity(0);
                                p2.P.setTextFill(Paint.valueOf("black"));
                            }
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
                        if(p1.getTurn()){
                            p1.black.setOpacity(0);
                            p1.red.setOpacity(0.67);
                            p1.P.setTextFill(Paint.valueOf("white"));
                        }
                        else{
                            p1.black.setOpacity(1);
                            p1.red.setOpacity(0);
                            p1.P.setTextFill(Paint.valueOf("black"));
                        }
                        if(p2.getTurn()){
                            p2.black.setOpacity(0);
                            p2.red.setOpacity(0.67);
                            p2.P.setTextFill(Paint.valueOf("white"));
                        }
                        else{
                            p2.black.setOpacity(1);
                            p2.red.setOpacity(0);
                            p2.P.setTextFill(Paint.valueOf("black"));
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }
    }
    class Tile{
        private final double x,y;
        private Tile next;
        private final int id;
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
        private Tile current;
        private ImageView img;
        private Rectangle red;
        private Rectangle black;
        private Label P;
        private boolean turn;
        private boolean hasStarted = false;
        public Player(ImageView img1, boolean f,Tile current, Rectangle red, Rectangle black, Label P){
            this.img = img1;
            this.turn = f;
            this.current = current;
            this.red = red;
            this.black = black;
            this.P = P;
        }
        double getX(){
            return this.current.getX();
        }
        double getY(){
            return this.current.getY();
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
        SequentialTransition move(){
            SequentialTransition seq = new SequentialTransition();
            if(current.next==null) return null;
//            if(current.getId()%10!=9) {
//                TranslateTransition first = new TranslateTransition();
//                first.setDuration(Duration.millis(125));
//                first.setByX((current.next.getX() - current.getX())/2);
//                first.setByY((current.next.getY() - current.getY())+15);
//                    seq.getChildren().add(first);
//                TranslateTransition second = new TranslateTransition();
//                second.setDuration(Duration.millis(125));
//                second.setByX((current.next.getX() - current.getX())/2);
//                second.setByY((current.next.getY() - current.getY())-15);
//
//                seq.getChildren().add(second);
//                current = current.next;
//                return seq;
//            }
//            TranslateTransition second = new TranslateTransition();
//            second.setDuration(Duration.millis(125));
//            second.setByX((current.next.getX() - current.getX()));
//            second.setByY((current.next.getY() - current.getY()));
//            seq.getChildren().add(second);
//            current = current.next;
            TranslateTransition movement = new TranslateTransition();
            if(current.next==null) return null;
            movement.setDuration(Duration.millis(250));
            System.out.println("current location "+current.getX()+" "+current.getY());
            movement.setByX(current.next.getX()-current.getX());
            movement.setByY(current.next.getY()-current.getY());
            current = current.next;
            System.out.println("next location "+current.getX()+" "+current.getY());
            seq.getChildren().add(movement);
            return seq;
//            System.out.println("next location "+current.getX()+" "+current.getY());
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
        private final Tile head;
        private final Tile tail;
        private final boolean zigzag;
        private final boolean sizethree;
        public Snakes(Tile H, Tile T, boolean zigzag,boolean sizethree){
            this.head = H;
            this.tail = T;
            this.zigzag = zigzag;
            this.sizethree=sizethree;
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
            if(zigzag && sizethree) return zigzagAnimationOfSize3(p);
            else if(zigzag) return zigzagAnimationOfSize6(p);
            return linearAnimation(p);
        }
        private SequentialTransition linearAnimation(Player p) {
            SequentialTransition completemovement = new SequentialTransition(p.getImage());
            TranslateTransition movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(15);
            movement.setByY(30);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-15);
            movement.setByY(60);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(15);
            movement.setByY(60);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-15);
            movement.setByY(30);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(10);
            movement.setByY(60);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-10);
            movement.setByY(60);
            completemovement.getChildren().add(movement);
            p.setTile(tail);
            return completemovement;
        }
        private SequentialTransition zigzagAnimationOfSize3(Player p) {
            SequentialTransition completemovement = new SequentialTransition(p.getImage());
            TranslateTransition movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-42);
            movement.setByY(30);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(+42);
            movement.setByY(30);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-21);
            movement.setByY(10);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-21);
            movement.setByY(-10);
            completemovement.getChildren().add(movement);
            p.setTile(tail);
            return completemovement;
        }
        private SequentialTransition zigzagAnimationOfSize6(Player p) {
            SequentialTransition completemovement = new SequentialTransition(p.getImage());
            double x= head.getX();
            double y = head.getY();
            TranslateTransition movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(42);
            movement.setByY(30);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(-42);
            movement.setByY(60);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByX(42);
            movement.setByY(10);
            completemovement.getChildren().add(movement);
            movement = new TranslateTransition();
            movement.setDuration(Duration.millis(200));
            movement.setByY(20);
            completemovement.getChildren().add(movement);
            p.setTile(tail);
            return completemovement;
        }
    }
    class Ladder implements SNL{
        private final Tile head;
        private final Tile tail;
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