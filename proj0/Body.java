public class Body
{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b)
	{
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b)
	{
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.hypot(dx, dy);
        return r;
	}

	public double calcForceExertedBy(Body b)
	{
		double distance = this.calcDistance(b);
		double Force = (G * this.mass * b.mass) / (distance * distance);
		return Force;
	}

	public double calcForceExertedByX(Body b)
	{
		double Force = this.calcForceExertedBy(b);
		double Force_x = Force * (b.xxPos - this.xxPos) / this.calcDistance(b);
		return Force_x;
	}

	public double calcForceExertedByY(Body b)
	{
		double Force = this.calcForceExertedBy(b);
		double Force_y = Force * (b.yyPos - this.yyPos) / this.calcDistance(b);
		return Force_y;
	}

	public double calcNetForceExertedByX(Body[] p)
	{
		double NetForce_x = 0;
		for (int i = 0; i < p.length; i++)
		{
			if (!this.equals(p[i]))
			{
				NetForce_x += this.calcForceExertedByX(p[i]);
			}
		}
		return NetForce_x;
	}

	public double calcNetForceExertedByY(Body[] p)
	{
		double NetForce_y = 0;
		for (int i = 0; i < p.length; i++)
		{
			if (!this.equals(p[i]))
			{
				NetForce_y += this.calcForceExertedByY(p[i]);
			}
		}
		return NetForce_y;
	}

	public void update(double dt, double fx, double fy)
	{
		double a_x = fx / this.mass;
		double a_y = fy / this.mass;

		this.xxVel += dt * a_x;
		this.yyVel += dt * a_y;

		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw()
	{
		StdDraw.picture(this.xxPos,this.yyPos,"images/" + imgFileName);
	}
}