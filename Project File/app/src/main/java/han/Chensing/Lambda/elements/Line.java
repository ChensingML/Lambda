package han.Chensing.Lambda.elements;
import android.graphics.*;
import han.Chensing.Lambda.math.*;
import han.Chensing.Lambda.*;
import han.Chensing.Lambda.util.*;

public class Line extends Element
{
	protected double direction;
	protected boolean isReversed;
	private float speedRate;

	public void setReversed(Boolean isReversed){
		this.isReversed = isReversed;
	}

	public Boolean isReversed(){
		return isReversed;
	}

	public void setSpeedRate(float speedRate){
		this.speedRate = speedRate;
	}

	public float getSpeedRate(){
		return speedRate;
	}

	public void setDirection(double direction){
		this.direction = CMath.toNormalAng(direction);
	}

	public double getDirection(){
		return direction;
	}
	
	@Override
	public void draw(Canvas can){
		double[] jd=CMath.crossPointWithScreen(x,y,direction);
		DpCanvas.drawLine(can,x,y,(float)jd[0],(float)jd[1],PaintKit.getInstance().get("lineP"));
	}

	@Override
	public void logic(){
		super.logic();
	}
}
