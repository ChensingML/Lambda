package han.Chensing.Lambda.elements;
import android.graphics.*;
import han.Chensing.Lambda.logic.*;
import han.Chensing.Lambda.util.*;
import java.util.*;

public class ClickEffect extends DyingElement
{
	
	private static final float scale=1.2f;
	private static final float speedScale=1.1f;

	protected static final float rate=0.03f*speedScale;
	
	private static final Value outLx_;
	private static final Value inLx_;
	private static final Value sCirSize_;
	private static final Value xCirSize_;
	private static final Value squSize_;
	private static final Value arcAngle_;
	
	private static final Paint perfectS;
	private static final Paint perfectX;
	
	static{
		int c=Color.parseColor("#fefeb4");
		perfectS=new Paint();
		perfectS.setColor(c);
		perfectS.setStrokeWidth(3*scale);
		perfectX=new Paint();
		perfectX.setColor(c);
		perfectX.setAlpha(128);
		PSpeed ps1=new PSpeed(0.2165*speedScale*speedScale);
		PSpeed ps2=new PSpeed(-0.2*speedScale*speedScale);
		PSpeed ps3=new PSpeed(0.14*speedScale*speedScale);
		PSpeed ps4=new PSpeed(-0.055*speedScale*speedScale);
		PSpeed ps5=new PSpeed(-0.3*speedScale*speedScale);
		PSpeed ps6=new PSpeed(-0.11*speedScale*speedScale);
		Rate r1=new Rate(-5.5*speedScale,ps1);//Width
		Rate r2=new Rate(5.5*speedScale,ps2);
		Rate r3=new Rate(-3.15*speedScale,ps3);
		Rate r4=new Rate(1.5*speedScale,ps4);
		Rate r5=new Rate(7*speedScale,ps5);
		Rate r6=new Rate(4.7*speedScale,ps6);
		outLx_=new Value(70,r1);
		inLx_=new Value(0,r2);
		sCirSize_=new Value(40,r3);
		xCirSize_=new Value(0,r4);
		squSize_=new Value(0,r5);
		arcAngle_=new Value(180,r6);
	}
	
	private Value outLx;
	private Value insLx;
	private Value sCirSize;
	private Value xCirSize;
	private Value squSize;
	private Value arcAngle;
	
	public ClickEffect(float x,float y){
		setX(x);
		setY(y);
		dying=0;
		dyingRate=rate;
		outLx=new Value(outLx_);
		insLx=new Value(inLx_);
		sCirSize=new Value(sCirSize_);
		xCirSize=new Value(xCirSize_);
		squSize=new Value(squSize_);
		arcAngle=new Value(arcAngle_);
	}
	
	@Override
	public void draw(Canvas can){
		if(isDead()){
			return;
		}
		double dyRate=dying<=0.8?1:1-(dying*5);
		Paint outLine=new Paint(perfectS);
		outLine.setAlpha((int)(255*dyRate));
		outLine.setStyle(Paint.Style.STROKE);
		double x=getX();
		double y=getY();
		float pxx=DensityUtil.get().dp2px((float)x);
		float pxy=DensityUtil.get().dp2px((float)y);
		double squS=squSize.getValue()*scale;
		Path bs=DrawUtil.createSquare(squS);
		Path cup=new Path();
		bs.offset(
			pxx,
			pxy,cup);
		can.drawPath(cup,outLine);
		
		Paint inLine=new Paint(perfectX);
		inLine.setStyle(Paint.Style.STROKE);
		inLine.setStrokeWidth((float)outLx.getValue()*scale);
		inLine.setAlpha((int)(128*dyRate));
		Path ls=DrawUtil.createSquare(insLx.getValue()*scale);
		Matrix _45=new Matrix();
		_45.postRotate(45);
		ls.transform(_45);
		ls.offset(pxx,pxy);
		can.drawPath(ls,inLine);

		Path scir=new Path();
		inLine.setStyle(Paint.Style.FILL);
		scir.addCircle(0,0,(float)xCirSize.getValue()*scale,Path.Direction.CW);
		scir.offset(pxx,pxy);
		can.drawPath(scir,inLine);
		
		Path bcir=new Path();
		outLine.setStyle(Paint.Style.FILL);
		bcir.addCircle(0,0,(float)sCirSize.getValue()*scale,Path.Direction.CW);
		bcir.offset(pxx,pxy);
		can.drawPath(bcir,outLine);
		
		Path arc=new Path();
		outLine.setStyle(Paint.Style.STROKE);
		float aw=(float)((insLx.getValue()-10)*scale);
		RectF r=new RectF(-aw,-aw,aw,aw);
		arc.addArc(r,
				   (float)arcAngle.getValue(),(float)(90));
		arc.addArc(r,
				   (float)arcAngle.getValue()+180,(float)(90));
		arc.offset(pxx,pxy);
		can.drawPath(arc,outLine);
	}

	@Override
	public void logic(){
		super.logic();
		if(isDead()){
			return;
		}
		outLx.applyRate();
		insLx.applyRate();
		sCirSize.applyRate();
		xCirSize.applyRate();
		squSize.applyRate();
		arcAngle.applyRate();
	}

}
