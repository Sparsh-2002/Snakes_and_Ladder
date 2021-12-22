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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView goti1;
    @FXML
    private ImageView goti2;
    @FXML
    private ImageView arrow;
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;
    @FXML
    private Button rollButton;
    private Dice die;
    private Player p1,p2;
    private static final ArrayList<SNL> Snakes_and_Ladders = new ArrayList<SNL>();
    private final ArrayList<Tile> alltiles = new ArrayList<Tile>();
    private Stage stage;
    private Scene scene;

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
    public void move(ActionEvent e) throws IOException {
        if(rollButton.isDisabled()){
            return;
        }
        int times = (int)(Math.random()*6+1);
        if(p1.getTurn()){
            p2.off();
        }
        if(p2.getTurn()){
            p1.off();
        }
        die.rollDice(p1,p2,times-1, arrow);
//        System.out.println(p1.getTile().getId());
//        System.out.println(p2.getTile().getId());
        if(p1.getTile().getId()==100){
            switchtoScene2(e);
        }
        if(p2.getTile().getId()==100){
            switchtoScene3(e);
        }
    }
    public static void roll(Player p, int r, ImageView temp) throws IOException {
        if(p.getTile().getId()+r>100){
            return;
        }
        SequentialTransition movement = new SequentialTransition(p.getImage());
        boolean checker = false;
        for(int i=0;i<r;i++){
            SequentialTransition current = p.move();
            if(current==null) {
                checker = true;
                break;
            }
            else movement.getChildren().add(current);
        }
        for(SNL t: Snakes_and_Ladders){
            if(t.getHead()==p.getTile()){
                movement.getChildren().add(t.animate(p));
                break;
            }
        }
        if(!checker) {
            movement.play();
        }
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
        File player1on = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/player1on.jpeg");
        File player1off = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/player1off.jpeg");
        File player2on = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/player2on.jpeg");
        File player2off = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/player2off.jpeg");
        Tile t = new Tile(0.0,60.0,0);
        t.setNextTile(alltiles.get(0));
        p1 = new Player(goti1,new Image(player1on.toURI().toString()),new Image(player1off.toURI().toString()),player1, true,t,1);
        p2 = new Player(goti2,new Image(player2on.toURI().toString()),new Image(player2off.toURI().toString()),player2, false,t,2);
        Snakes_and_Ladders.add(new Ladder(alltiles.get(4),alltiles.get(16)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(6),alltiles.get(14)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(8),alltiles.get(12)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(27),alltiles.get(13),true,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(25),alltiles.get(15),true,true));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(23),alltiles.get(17),true,true));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(32),alltiles.get(46)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(34),alltiles.get(44)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(36),alltiles.get(42)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(39),alltiles.get(80)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(54),alltiles.get(33),true,false));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(56),alltiles.get(35),true,false));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(58),alltiles.get(37),true,false));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(67),alltiles.get(86)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(65),alltiles.get(84)));
        Snakes_and_Ladders.add(new Ladder(alltiles.get(63),alltiles.get(82)));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(90),alltiles.get(49),false,false));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(94),alltiles.get(73),true,false));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(96),alltiles.get(75),true,false));
        Snakes_and_Ladders.add(new Snakes(alltiles.get(98),alltiles.get(77),true,false));

//        File v1 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/1.png");
//        File v2 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/2.png");
//        File v3 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/3.png");
//        File v4 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/4.png");
//        File v5 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/5.png");
//        File v6 = new File("/Users/sandeepsehgal/IdeaProjects/demo/src/main/resources/com/example/demo/6.png");
        File v1 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/1.png");
        File v2 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/2.png");
        File v3 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/3.png");
        File v4 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/4.png");
        File v5 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/5.png");
        File v6 = new File("D:/coding/javafx/ap/AP_project-main/demo/src/main/resources/com/example/demo/6.png");
        die = new Dice(new Image(v1.toURI().toString()),new Image(v2.toURI().toString()),new Image(v3.toURI().toString()),new Image(v4.toURI().toString()),new Image(v5.toURI().toString()),new Image(v6.toURI().toString()),Dice,rollButton);
    }
}
class Dice{
    private final ArrayList<Image> values = new ArrayList<Image>();
    private final ImageView Dice;
    private Button control;
    Dice(Image img1,Image img2,Image img3,Image img4,Image img5,Image img6, ImageView d,Button control){
        this.values.add(img1);
        this.values.add(img2);
        this.values.add(img3);
        this.values.add(img4);
        this.values.add(img5);
        this.values.add(img6);
        this.Dice = d;
        this.control = control;
    }
    void rollDice(Player p1, Player p2, int value, ImageView arrow){
        Thread thread = new Thread(){
            public void run(){
                try {
                    control.setDisable(true);
                    arrow.setOpacity(0);
                    for (int i = 0; i < 12; i++) {
                        Dice.setImage(values.get(i%6));
                        Thread.sleep(50);
                        if(i==11){
                            Dice.setImage(values.get(value));
                        }
                    }
                    int times = value+1;
                    Thread.sleep(200);
                    if(!p1.getHasStarted() && times==1 && p1.getTurn()){
                        p1.setHasStarted();
                        HelloController.roll(p1,times,arrow);
                        p1.setTurn();
                        p2.setTurn();
                        if(p1.getTurn()){
                            p1.on();
                        }
                        else{
                            p1.off();
                        }
                        if(p2.getTurn()){
                            p2.on();
                        }
                        else{
                            p2.off();
                        }
                        arrow.setOpacity(1);
                        control.setDisable(false);
                        return;
                    }
                    if(!p2.getHasStarted() && times==1 && p2.getTurn()){
                        p2.setHasStarted();
                        HelloController.roll(p2,times,arrow);
                        p2.setTurn();
                        p1.setTurn();
                        if(p1.getTurn()){
                            p1.on();
                        }
                        else{
                            p1.off();
                        }
                        if(p2.getTurn()){
                            p2.on();
                        }
                        else{
                            p2.off();
                        }
                        arrow.setOpacity(1);
                        control.setDisable(false);
                        return;
                    }
                    if(p1.getTurn() && p1.getHasStarted()){
                        HelloController.roll(p1,times,arrow);
                    }
                    else if(p2.getTurn() && p2.getHasStarted()){
                        HelloController.roll(p2,times,arrow);
                    }
                    p1.setTurn();
                    p2.setTurn();
                    if(p1.getTurn()){
                        p1.on();
                    }
                    else{
                        p1.off();
                    }
                    if(p2.getTurn()){
                        p2.on();
                    }
                    else{
                        p2.off();
                    }
                    arrow.setOpacity(1);
                    control.setDisable(false);
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
    private final ImageView img;
    private final Image on;
    private final Image off;
    private final ImageView status;
    private final int ID;
    private boolean turn;
    private boolean hasStarted = false;
    public Player(ImageView img1, Image on, Image off, ImageView status,boolean f,Tile current,int ID){
        this.status = status;
        this.on= on;
        this.off=off;
        this.img = img1;
        this.turn = f;
        this.current = current;
        this.ID = ID;
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
        if(current.getNextTile()==null) return null;
        TranslateTransition movement = new TranslateTransition();
        if(current.getNextTile()==null) return null;
        movement.setDuration(Duration.millis(250));
        movement.setByX(current.getNextTile().getX()-current.getX());
        movement.setByY(current.getNextTile().getY()-current.getY());
        current = current.getNextTile();
        seq.getChildren().add(movement);
        return seq;
    }
    public ImageView getImage() {
        return this.img;
    }
    public Tile getTile() { return this.current;}
    public void setTile(Tile next){
        current = next;
    }
    public void on(){
        this.status.setImage(on);
    }
    public void off(){
        this.status.setImage(off);
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
        movement.setByX(-42.5);
        movement.setByY(30);
        completemovement.getChildren().add(movement);
        movement = new TranslateTransition();
        movement.setDuration(Duration.millis(200));
        movement.setByX(+42.5);
        movement.setByY(30);
        completemovement.getChildren().add(movement);
        movement = new TranslateTransition();
        movement.setDuration(Duration.millis(200));
        movement.setByX(-21.25);
        movement.setByY(10);
        completemovement.getChildren().add(movement);
        movement = new TranslateTransition();
        movement.setDuration(Duration.millis(200));
        movement.setByX(-21.25);
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
        movement.setByX(42.5);
        movement.setByY(30);
        completemovement.getChildren().add(movement);
        movement = new TranslateTransition();
        movement.setDuration(Duration.millis(200));
        movement.setByX(-42.5);
        movement.setByY(60);
        completemovement.getChildren().add(movement);
        movement = new TranslateTransition();
        movement.setDuration(Duration.millis(200));
        movement.setByX(42.5);
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
class Game{
    ArrayList<Tile> Alltiles;
    ArrayList<SNL> Snakes_and_Ladders;
    Player player_1;
    Player player_2;
    Dice dice;
    public Game(ArrayList arr,ArrayList arr1, Player a, Player b, Dice d){
        this.Alltiles = arr;
        this.Snakes_and_Ladders = arr1;
        this.player_1 = a;
        this.player_2 = b;
        this.dice = d;
    }
}