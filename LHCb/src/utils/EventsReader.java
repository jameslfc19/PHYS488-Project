package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import exceptions.EventsReaderException;
import tracker.Event;

public class EventsReader {
	
	private HashMap<Integer,Event> events;
	
	private static EventsReader reader;
	private boolean init = false;
	
	public static void init() throws FileNotFoundException {
		reader = new EventsReader();
		reader.init = true;
	}
	
	private EventsReader() throws FileNotFoundException {
		events = new HashMap<Integer, Event>();
		
    	//Split CSV into each event using the 'Next...' keyword.
		Scanner scanner = new Scanner(new File("b0vectors1.csv"));
		
		//Splits the CSV into each event splitting around the 'Next' line.
        scanner.useDelimiter("\\s*Next...\\s*");
        
        int id = 0;
        //Loop over every event in the CSV.
        while(scanner.hasNext()){
        	id++;
        	//Split each event into their rows.
            Scanner event = new Scanner(scanner.next());
            event.useDelimiter("\n ");
            
            //Create array to store the data for this event.
            ArrayList<Double> data = new ArrayList<Double>();
            
            //Create event with 5 particles - B0, two muons and two pions.
            Event e = new Event(id,5);
            //System.out.println("Settings up event: "+id);
            
            //Read data from each row and create array for each event.
            while(event.hasNext()) {
            	String row = event.next();
            	String[] values = row.split("\\s*,\\s*");
            	for(String value : values) {
            		double v = toDouble(value);
            		if(!Double.isNaN(v)) data.add(v);
            	}
            }
            
            //Add data from CSV to event
        	e.setTruePos(data.get(0),data.get(1),data.get(2));
        	e.setParticleMomentum(0, data.get(6).intValue(), data.get(3), data.get(4), data.get(5));
        	e.setDecayTime(data.get(7));
        	e.setDecayLength(data.get(8));
        	
        	//Loop over remaining particles and add to event
        	int n = 9;      	
        	for(int i = 0; i <= 3; i++) {
        		e.setParticleMomentum(i+1, data.get(n+3).intValue(), data.get(n),data.get(n+1), data.get(n+2));
        		n = n + 4;
        	}
        	
        	e.setup();
   
        	//Add event to array and close scanner.
        	events.put(e.getId(), e);         
            event.close();
        }
        scanner.close();
	}
	
	//Method to convert string to double and catch any exception.
	private double toDouble(String s) {
		try { 
			return Double.valueOf(s);
		} catch(Exception e) { 
			System.err.println("Value: "+s+" was invalid!");
			return Double.NaN;
		}
	}
	
	public static Event getEvent(int id) throws EventsReaderException {
		if(!reader.init) throw new EventsReaderException();
		return reader.events.get(id);
	}
	
	public static HashMap<Integer,Event> getEvents() throws EventsReaderException{
		if(!reader.init) throw new EventsReaderException();
		return reader.events;
	}
	
	private static void resetEvents() {
		reader.events.values().forEach(event -> {
			event.setup();
		});
	}
	
}



