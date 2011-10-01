package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TutorialManager {
	
	final URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
	String parentDir;
	String imageDest;
	String normalDest;
	private final String folderName = "WireworldGameTutorial";
	
	public TutorialManager() {
		
		//Regular expression matches /something.jar so we can refer to folders in the same directory
		parentDir = location.getPath().replaceAll("/[^/]+\\.jar$", "/");
		
		
		normalDest = parentDir+folderName+"/";
		imageDest = parentDir+folderName+"/img/";
		
		
		
		
		//Check if tutorial has already been extracted
		boolean tutorialExists = new File(parentDir.concat(folderName)).exists();
		
		if (tutorialExists) {
			System.out.println("Tutorial already exists");
			openTutorial();
		}
		else {
			unpackTutorial();
			openTutorial();
		}
		
		
	}
	
	private void unpackTutorial() {
		
		//Make the directories we need
		new File(normalDest).mkdir();
		new File(imageDest).mkdir();
		
		try {
			JarFile jar = new JarFile(location.getFile());
			Enumeration<JarEntry> entries = jar.entries();
			
			while (entries.hasMoreElements()) {
				process(entries.nextElement(), jar);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	private void openTutorial() {
		
		String tutDir = this.parentDir.concat(folderName+"/tutorial.html").replaceFirst("/", "");
		tutDir.replaceAll("/", File.pathSeparator);
		System.out.println("Opening tutorial at "+tutDir);
		OpenBrowser.openURL(tutDir);
	}
	
	private void process(JarEntry entry, JarFile jar) {
		
		String name = entry.getName();
		String[] bits = name.split("/");
		String fileName = bits[bits.length-1];
		
		//Create the input stream from the jar file
		InputStream input = null;
		try {
			input = jar.getInputStream(entry);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Create the output stream to the correct destination
		FileOutputStream output = null;
		
		if (matchesType(fileName, "png")) {
			try {
				output = new FileOutputStream(new File(this.imageDest + fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (matchesType(fileName, "html")) {
			try {
				output = new FileOutputStream(new File(this.normalDest + fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		//Output the data
		try {
			
			
			while (input.available() > 0 && output != null){
				output.write(input.read());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			input.close();
			if (output != null) {
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private boolean matchesType(String name, String type) {
		String regex = "[^/]+\\."+type+"$";
		
		if (name.matches(regex)) {
			return true;
		}
		
		return false;
	}
}
