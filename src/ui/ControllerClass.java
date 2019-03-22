package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Move;
import model.PacMan;
import model.Score;
import threads.ControlThread;
import threads.MoveThread;

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
    private Pane pane;

    @FXML
    private Label lbScore;

    @FXML
    private Label points;
    
    private ArrayList<PacMan> jim;
    
    private ArrayList<Arc> arcPa;
    
    private PacMan jimmy;
    
    private Arc pac;
    
    private Stage stage;
    
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
    void level1(ActionEvent event) throws IOException {
    	
    	String filePath = "levels/level1.txt";
    	loadGame(filePath);
    	
    	
    }

    @FXML
    void level2(ActionEvent event) throws IOException {
    	
    	String filePath = "./levels/level2.txt";
    	loadGame(filePath);
    	
    }

    @FXML
    void level3(ActionEvent event) throws IOException {
    	
    	String filePath = "./levels/level3.txt";
    	loadGame(filePath);
    	
    }
    
    @FXML
    void saveGame(ActionEvent event) throws FileNotFoundException, IOException {
    	ObjectOutputStream typing = new ObjectOutputStream(new FileOutputStream("levels/scores.txt"));
    	typing.writeObject(points);
    	typing.close();
    }

    @FXML
    void topScores(ActionEvent event) throws IOException {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("Top Scores");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	
    	File f = new File("levels/topScores.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		String txt = "";
		while(line != null) {
			String[] parts = line.split("-");
			
			String name = parts[0];
			int score = Integer.parseInt(parts[1]);
			
			Score s = new Score(name, score);
			txt = txt+s+"\n";
			
			line = br.readLine();
		}
		
		br.close();
		fr.close();
    	
    	info.setContentText(txt);
    	info.show();
    }
    

    public void loadGame(String path) {
    	double radio = 0;
    	double posX = 0;
    	double posY = 0;
    	int waitt = 0;
    	String direction = "";
    	int bounces = 0;
    	boolean stoped = false;
    	
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
    			stoped = Boolean.parseBoolean(var[6]);
    			
    			Move move = null;
    			if(direction.equals("DERECHA")) {
    				move = Move.RIGHT;
    			}else if(direction.equals("IZQUIERDA")) {
    				move = Move.LEFT;
    			}else if(direction.equals("ARRIBA")) {
    				move = Move.UP;
    			}else if(direction.equals("ABAJO")) {
    				move = Move.DOWN;
    			}
    			
    			if(var[6] == "false") {
    				stoped = false;
    			}else {
    				stoped = true;
    			}
    			
    			jimmy = new PacMan(radio,posX,posY,waitt,direction,bounces,stoped,move);
    			jim.add(jimmy);
    			line = br.readLine();
    		}
    		br.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	for(int i = 0; i < jim.size(); i++) {
    		pac = new Arc(jim.get(i).getPosX(), jim.get(i).getPosY(), jim.get(i).getRadio(),jim.get(i).getRadio(),32,300);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    		pane.getChildren().add(pac);
    		arcPa.add(pac);
    		
    		MoveThread ua = new MoveThread(this,  jim.get(i));
    		ua.setDaemon(true);
    		ua.start();
    	}
    }
      
    public void update() {
    	for(int i = 0; i < arcPa.size(); i++) {
    		arcPa.get(i).setLayoutX(jim.get(i).getPosX());
    		arcPa.get(i).setLayoutY(jim.get(i).getPosY());
    		
    	}
    }
    
    public double getWidth() {
    	return stage.getScene().getWidth();
    }
    
    public double geTHeigth() {
		return stage.getScene().getHeight();
	}
    
    public void setStage(Stage g) {
    	stage = g;
    }
    
 

	@FXML
    void initialize() {
    
    	jim = new ArrayList<PacMan>();
        arcPa = new ArrayList<Arc>();
        ControlThread pacmt = new ControlThread(this);
        pacmt.setDaemon(true);
        pacmt.start();
       
    }
}

