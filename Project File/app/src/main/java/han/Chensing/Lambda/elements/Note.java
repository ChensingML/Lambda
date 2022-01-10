package han.Chensing.Lambda.elements;
import android.graphics.*;
import han.Chensing.Lambda.util.*;

public abstract class Note extends Element
{
	protected boolean isUsingLineSet;
	protected Line bindLine;
	protected LineSet bindLineSet;
	protected double distanceLine;
	protected double positionLine;
	protected boolean clicked;
	protected int scoreState;
	
	protected Path[] currentPaths;
	protected Paint[] currentPaints;
	
	protected final static Path cpath;
	protected final static Path cpathIn;

	public static final float clickDistance=60;

	public void setScoreState(int scoreState){
		this.scoreState = scoreState;
	}

	public int getScoreState(){
		return scoreState;
	}

	public void setClicked(boolean clicked){
		this.clicked = clicked;
	}

	public boolean isClicked(){
		return clicked;
	}

	public void setIsUsingLineSet(boolean isUsingLineSet){
		this.isUsingLineSet = isUsingLineSet;
	}

	public boolean isUsingLineSet(){
		return isUsingLineSet;
	}

	public void setBindLineSet(LineSet bindLineSet){
		isUsingLineSet=true;
		this.bindLineSet = bindLineSet;
	}

	public LineSet getBindLineSet(){
		return bindLineSet;
	}

	public void setBindLine(Line bindLine){
		isUsingLineSet=false;
		this.bindLine = bindLine;
	}

	public Line getBindLine(){
		return bindLine;
	}

	public void setDistanceLine(double distanceLine){
		this.distanceLine = distanceLine;
	}

	public double getDistanceLine(){
		return distanceLine;
	}

	public void setPositionLine(double positionLine){
		this.positionLine = positionLine;
	}

	public double getPositionLine(){
		return positionLine;
	}
	
	static{
		cpath=new Path();
		int p1=4;
		int p2=9;
		int p3=5;
		int p4=13;
		int offset=80;
		Path c1=new Path();
		c1.moveTo(p1+p3,p2);
		c1.lineTo(p1,p2);
		c1.lineTo(0,0);
		c1.lineTo(p1,-p2);
		c1.lineTo(p1+p3,-p2);
		c1.lineTo(p3,0);
		c1.lineTo(p1+p3,p2);
		c1.offset(-offset-p4,0);
		Path c2=new Path();
		c2.moveTo(-p1-p3,p2);
		c2.lineTo(-p1,p2);
		c2.lineTo(0,0);
		c2.lineTo(-p1,-p2);
		c2.lineTo(-p1-p3,-p2);
		c2.lineTo(-p3,0);
		c2.lineTo(-p1-p3,p2);
		c2.offset(offset+p4,0);
		Path c3=new Path();
		c3.moveTo(-offset,p2);
		c3.lineTo(offset,p2);
		c3.lineTo(offset+p1,0);
		c3.lineTo(offset,-p2);
		c3.lineTo(-offset,-p2);
		c3.lineTo(-offset-p1,0);
		c3.close();
		cpath.addPath(c1);
		cpath.addPath(c2);
		cpath.addPath(c3);
		float q1=4.8f;
		int q2=6;
		Path ci=new Path();
		ci.moveTo(-offset+q2,p2-q1);
		ci.lineTo(offset-q2,p2-q1);
		ci.lineTo(offset+p1-q2,0);
		ci.lineTo(offset-q2,-p2+q1);
		ci.lineTo(-offset+q2,-p2+q1);
		ci.lineTo(-offset-p1+q2,0);
		ci.close();
		cpathIn=new Path(ci);
	}

	@Override
	public void draw(Canvas can)
	{
		double positionLine;
		Line l;
		Matrix m=new Matrix();
		if(!isUsingLineSet){
			positionLine=Math.abs(this.positionLine);
			l=bindLine;
		}else{
			l=this.positionLine>=0?
				bindLineSet.getRight():
				bindLineSet.getLeft();
			positionLine=this.positionLine<0?
				positionLine=Math.abs(this.positionLine):
				this.positionLine;
		}
		double a=l.getDirection();
		double x=l.getX();
		double y=l.getY();
		int rev=l.isReversed()?-1:1;
		float dx=(float)(DensityUtil.get().dp2px((float)x)+
			(Math.cos(a)*positionLine)-
			(Math.sin(a)*distanceLine*rev));
		float dy=(float)(DensityUtil.get().dp2px((float)y)-
			(Math.sin(a)*positionLine)-
			(Math.cos(a)*distanceLine*rev));
		m.setRotate((float)-Math.toDegrees(a));
		setX(DensityUtil.get().px2dp((dx)));
		setY(DensityUtil.get().px2dp((dy)));
		currentPaths[0]=new Path(cpath);
		currentPaths[1]=new Path(cpathIn);
		currentPaths[0].transform(m);
		currentPaths[1].transform(m);
		currentPaths[0].offset(dx,dy);
		currentPaths[1].offset(dx,dy);
		DpCanvas.drawPaths(can,currentPaths,currentPaints);
	}
}
