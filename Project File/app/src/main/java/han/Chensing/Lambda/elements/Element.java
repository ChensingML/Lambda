package han.Chensing.Lambda.elements;
import android.graphics.*;
import han.Chensing.Lambda.logic.*;
import han.Chensing.Lambda.util.*;

public abstract class Element
{
	protected float x;
	protected float y;
	protected Speed speed;

	public void setSpeed(Speed speed){
		this.speed = speed;
	}

	public Speed getSpeed(){
		return speed;
	}

	public void setX(float x){
		this.x = x;
	}

	public float getX(){
		return x;
	}

	public void setY(float y){
		this.y = y;
	}

	public float getY(){
		return y;
	}
	
	public abstract void draw(Canvas can);
	
	public void logic(){
		if(speed!=null){
			speed.applyPSpeed();
			float plusx=(float)(Math.cos(speed.getDirection())*speed.getRate());
			float plusy=(float)(Math.sin(speed.getDirection())*speed.getRate());
			x+=plusx;
			y-=plusy;
		}
	}
}
