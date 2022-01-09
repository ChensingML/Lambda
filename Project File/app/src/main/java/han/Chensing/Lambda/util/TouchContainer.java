package han.Chensing.Lambda.util;
import java.util.*;
import android.view.*;
import java.util.concurrent.*;

public class TouchContainer
{
	private static TouchContainer instance;
	
	public static volatile boolean isDone=true;
	
	private volatile List<Finger> fingers;
	private volatile Queue<MotionEvent> event;

	public List<Finger> getFingers(){
		return fingers;
	}
	
	public synchronized void pushEvent(MotionEvent event){
		this.event.offer(event);
	}
	
	public static TouchContainer get(){
		if(instance==null)
			instance=new TouchContainer();
		return instance;
	}
	
	public List<Finger> getList(){
		return new LinkedList<Finger>(fingers);
	}
	
	public void loop(){
		int len=event.size();
		for(int i=0;i<len;i++){
			solve(event.poll());
		}
	}
	
	public synchronized void solve(MotionEvent event){
		//isDone=false;
		if(event==null)
			return;
		switch(event.getActionMasked()){
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:{
				fingers.clear();
				break;
			}
			case MotionEvent.ACTION_DOWN:{
				//fingers.clear();
				fingers.add(createFinger(event));
				break;
			}
			case MotionEvent.ACTION_MOVE:{
				int len=event.getPointerCount();
				for(int i=0;i<len;i++){
					int id=event.getPointerId(i);
					Iterator<Finger> in=fingers.iterator();
					while(in.hasNext()){
						Finger f=in.next();
						if(id==-1){
							try{
							//in.remove();
							}catch(Exception e){
								e.printStackTrace();
							}
							continue;
						}
						if(f.getId()==id){
							if(f==null){
								in.remove();
								break;
							}
							f.setX(event.getX(event.findPointerIndex(id)));
							f.setY(event.getY(event.findPointerIndex(id)));
							break;
						}
					}
				}
				break;
			}
			case MotionEvent.ACTION_POINTER_UP:{
				int id=event.getPointerId(
						event.getActionIndex());
				Iterator<Finger> in=fingers.iterator();
				while(in.hasNext()){
					try{
					Finger f=in.next();
					if(f.getId()==id){
						in.remove();
						//break;
					}}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			}
			case MotionEvent.ACTION_POINTER_DOWN:{
				fingers.add(createFinger(event));
				break;
			}
		}
		//isDone=true;
	}
	
	private Finger createFinger(MotionEvent event){
		int id=event.getPointerId(
			event.getActionIndex());
		Finger fin=new Finger(id);
		float x=event.getX(id);
		float y=event.getY(id);
		fin.setX(x);
		fin.setY(y);
		return fin;
	}
	
	private TouchContainer(){
		fingers=(new LinkedList<Finger>());
		event=new LinkedBlockingQueue<MotionEvent>();
	}
	
	public static class Finger{
		private volatile int id;
		private boolean isClicked;//是否被Click判定
		private boolean isFlicked;//是否被Flick判断
		private boolean isHold;//是否被Hold判定
		private volatile float x;
		private volatile float y;
		
		public Finger(int id){
			this.id=id;
			this.isClicked=false;
			this.isFlicked=false;
			this.isHold=false;
		}
	
		public void setClicked(boolean isClicked){
			this.isClicked = isClicked;
		}

		public boolean isClicked(){
			return isClicked;
		}

		public void setFlicked(boolean isFlicked){
			this.isFlicked = isFlicked;
		}

		public boolean isFlicked(){
			return isFlicked;
		}

		public void setHold(boolean isHold){
			this.isHold = isHold;
		}

		public boolean isHold(){
			return isHold;
		}

		public void setId(int id){
			this.id = id;
		}

		public int getId(){
			return id;
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
	}
}
