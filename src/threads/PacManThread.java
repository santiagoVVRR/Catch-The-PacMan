package threads;

import ui.ControllerClass;


public class PacManThread extends Thread{
	
	private ControllerClass pc;

	public PacManThread(ControllerClass pc){
		this.pc = pc;
	}
	
	@Override
	public void run() {
		pc.update();
	}
	
}