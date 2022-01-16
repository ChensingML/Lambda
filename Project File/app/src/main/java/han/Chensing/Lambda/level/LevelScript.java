package han.Chensing.Lambda.level;
import java.util.*;
import han.Chensing.Lambda.elements.*;
import java.io.*;
import han.Chensing.Lambda.util.*;

public class LevelScript implements Serializable
{
	private ArrayList<ScriptItem> itemOrg;
	private ArrayList<ScriptItem> itemLive;
	private LinesContainer lines;
	private LinkedList<Element> notes;
	private int index;
	private int size;
	private long startTime;

	public LevelScript(LinesContainer lines, LinkedList<Element> notes)
	{
		this.index=0;
		this.startTime=System.currentTimeMillis();
		this.lines = lines;
		this.notes = notes;
	}
	
	public void setItems(ArrayList<ScriptItem> items){
		this.itemOrg=items;
		this.itemLive=new ArrayList<ScriptItem>(itemOrg);
		this.size=this.itemOrg.size();
	}
	
	public List<ScriptItem> next(){
		long now=System.currentTimeMillis();
		long delay=now-startTime;
		long l=delay-20;
		long r=delay+30;
		ArrayList<ScriptItem> si=new ArrayList<>();
		int i=index;
		int inva=index;
		for(;i<size&&i<index+10;i++){
			ScriptItem sc=itemLive.get(i);
			long cu=sc.getDelayTime();
			if(l<=cu&&r>=cu){
				if(!sc.isSolved())
					si.add(sc);
				sc.setSolved(true);
				inva=i;
			}
		}
		index=inva;
		return si;
	}
	
	/**
	 *	Script args
	 *	CREATE_NOTE:
	 * 		[0]		Name of Line
	 * 		[1]		Type of Note
	 * 					c - Click
	 * 		[2]		Line position
	 * 		[3]		Line distance
	 */
	public void solveNext(){
		List<ScriptItem> ns=next();
		for(ScriptItem i:ns){
			switch(i.getType()){
				case CREATE_NOTE:{
					String[] args=i.getArgs();
					Element e=lines.findLine(args[0]);
					boolean isLineSet=e instanceof LineSet;
					Note n;
					switch(args[1].charAt(0)){
						case 'c':{
							n=new ClickNote();
							if(isLineSet)
								n.setBindLineSet((LineSet)e);
							else
								n.setBindLine((Line)e);
							n.setPositionLine(Double.parseDouble(args[2]));
							n.setDistanceLine(Double.parseDouble(args[3]));
							n.setClicked(false);
							notes.add(n);
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	public static class ScriptItem{
		private long delayTime;
		private boolean solved;
		private OpratingType type;
		private String[] args;
		
		public ScriptItem(){
			this.solved=false;
		}

		public void setDelayTime(long delayTime){
			this.delayTime = delayTime;
		}

		public long getDelayTime(){
			return delayTime;
		}

		public void setSolved(boolean solved){
			this.solved = solved;
		}

		public boolean isSolved(){
			return solved;
		}

		public void setType(OpratingType type){
			this.type = type;
		}

		public OpratingType getType(){
			return type;
		}

		public void setArgs(String[] args){
			this.args = args;
		}

		public String[] getArgs(){
			return args;
		}
		
		public static enum OpratingType{
			CREATE_NOTE,CREATE_LINE,MOVE_LINE
		}
	}
}
