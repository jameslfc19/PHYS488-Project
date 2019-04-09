package simulations.abstracts;

import java.util.ArrayList;
import java.util.concurrent.Future;

import org.apache.commons.math3.linear.RealVector;

import exceptions.EventsReaderException;
import tracker.Event;
import tracker.EventSimulation;
import utils.EventsReader;
import utils.GraphValues;

public abstract class SimpleSimulation extends Simulation {
	
	protected int eventId;
	private int repeatMeasurements;
	private int separateMeasurements;

	public SimpleSimulation(String name, int eventId, int repeatMeasurements, int separateMeasurements)
			throws Exception {
		super(name);
		this.eventId = eventId;
		this.repeatMeasurements = repeatMeasurements;
		this.separateMeasurements = separateMeasurements;
		eventSims = new ArrayList<Future<EventSimulation>>();
	}
	
	@Override
	public void start() throws Exception {
		this.simStarted = true;
		
		for(int s = 0; s < separateMeasurements; s++) {
			for(int r = 0; r < repeatMeasurements; r++) {
				n = 0;
				//System.out.println("Loop "+currentLoop);
				
				//Loop over every event
				Event event = EventsReader.getEvent(eventId);
				
				event.setup();
				Future<EventSimulation> f = service.submit(() -> {
					n++;
					//Print out simulation progress.
					//if(n % 250 == 0)
					//System.out.println("Simulating event "+n+"/"+EventsReader.getEvents().size());
						
					return EventLoop(event.getId(),currentLoop);
				});
							
				eventSims.add(f);
				
				//Hold until complete.
				currentLoop++;
			}
			while(!allTasksComplete(eventSims));
			postSimulation(s);
			System.out.println("Completed Loop "+(s+1)+" of repeated measurements.");
		}
		
		postSimulationLoop();
		System.out.println("Simulation Finished");
		eventSims = new ArrayList<Future<EventSimulation>>();
	}
	
	@Override
	public EventSimulation EventLoop(int eventId, int currentLoop) throws Exception {
		int i = currentLoop/repeatMeasurements;
		CurrentLoop(i);
		return null;
	}
	
	public abstract void CurrentLoop(int n) throws Exception;

	@Override
	public double calculateHistogramValue(RealVector point, EventSimulation sim) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}