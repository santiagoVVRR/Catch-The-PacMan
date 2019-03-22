package model;

public class PacMan {
	
	public static final int ADVANCE = 5;
	
	private double radio;
	private double posX;
	private double posY;
	private int wait;
	private String direction;
	private int bounce;
	private boolean stoped;
	private Move state;
	
	public PacMan(double radio, double posX, double posY, int wait, String direction, int bounce, boolean stoped, Move state) {
		this.radio = radio;
		this.posX = posX;
		this.posY = posY;
		this.wait = wait;
		this.direction = direction;
		this.bounce = bounce;
		this.stoped = stoped;
		this.state = state;
	}

	
	
	public Move getState() {
		return state;
	}
	
	public void setState(Move state) {
		this.state = state;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getBounce() {
		return bounce;
	}

	public void setBounce(int bounce) {
		this.bounce = bounce;
	}

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}
	
	public void move(int max) {
		switch(state) {
		case RIGHT:
			if(posX+ADVANCE+radio>max) {
				state = Move.LEFT;
				posX = max-radio;
			}else {
				posX = posX+ADVANCE;
			}
			break;
		case LEFT:
			if(posX+ADVANCE+radio<0) {
				state = Move.RIGHT;
				posX = radio;
			}else {
				posX = posX-ADVANCE;
			}
			break;
		case UP:
			if(posY+ADVANCE+radio<max) {
				state = Move.DOWN;
				posY = max-radio;
			}else {
				posY = posY+ADVANCE;
			}
			break;
		case DOWN:
			if(posY+ADVANCE+radio<0) {
				state = Move.UP;
				posY = radio;
			}else {
				posY = posY-ADVANCE;
			}
			break;
		}
	}

	
	
	

}
