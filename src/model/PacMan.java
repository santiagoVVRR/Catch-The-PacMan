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
	
	private int angle;
	private int length;
	private boolean keep;
	
	public PacMan(double radio, double posX, double posY, int wait, String direction, int bounce, boolean stoped, Move state) {
		this.radio = radio;
		this.posX = posX;
		this.posY = posY;
		this.wait = wait;
		this.direction = direction;
		this.bounce = bounce;
		this.stoped = stoped;
		this.state = state;
		
		keep = true;
		length = 270;
	}

	
	
	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isKeep() {
		return keep;
	}

	public void setKeep(boolean keep) {
		this.keep = keep;
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
	
	public void move(int max, int maxY) {
		switch(state) {
			case RIGHT:
				angle = 0;
				mouth();
				if(posX+ADVANCE+radio<max) {

					posX = posX+ADVANCE;
				}else {
					state = Move.LEFT;
					posX = max-radio;					
				}
			break;
			case LEFT:
				angle = 180;
				mouth();
				if(posX-ADVANCE-radio>0) {
					posX = posX-ADVANCE;
				}else {
					state = Move.RIGHT;
					posX = radio;
				}
			break;
			case UP:
				angle = 270;
				mouth();
				if(posY-ADVANCE-radio>0) {
					posY = posY-ADVANCE;
				}else {
					state = Move.DOWN;
					posY = radio;
				}
			break;
			case DOWN:
				angle = 90;
				mouth();
				if(posY+ADVANCE+radio<maxY) {

					posY = posY+ADVANCE;
				}else {
					state = Move.UP;
					posY = maxY-radio;					
				}
			break;
		}
	}
	
	public void mouth() {
		if(keep) {
			length = length+4;
			//angle = angle+2;
		}
		if(length>=360) {
			keep = false;
		}
		
		if(!keep) {
			length = length-4;
			//angle = angle-2;
		}
		if(length<270) {
			keep = true;
		}
	}
	
	
	

}
