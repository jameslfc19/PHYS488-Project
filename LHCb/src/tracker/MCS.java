package tracker;

public class MCS {
	
	private double p, Z, A, thickness; //Material Density, Atomic number, Atomic Mass.
	
	public MCS(double materialDensity, double Z, double A, double thickness) {
		p = materialDensity;
		this.Z = Z;
		this.A = A;
		this.thickness = thickness;
		//System.out.println("X0: "+getX0()+"m");
	}

	//returns X0 in m.
	public double getX0() {
		if(Z==0) return 0;
		return 0.01*(716.3*A)/(p*Z*(Z+1)*Math.log(287/Math.sqrt(Z)));
	}
	
	//returns theta-0 in radians.
	public double getTheta0(Particle particle) {
		if(Z==0) return 0;
		
		double x = thickness;
		double B = particle.getBeta();
		double mom = particle.getLastMomentumMag();
		double X0 = getX0();
		int z = particle.getCharge();
		
		double t = Math.abs((13.6/(B*mom))*z*Math.sqrt(x/X0)*(1+0.038*Math.log(x/X0)));
		
		//System.out.println("Theta: "+t+", x:"+x+", B:"+B+", mom:"+mom+", X0:"+X0);
		
		return t;

	}

}
