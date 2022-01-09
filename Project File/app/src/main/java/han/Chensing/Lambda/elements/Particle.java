package han.Chensing.Lambda.elements;
import android.graphics.*;
import java.util.*;
import han.Chensing.Lambda.logic.*;
import han.Chensing.Lambda.util.*;

public class Particle extends DyingElement
{
	private static final Speed defSpeed;
	static{
		defSpeed=new Speed();
		defSpeed.setRate(10);
		PSpeed psp=new PSpeed();
		psp.setRate(-0.38);
		psp.setStop(true);
		defSpeed.setPSpeed(psp);
	}
	
	private Random ran;
	private Paint paint;
	
	public Particle(int color){
		dyingRate=ClickEffect.rate;
		this.ran=new Random();
		int deg=ran.nextInt(360);
		double arc=Math.toRadians(deg);
		speed=new Speed(defSpeed);
		speed.setDirection(arc);
		this.paint=new Paint();
		paint.setColor(color);
	}
	
	@Override
	public void draw(Canvas can){
		double al=dying<=0.8?1:1-(dying*5);
		paint.setAlpha((int)(255*al));
		float pxx=DensityUtil.get().dp2px(getX());
		float pxy=DensityUtil.get().dp2px(getY());
		Path p=DrawUtil.createSquare(14);
		p.offset(pxx,pxy);
		can.drawPath(p,paint);
	}

	@Override
	public void logic(){
		super.logic();
		
	}

}
