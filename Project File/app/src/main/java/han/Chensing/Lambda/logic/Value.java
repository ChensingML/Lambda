package han.Chensing.Lambda.logic;

public class Value
{
	private double value;
	private Rate rate;

	public Value(double value, Rate rate){
		this.value = value;
		this.rate = rate;
	}

	public Value(double value){
		this(value,null);
	}
	
	public Value(Value value){
		this(value.getValue(),new Rate(value.getRate()));
	}
	
	public Value(){
		this(0,null);
	}

	public void setValue(double value){
		this.value = value;
	}

	public double getValue(){
		return value;
	}

	public void setRate(Rate rate){
		this.rate = rate;
	}

	public Rate getRate(){
		return rate;
	}
	
	public void applyRate(){
		if(rate==null){
			rate=new Rate(0);
			return;
		}
		rate.applyPSpeed();
		value+=rate.getRate();
	}
}
