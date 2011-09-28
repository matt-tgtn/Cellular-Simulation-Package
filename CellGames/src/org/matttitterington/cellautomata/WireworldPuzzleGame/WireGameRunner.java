package org.matttitterington.cellautomata.WireworldPuzzleGame;

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
		parent.puzzleRunner.performActions();
		
		currentWorld.syncToInternal();
		
		WireWorld newWorld = currentWorld.createNextGenerationWorld();
		
		this.currentWorld = null;
		this.currentWorld = newWorld;
		
		this.currentWorld.syncToGUI();
		
	}

	@Override
	protected Void doInBackground() throws Exception {
		parent.puzzleRunner.newRun();
		
		Timer timer = new Timer();
		timer.schedule(new PerformGameIteration(), 0, 1000 / parent.fps);
		
		while (!isCancelled()) {
			
		}
		
		timer.cancel();
		
		return null;
		
	}

	
	private class PerformGameIteration extends java.util.TimerTask {
		
		public void run() {
			
			step();
		}
	}

}
