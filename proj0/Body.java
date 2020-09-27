import java.lang.Math;

public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G=6.67*Math.pow(10,-11);
	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Body(Body b){
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}

	/**
	method
	*/
	public double calcDistance(Body a){
		/** return the distance of a and this. */
		double dx=a.xxPos-this.xxPos;
		double dy=a.yyPos-this.yyPos;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public double calcForceExertedBy(Body a){
		/** return F of this and a. */
		double r=this.calcDistance(a);
		if (this.equals(a)) {
			return 0;
		}else {
			return Body.G*a.mass*this.mass/(r*r);
		}
	}

	public double calcForceExertedByX(Body a){
		/** return the x part of the force. */
		double r=this.calcDistance(a);
		double f=this.calcForceExertedBy(a);
		double dx=a.xxPos-this.xxPos;
		return f*dx/r;
	}

	public double calcForceExertedByY(Body a){
		/** return the y part of the force. */
		double r=this.calcDistance(a);
		double f=this.calcForceExertedBy(a);
		double dy=a.yyPos-this.yyPos;
		return f*dy/r;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		/** return the net force x on this under 
		  the influence of the Bpdy arrays*/
		double nfx=0;
		for (Body element : allBodys){
			if(this.equals(element)){
				continue;
			}
			nfx += this.calcForceExertedByX(element);
		}
		return nfx;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		/** return the net force y on this under 
		  the influence of the Bpdy arrays*/
		double nfy=0;
		for (Body element : allBodys){
			if(this.equals(element)){
				continue;
			}
			nfy += this.calcForceExertedByY(element);
		}
		return nfy;
	}

	public void update(double dt, double fX, double fY){
		/** Can we get rid of "this" in "this.mass" ? : Yes. */
		double ax=fX/this.mass;
		double ay=fY/this.mass;
		this.xxVel=this.xxVel+dt*ax;
		this.yyVel=this.yyVel+dt*ay;
		this.xxPos=this.xxPos+dt*this.xxVel;
		this.yyPos=this.yyPos+dt*this.yyVel;
	}
	
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}