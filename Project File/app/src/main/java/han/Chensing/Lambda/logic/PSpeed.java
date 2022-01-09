package han.Chensing.Lambda.logic;

public class PSpeed
{
	private double rate;
	private boolean stop;
	
	public PSpeed(PSpeed pSpeed){
		this(pSpeed.rate,pSpeed.stop);
	}

	public PSpeed(double rate, boolean stop)
	{
		this.rate = rate;
		this.stop = stop;
	}
	
	public PSpeed(double rate){
		this(rate,true);
	}
	
	public PSpeed(){
		this(0,true);
	}

	public void setRate(double rate){
		this.rate = rate;
	}

	public double getRate(){
		return rate;
	}

	public void setStop(boolean stop){
		this.stop = stop;
	}

	public boolean isStop(){
		return stop;
	}
}
