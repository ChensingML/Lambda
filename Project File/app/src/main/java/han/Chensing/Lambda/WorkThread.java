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
		this.con = con;
		running = true;
		lines = new LinkedList<>();
		notes = new LinkedList<>();
		effects = new LinkedList<>();
		LineSet l=new LineSet();
		Speed speed=new Speed();
		speed.setDirection(Math.PI / 2);
		speed.setRate(5);
		PSpeed ps=new PSpeed();
		ps.setRate(-0.03);
		ps.setStop(true);
		speed.setPSpeed(ps);
		l.setSpeed(speed);
		l.setX(0);
		l.setY(700);
		l.setDirection(0);
		lines.add(l);
		
		
	}

	@Override
	public void run(){
		super.run();
		int cc=100;
		Random r=new Random();
		while (running){
			Canvas can=(con.getHolder().lockCanvas());
			try{
				can.drawColor(Color.BLACK);
				DrawUtil.drawAndLogicAll(lines, can);
				DrawUtil.drawAndLogicAll(notes, can);
				DrawUtil.drawAndLogicAll(effects, can);

				Paint p;
				List<TouchContainer.Finger> l=TouchContainer.get().getList();
				for (TouchContainer.Finger f:l){
					Iterator<Element> nts=notes.iterator();
					int i=10;
					while (i-- >= 0 && nts.hasNext()){
						Note n=(Note)nts.next();
						if (CMath.isTouchOn(f.getX(), f.getY(), n)){
							p = PaintKit.getInstance().get("clickInP");
						}else{
							p = PaintKit.getInstance().get("clickP");
						}
					}
				}
				if(cc--<=0){
					cc=10;
					ClickNote n=new ClickNote(){
						public void logic(){
							super.logic();
							setDistanceLine(getDistanceLine()-10);
						}
					};
					n.setBindLineSet((LineSet)lines.get(0));
					n.setDistanceLine(1000);
					n.setPositionLine(r.nextInt(700));
					notes.add(n);
				}
				Iterator<Element> ie=notes.iterator();
				while (ie.hasNext()){
					Note n=(Note)ie.next();
					if (n.getDistanceLine() <= 0
						&& !n.isClicked()){
						ClickEffect ce=new ClickEffect(
							n.getX(),
							n.getY());
						effects.add(ce);
						for (int i=0;i < 4;i++){
							Particle pc=new Particle(
								Color.parseColor("#fefeb4"));
							pc.setX(ce.getX());
							pc.setY(ce.getY());
							effects.add(pc);
						}
						n.setClicked(true);
						ie.remove();
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			con.getHolder().unlockCanvasAndPost(can);
		}
	}

}
