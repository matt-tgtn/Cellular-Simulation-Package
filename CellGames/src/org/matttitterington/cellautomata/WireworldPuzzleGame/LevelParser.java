package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LevelParser extends DefaultHandler{
	
	private ArrayList<Level> myLevels;
	
	private String tempVal;
	private Level tempLev;
	
	public LevelParser () {
		this.myLevels = new ArrayList<Level>();
	}
	
	private void parseLevels(String source) {
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		
		try {
			
			SAXParser sp = spf.newSAXParser();
			
			//Make sure we parse the correct level data file
			sp.parse(source, this);
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//Parser event handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		
		if (qName.equalsIgnoreCase("Level")) {
			//create new level object
			tempLev = new Level();
			tempLev.setID(Integer.parseInt(attributes.getValue("id")));
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start, length);
	}
	
	public void endElement (String uri, String localName, String qName) throws SAXException {
		
		//If it is a level end tag
		if (qName.equalsIgnoreCase("Level")){
			//Add it to the list
			myLevels.add(tempLev);
		}
		else if (qName.equalsIgnoreCase("inputA")) {
			tempLev.setInputA(tempVal);
		}
		else if (qName.equalsIgnoreCase("inputB")) {
			tempLev.setInputB(tempVal);
		}
		else if (qName.equalsIgnoreCase("output")) {
			tempLev.setExpectedOutput(tempVal);
		}
		else if (qName.equalsIgnoreCase("electrons")) {
			tempLev.setElectronAllowance(Integer.parseInt(tempVal));
		}
		else if (qName.equalsIgnoreCase("levelsummary")) {
			tempLev.setLevelSummary(tempVal);
		}
		else if (qName.equalsIgnoreCase("leveldescription")) {
			tempLev.setLevelDescription(tempVal);
		}
		else if (qName.equalsIgnoreCase("levelHint")) {
			tempLev.setLevelHint(tempVal);
		}
		else if (qName.equalsIgnoreCase("password")) {
			tempLev.setLevelPassword(tempVal);
		}
	}
	
	public Level getLevel(int requestedLevel) {
		
		//See if the requested level is currently loaded
		if (myLevels.isEmpty()) {
			//Parse the document containing the correct number of levels
			if ((requestedLevel > 0)&&(requestedLevel <= 20)) {
				this.parseLevels("levels/levels1-20.xml");
			}
		}
		
		
		
		
		for (Level level : myLevels) {
			if (level.getID() == requestedLevel) {
				return level;
			}
		}
		
		throw new Error("Level not found");
		
	}
}





















