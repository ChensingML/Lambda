package han.Chensing.Lambda.elements;
import android.graphics.*;
import han.Chensing.Lambda.math.*;
import han.Chensing.Lambda.util.*;
import han.Chensing.Lambda.*;

public class LineSet extends Element
{
	private Line left;
	private Line right;
	private double direction;
	private double speedRate;
	
	public LineSet(){
		this.left=new Line();
		this.right=new Line();
		left.setReversed(true);
	}
	
	public Line getLeft(){
		return left;
	}

	public Line getRight(){
		return right;
	}

	public void setDirection(double direction){
		this.direction = CMath.toNormalAng(direction);
	}

	public double getDirection(){
		return direction;
	}

	public void setSpeedRate(double speedRate){
		this.speedRate = speedRate;
	}

	public double getSpeedRate(){
		return speedRate;
	}

	@Override
	public void logic()
	{
		super.logic();
		left.setX(getX());
		left.setY(getY());
		right.setX(getX());
		right.setY(getY());
		left.setDirection(direction-Math.PI);
		right.setDirection(direction);
	}
	

	@Override
	public void draw(Canvas can)
	{
		double[] jd=CMath.crossPointWithScreen(x,y,left.direction);
		DpCanvas.drawLine(can,x,y,(float)jd[0],(float)jd[1],
						  PaintKit.getInstance().get("lineP"));
		double[] jd2=CMath.crossPointWithScreen(x,y,right.direction);
		DpCanvas.drawLine(can,x,y,(float)jd2[0],(float)jd2[1],
						  PaintKit.getInstance().get("lineP"));
	}
}
