package org.matttitterington.cellautomata.LangtonsAnt;

import java.util.Timer;

import javax.swing.SwingWorker;

public class AntGameRunner extends SwingWorker<Void, Void>{
	
	GameInterface parent;

	public AntGameRunner(GameInterface gameInterface) {
		this.parent = gameInterface;
	}

	public void step() {
		this.parent.ant.syncAnt();
		this.parent.ant.performOneIteration();
		this.parent.frameLabel.setText("Frame: "+this.parent.ant.frame);
		
	}

	@Override
	protected Void doInBackground() throws Exception {
		this.parent.ant.syncAnt();
		
		Timer timer = new Timer();
		timer.schedule(new PerformGameIteration(this), 0, 1000 / this.parent.fps);
		
		while (!isCancelled()) {
			
		}
		
		timer.cancel();
		
		return null;
		
	}
	
	private class PerformGameIteration extends java.util.TimerTask {
		AntGameRunner parent;
		
		public PerformGameIteration(AntGameRunner parent) {
			this.parent = parent;
		}
		
		public void run() {
			this.parent.parent.ant.performOneIteration();
			this.parent.parent.frameLabel.setText("Frame: "+this.parent.parent.ant.frame);
		}
	}

}
