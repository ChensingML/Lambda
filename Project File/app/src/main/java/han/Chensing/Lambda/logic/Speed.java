package han.Chensing.Lambda.logic;
import han.Chensing.Lambda.math.*;

public class Speed extends Rate
{
	private double direction;
	
	public Speed(){
		
	}
	
	public Speed(Speed speed){
		this.direction=speed.direction;
		this.rate=speed.rate;
		this.pSpeed=new PSpeed(speed.pSpeed);
	}

	public void setDirection(double direction){
		this.direction = CMath.toNormalAng(direction);
	}

	public double getDirection(){
		return direction;
	}
}
