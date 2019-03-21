package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.StageStyle;
import model.PacMan;
import threads.PacThreads;

public class ControllerClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menu;

    @FXML
    private Menu file;

    @FXML
    private MenuItem nGame1;

    @FXML
    private MenuItem lGame2;

    @FXML
    private MenuItem sGame3;

    @FXML
    private Pane pane;

    @FXML
    private Label lbScore;

    @FXML
    private Label points;
    
    private ArrayList<PacMan> jim;
    
    private PacMan jimmy;
    
    private Arc pac;
    
    private int Levels;
    
    private boolean move;
    
    private int wait;
    
    private PacThreads pt;
    
    private static double ballSpeed = 4;

    @FXML
    void about(ActionEvent event) {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(" INSTRUCTIONS \n\n"+
    	" Choose a level of dificulty in the ''New Game'' option\n"+
    	" Click on the Pac-Mans before they bounce on the walls "+
    	" The idea of this game is not to let the Pac-Man's bounce to many times"+
    	" so you can be in the Top Scores"+
    	" \n\ngood luck bitches");
    	info.show();
    }
    
    @FXML
    void close(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void level1(ActionEvent event) {
    	setLevels(1);
    	String filePath = "./levels/level1.txt";
    	loadGame(filePath);
    	startGame();
    	move();
    	
    	pt = new PacThreads(this,move,wait);
    	pt.start();
    	
    }

    @FXML
    void level2(ActionEvent event) {
    	
    }

    @FXML
    void level3(ActionEvent event) {

    }
    
    @FXML
    void saveGame(ActionEvent event) {

    }

    @FXML
    void topScores(ActionEvent event) {

    }

    public void loadGame(String path) {
    	double radio;
    	double posX;
    	double posY;
    	int waitt;
    	String direction;
    	int bounces;
    	boolean stoped;
    	
    	try {
    		FileReader level = new FileReader(path);
    		BufferedReader br = new BufferedReader(level);
    		String line = br.readLine();
    		while(line != null) {
    			String[] var = line.split("\t");
    			radio = Integer.parseInt(var[0]);
    			posX = Integer.parseInt(var[1]);
    			posY = Integer.parseInt(var[2]);
    			waitt = Integer.parseInt(var[3]);
    			direction = var[4];
    			bounces = Integer.parseInt(var[5]);
    			if(var[6] == "false") {
    				stoped = false;
    			}else {
    				stoped = true;
    			}
    			
    			jimmy = new PacMan(radio,posX,posY,waitt,direction,bounces,stoped);
    			jim.add(jimmy);
    			line = br.readLine();
    		}
    		br.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void startGame() {
    	for(int i = 0; i < jim.size(); i++) {
    		
    		pac = new Arc(jim.get(i).getPosX(),jim.get(i).getPosX(),jim.get(i).getRadio(),jim.get(i).getRadio(),32,300);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    		pane.getChildren().add(pac);
    		
    	/**
    		
    		AnimationTimer ani = new AnimationTimer() {

				@Override
				public void handle(long arg0) {
					for(int j = 0; j < jim.size(); j++)
						if(jim.get(j).getDirection().equals("IZQUIERDA")) {
							pac.setTranslateX(pac.getTranslateX() + ballSpeed);
							ballSpeed *= -1;
							if(pac.getTranslateX() < 0 || pac.getTranslateX() > pane.getLayoutX()) {
								ballSpeed *= -1;
							}
						}else if(jim.get(j).getDirection().equals("DERECHA")) {
							pac.setTranslateX(pac.getTranslateX() + ballSpeed);
							if(pac.getTranslateX() < 0 || pac.getTranslateX() > pane.getLayoutX()) {
								ballSpeed *= -1;
							}
						}
					
					
					
				}
    			
    		};
    		ani.start();
    		*/
    	}
    }
    
    public boolean openMouth() {
    	boolean m = false;
    	if(m) {
    		pac.setLength(pac.getLength()-5);
    		pac.setStartAngle(pac.getStartAngle()+3);
    	}
    	if(pac.getLength()<270) {
    		m = true;
    	}
		return m;
    }
    
    public boolean closeMouth() {
    	boolean m = false;
    	if(!m) {
    		pac.setLength(pac.getLength()+5);
    		pac.setStartAngle(pac.getStartAngle()-3);
    	}
    	if(pac.getLength()>=360) {
    		m = false;
    	}
		return m;
    }
    
   public void move() {
	   		//if(move) {
		   AnimationTimer ani = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				for(int i = 0; i<jim.size(); i++) {
					if(jim.get(i).getDirection().equals("DERECHA")) {
						 //move = true;
						   if(pac.getTranslateX() < 0 || pac.getTranslateX() > pane.getLayoutY()) {
							   ballSpeed *= -1;
							   pac.setTranslateX(pac.getTranslateX() + ballSpeed);						  
							   
						   }
					}else if(jim.get(i).getDirection().equals("IZQUIERDA")) {
					//	move = true;
						 if(pac.getTranslateX() < 0 || pac.getTranslateX() > pane.getLayoutY()) {
							 ballSpeed = 4;
							 pac.setTranslateX(pac.getTranslateX() + ballSpeed);						 
							
						 }
					}else if(jim.get(i).getDirection().equals("ARRIBA")) {
					//	 move = true;
						if(pac.getTranslateY() < 0 || pac.getTranslateY() > pane.getLayoutX()) {
							ballSpeed *= -1;
							 pac.setTranslateY(pac.getTranslateY() + ballSpeed);						
							 
						}
					}else if(jim.get(i).getDirection().equals("ABAJO")) {
					//	 move = true;
						if(pac.getTranslateY() < 0 || pac.getTranslateY() > pane.getLayoutX()) {
							ballSpeed = 4;
							 pac.setTranslateY(pac.getTranslateY() + ballSpeed);
							 
						}
					}
				}
		   };
		   
	   };
	   ani.start();
	   //}
	   //	return move;
   }
    
    public int getLevels() {
		return Levels;
	}

	public void setLevels(int levels) {
		Levels = levels;
	}

	@FXML
    void initialize() {
    
    	jim = new ArrayList<PacMan>();
        
       
    }
}

