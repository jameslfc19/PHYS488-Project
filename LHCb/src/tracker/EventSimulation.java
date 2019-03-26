package tracker;

import java.io.*;
import java.util.Scanner;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class EventSimulation {   
    static Random randGen = new Random();
    static Scanner keyboard = new Scanner(System.in);

    // maximum allowed number of steps before simulation is aborted
    static final int numSteps = 10000;
    static final double simTime = 1e-8;
    
    public ArrayList<ArrayList<RealVector>> detections;
    public Particle [] Particles_sim;
    
    public EventSimulation(Particle[] particles) throws IOException {
    	detections = new ArrayList<ArrayList<RealVector>>();
    	
    	//randGen.setSeed(7894694);
    	
    	// Define the genotrical properties of the experiment
    	Geometry Experiment = SetupExperiment();
    	
    	Particles_sim = new Particle[particles.length];
 	    
 	    for (int ip = 0; ip < particles.length; ip++) {
	 		// create an instance of the particle tracker
	 		// usage: particleTracker(particle, time to track (s), number of time steps)
	 		particleTracker tracker = new particleTracker(particles[ip], simTime, numSteps);
	
	 		// some output (need to disable before running large numbers of events!)
	 		System.out.println("Simulating particle " + ip);
	 		//particles[ip].print();
	
	 		Particles_sim[ip] = tracker.track(Experiment);
 	    }
 	    
 	   Particles_sim[0].DumpTXYZPxPyPz("outputs/sample.csv");
 	    
 	    // simulate detection of each particle in each element
	    Particle [] Particles_det = Experiment.detectParticles(Particles_sim);
	    
	    for(int j =0; j<Particles_det.length; j++) {
	    	Particle p = Particles_det[j];
	    	ArrayList<RealVector> detection = new ArrayList<RealVector>();
	    	System.out.println(p.getParticleName()+p.getCharge());
	    	for(int i = 1; i < 20; i++) {
	    		double[] pos = p.getPosition(i);
	    		if(pos[0] != 0) detection.add(new ArrayRealVector(new double[] {pos[1],pos[2],pos[3]}));
	    		System.out.println(Arrays.toString(p.getPosition(i)));
	    	}
	    	detections.add(detection);
	    }
    }
    

    public static double smear(double resolution) {
    	return randGen.nextGaussian()*resolution;
    }

    
    public static Geometry SetupExperiment ()
    {
	// example setup the experiment:
	// * first line defines the size of the experiment in vacuum
	// * then add one block of iron: 0.3x0.3 m^2 wide in x,y-direction, ironThickness cm thick in z-direction
	// * then add two thin (1mm) "silicon detectors" 10cm and 20cm after the iron block

	Geometry Experiment = new Geometry(randGen, 0.0000001);
	
	
	
	int detectors = 19;
	double detectorThickness = 0.0003;
	double spacing = 0.03;
	
//	Experiment.AddDisk(0, 0, 0,
//		     0.1, 0, 100*(detectorThickness+spacing),
//		     0,0,0);
	
	Experiment.AddCuboid(-0.024, -0.024, 0, 0.024, 0.024, 1, 0,0,0);

	
	for(int i = 0; i < detectors; i++) {
//		Experiment.AddDisk(0, 0, i*(detectorThickness+spacing),
//			     0.042, 0.008, detectorThickness,
//			     2.33, 14, 28.085);
		Experiment.AddCuboid(-0.024, -0.024, i*(spacing), 0.024, 0.024, i*(spacing)+detectorThickness, 2.33, 14, 28.085);
	}
	
	Experiment.Print();

	return Experiment;
    }


//    public static Particle[] GetParticles(double[] pos0, double startMomentum, double startAngle) {
//	// example to simulate just one muon starting at p0
//	// with a total momentum startMomentum and theta=startAngle
//	// we follow the particle physics "convention"
//	// to have the z-axis in the (approximate) direction of the beam
//	
//	Particle [] Particles_gen = new Particle[1];
//	// initial momentum px,py,pz (MeV)
//	double phi = 0;
//	double[] mom0 = {startMomentum*Math.sin(startAngle)*Math.cos(phi),
//			 startMomentum*Math.sin(startAngle)*Math.sin(phi),
//			 startMomentum*Math.cos(startAngle)};
//	Particles_gen[0] = new Particle("muon", pos0, mom0, numSteps);
//
//	return Particles_gen;
//    }
}