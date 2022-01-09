package han.Chensing.Lambda.logic;

public class Rate
{
	protected double rate;
	protected PSpeed pSpeed;

	public Rate(double rate, PSpeed pSpeed){
		this.rate = rate;
		this.pSpeed = pSpeed;
	}
	
	public Rate(Rate rate){
		this.rate=rate.getRate();
		this.pSpeed=rate.getPSpeed();
	}
	
	public Rate(double rate){
		this(rate,null);
	}
	
	public Rate(){
		
	}

	public void setRate(double rate){
		this.rate = rate;
	}

	public double getRate(){
		return rate;
	}

	public void setPSpeed(PSpeed pSpeed){
		this.pSpeed = pSpeed;
	}

	public PSpeed getPSpeed(){
		return pSpeed;
	}

	public void applyPSpeed(){
		if(pSpeed==null)
			pSpeed=new PSpeed();
		double n=rate+pSpeed.getRate();
		if((rate*n)<=0&&pSpeed.isStop()){//异号
			pSpeed=null;
			rate=0;
			return;
		}
		rate=n;
	}
}
