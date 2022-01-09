package han.Chensing.Lambda.util;
import java.util.*;
import han.Chensing.Lambda.elements.*;
import android.graphics.*;

public class DrawUtil
{
	public static void drawAll(List<Element> elements,Canvas canvas){
		Iterator<Element> it=elements.iterator();
		while(it.hasNext()){
			Element e=it.next();
			if(e==null)
				continue;
			e.draw(canvas);
		}
	}
	
	public static void logicAll(List<Element> elements){
		Iterator<Element> it=elements.iterator();
		while(it.hasNext()){
			Element e=it.next();
			if(e==null)
				continue;
			e.logic();
			if(e instanceof DyingElement){
				DyingElement de=(DyingElement)e;
				if(de.isDead())
					it.remove();
			}
		}
	}
	
	public static void drawAndLogicAll(List<Element> elements,Canvas canvas){
		Iterator<Element> it=elements.iterator();
		while(it.hasNext()){
			Element e=it.next();
			if(e==null)
				continue;
			e.logic();
			e.draw(canvas);
			if(e instanceof DyingElement){
				DyingElement de=(DyingElement)e;
				if(de.isDead())
					it.remove();
			}
		}
	}
	
	public static Path createSquare(double width){
		double squS=width;
		Path bs=new Path();
		bs.moveTo((float)(squS),(float)(squS));
		bs.lineTo((float)(squS),(float)(-squS));
		bs.lineTo((float)(-squS),(float)(-squS));
		bs.lineTo((float)(-squS),(float)(squS));
		bs.close();
		return bs;
	}
}
