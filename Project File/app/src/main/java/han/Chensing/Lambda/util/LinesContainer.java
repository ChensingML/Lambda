package han.Chensing.Lambda.util;
import java.util.*;
import han.Chensing.Lambda.elements.*;

public class LinesContainer
{
	private LinkedList<Element> lines;
	private HashMap<String,Element> lmap;
	
	public LinesContainer(){
		this.lines=new LinkedList<>();
		this.lmap=new HashMap<>();
	}

	public LinkedList<Element> getLines(){
		return lines;
	}
	
	public HashMap<String, Element> getLmap(){
		return lmap;
	}
	
	public Element findLine(String name){
		return lmap.get(name);
	}
	
	public void add(Element line,String name){
		lmap.put(name,line);
		lines.add(line);
	}
	
}
