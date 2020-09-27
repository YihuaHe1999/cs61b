/**NBody is a class that 
	will actually run your simulation*/
public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

	public static Body[] readBodies(String filename){
		/**Why not marge the method "reaRadius" 
			and "readBodies" into a single method?*/
		In in = new In(filename);
		Body[] allBodies=new Body[in.readInt()];
		in.readDouble();
		/** You can not use "while(!in.isEmpty())",
			 since there are something else at the end of the filename.
			 Tips: Look at the data file. */
		for(int i = 0; i<allBodies.length; i++) {
			/**Everytime you construct a array, you need to "new" every element in it.*/
			allBodies[i]=new Body(0,0,0,0,0," ");
			allBodies[i].xxPos=in.readDouble();
			allBodies[i].yyPos=in.readDouble();
			allBodies[i].xxVel=in.readDouble();
			allBodies[i].yyVel=in.readDouble();
			allBodies[i].mass=in.readDouble();
			allBodies[i].imgFileName=in.readString();
		}
		return allBodies;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename=args[2];
		double R=readRadius(filename);
		Body[] allBodies=readBodies(filename);

/**
	Important!
	Error: \StdDraw.java:299: Unmapped character encoding GBK(0x92)
	That's because I use UTF-8 to encode the file. 
	I should use ASII !!!
	So finally I resave  "NBody", "Body" and "StdDraw", then it runs succesfully.
	It really took me a lot of time !
	*/
		StdDraw.enableDoubleBuffering();
		/** almost forget setScale! It is to set the scale of the whole picture. */
		StdDraw.setScale(-R,R);
		double t=0;
		StdAudio.play("audio/2001.mid");
		/** Notice the sequence of StdDraw: clear, picture, show, pause*/
		while (t<T){
			double[] xForces=new double[allBodies.length];
			double[] yForces=new double[allBodies.length];
			for(int i=0; i<xForces.length; i++){
				xForces[i]=allBodies[i].calcNetForceExertedByX(allBodies);
				yForces[i]=allBodies[i].calcNetForceExertedByY(allBodies);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i=0; i<xForces.length; i++){
				allBodies[i].update(dt,xForces[i],yForces[i]);
				allBodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10); 
			t+=dt;
		}
		StdOut.printf("%d\n", allBodies.length);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < allBodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                  allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);   
		}
	}
}