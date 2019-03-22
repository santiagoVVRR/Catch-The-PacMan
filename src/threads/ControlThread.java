package threads;

import javafx.application.Platform;
import ui.ControllerClass;

public class ControlThread extends Thread{
	
	private ControllerClass pcc;
	
	public ControlThread(ControllerClass pcc) {
		this.pcc = pcc;
	}
	
	public void run() {
		while (true) {
			PacManThread pt = new PacManThread(pcc);
			Platform.runLater(pt);

			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
