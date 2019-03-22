package threads;

import model.PacMan;
import ui.ControllerClass;


public class MoveThread extends Thread{
	
	private ControllerClass pc;
	private PacMan pacman;

	public MoveThread(ControllerClass pc, PacMan pacman){
		this.pc = pc;
		this.pacman = pacman;
	}
	
	@Override
	public void run() {
		
		while(true) {
				pacman.move((int)pc.getWidth());
				try {
					sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
	
}