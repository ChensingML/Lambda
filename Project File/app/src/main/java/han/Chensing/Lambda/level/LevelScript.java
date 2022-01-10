package han.Chensing.Lambda.level;
import java.util.*;
import han.Chensing.Lambda.elements.*;

public class LevelScript
{
	private ArrayList<ScriptItem> itemOrg;
	private ArrayList<ScriptItem> itemLive;
	private LinkedList<Element> lines;
	private LinkedList<Element> notes;
	private int index;
	private long startTime;

	protected LevelScript(LinkedList<Element> lines, LinkedList<Element> notes,ArrayList<ScriptItem> items)
	{
		this.index=0;
		this.startTime=System.currentTimeMillis();
		this.lines = lines;
		this.notes = notes;
		this.itemOrg=items;
		this.itemLive=new ArrayList<ScriptItem>(itemOrg);
	}
	
	public ScriptItem next(){
		long now=System.currentTimeMillis();
		long delay=now-startTime;
		return null;
	}
	
	public static class ScriptItem{
		private long delayTime;
		private boolean solved;
		private OpratingType type;
		private int[] args;
		
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

		public void setArgs(int[] args){
			this.args = args;
		}

		public int[] getArgs(){
			return args;
		}
		
		public static enum OpratingType{
			CREATE_NOTE,CREATE_LINE,MOVE_LINE
		}
	}
}
