package han.Chensing.Lambda;
import android.content.*;
import android.graphics.*;
import java.util.*;
import han.Chensing.Lambda.elements.*;
import han.Chensing.Lambda.util.*;
import han.Chensing.Lambda.math.*;
import han.Chensing.Lambda.logic.*;

public class WorkThread extends Thread
{
	private CSV con;
	private volatile boolean running;
	
	private LinkedList<Element> lines;
	private LinkedList<Element> notes;
	private LinkedList<Element> effects;
	
	public WorkThread(CSV con){
		super();
		this.con=con;
		running=true;
		lines=new LinkedList<>();
		notes=new LinkedList<>();
		effects=new LinkedList<>();
		LineSet l=new LineSet(){
			@Override
			public void logic(){
				super.logic();
				//setDirection(0);
				//setDirection(getDirection()+0.01);
				//setDirection((Math.PI*3/4)+0.4);
			}
		};
		Speed speed=new Speed();
		speed.setDirection(Math.PI/2);
		speed.setRate(3);
		PSpeed ps=new PSpeed();
		ps.setRate(-0.05);
		ps.setStop(true);
		speed.setPSpeed(ps);
		l.setSpeed(speed);
		l.setX(160);
		l.setY(400);
		l.setDirection(0);
		lines.add(l);
	}
	
	@Override
	public void run()
	{
		super.run();
		while(running){
			Canvas can=(con.getHolder().lockCanvas());
			try{
			can.drawColor(Color.BLACK);
			DrawUtil.drawAndLogicAll(lines,can);
			DrawUtil.drawAndLogicAll(notes,can);
			DrawUtil.drawAndLogicAll(effects,can);
			
			Paint p;
			List<TouchContainer.Finger> l=TouchContainer.get().getList();
			for(TouchContainer.Finger f:
			l){
				/*if(CMath.isTouchOn(f.getX(),f.getY(),(Note)notes.get(0))){
					p=PaintKit.getInstance().get("clickInP");
				}else{
					p=PaintKit.getInstance().get("clickP");
				}*/
				if(f.isClicked())
					continue;
				f.setClicked(true);
				//can.drawRect(f.getX()-10,f.getY()-140,f.getX()+10,f.getY()-160,p);
				ClickNote cn=new ClickNote(){
					boolean right=true;
					@Override
					public void logic(){
						super.logic();
						/*if(getPositionLine()>=100&&right){
							right=false;
						}else if(getPositionLine()<=-100&&!right){
							right=true;
						}
						setPositionLine(getPositionLine()+5*(right?1:-1));*/
						setDistanceLine(getDistanceLine()-7);
					}
				};
				LineSet ls=(LineSet)lines.get(0);
				cn.setBindLineSet(ls);
				cn.setDistanceLine(1000);
				cn.setPositionLine(-(ls.getX()-
					DensityUtil.get().px2dp(f.getX())));
				notes.add(cn);
			}
			Iterator<Element> ie=notes.iterator();
			while(ie.hasNext()){
				Note n=(Note)ie.next();
				if(n.getDistanceLine()<=0
				&&!n.isClicked()){
					ClickEffect ce=new ClickEffect(
						n.getX(),
						n.getY());
					effects.add(ce);
					for(int i=0;i<3;i++){
						Particle pc=new Particle(Color.parseColor("#fefeb4"));
						pc.setX(ce.getX());
						pc.setY(ce.getY());
						effects.add(pc);
					}
					n.setClicked(true);
					ie.remove();
				}
			}
			/*can.drawCircle(
				(float)DensityUtil.get().dp2px(testEffect.getX()),
					(float)DensityUtil.get().dp2px(testEffect.getY()),3
					,PaintKit.getInstance().get("clickInP"));*/
			}catch(Exception e){
				e.printStackTrace();
			}
			con.getHolder().unlockCanvasAndPost(can);
		}
	}
	
}
