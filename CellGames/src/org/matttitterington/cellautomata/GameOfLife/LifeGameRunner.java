package org.matttitterington.cellautomata.GameOfLife;

import java.util.Timer;

import javax.swing.SwingWorker;

public class LifeGameRunner extends SwingWorker<Void, Void>{
	
	GameInterface parent;
	LifeWorld currentWorld;

	public LifeGameRunner(GameInterface gameInterface) {
		this.parent = gameInterface;
		this.currentWorld = new LifeWorld(this.parent);
		this.currentWorld.syncToInternal();
	}

	public void step() {
		currentWorld.syncToInternal();
		
		LifeWorld newWorld = currentWorld.createNextGenerationWorld();
		
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
		LifeGameRunner parent;
		
		public PerformGameIteration(LifeGameRunner parent) {
			this.parent = parent;
		}
		
		public void run() {
			LifeWorld newWorld = currentWorld.createNextGenerationWorld();
			
			parent.currentWorld = null;
			parent.currentWorld = newWorld;
		
			parent.currentWorld.syncToGUI();
		}
	}

}
