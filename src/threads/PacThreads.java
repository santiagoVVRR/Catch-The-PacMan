package threads;

import ui.ControllerClass;

public class PacThreads extends Thread{

	private ControllerClass control;
	private boolean stop;
	private boolean sprite;
	private int wait;
	
	public PacThreads(ControllerClass control, boolean stop,  int wait) {
		this.control = control;
		this.stop = true;
		this.sprite = true;
		this.wait = wait;
	}
	
	@Override
	public void run() {
		while(stop) {
			sprite = control.openMouth();
			sprite = control.closeMouth();
			control.move();
			try {
				sleep(wait);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
}
