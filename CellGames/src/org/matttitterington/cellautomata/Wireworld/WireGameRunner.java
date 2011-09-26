package org.matttitterington.cellautomata.Wireworld;

import java.util.Timer;

import javax.swing.SwingWorker;

public class WireGameRunner extends SwingWorker<Void, Void>{
	
	GameInterface parent;
	WireWorld currentWorld;

	public WireGameRunner(GameInterface gameInterface) {
		this.parent = gameInterface;
		this.currentWorld = new WireWorld(this.parent);
		this.currentWorld.syncToInternal();
	}

	public void step() {
		currentWorld.syncToInternal();
		
		WireWorld newWorld = currentWorld.createNextGenerationWorld();
		
		this.currentWorld = null;
		this.currentWorld = newWorld;
		
		this.currentWorld.syncToGUI();
		
	}

	@Override
	protected Void doInBackground() throws Exception {
		currentWorld.syncToInternal();
		
		Timer timer = new Timer();
		timer.schedule(new PerformGameIteration(this), 0, 1000 / parent.fps);
		
		while (!isCancelled()) {
			
		}
		
		timer.cancel();
		
		return null;
		
	}

	
	private class PerformGameIteration extends java.util.TimerTask {
		WireGameRunner parent;
		
		public PerformGameIteration(WireGameRunner parent) {
			this.parent = parent;
		}
		
		public void run() {
			WireWorld newWorld = currentWorld.createNextGenerationWorld();
			
			parent.currentWorld = null;
			parent.currentWorld = newWorld;
		
			parent.currentWorld.syncToGUI();
		}
	}

}
