package han.Chensing.Lambda;
import java.util.*;
import android.graphics.*;

public class PaintKit
{
	private static PaintKit instance;
	public static PaintKit getInstance(){
		if(instance==null)
			instance=new PaintKit();
		return instance;
	}
	
	private HashMap<String,Paint> paints;
	public HashMap<String,Paint> getPaint(){
		return this.paints;
	}
	
	public void add(String name,Paint paint){
		paints.put(name,paint);
	}
	
	public Paint get(String name){
		return paints.get(name);
	}
	
	private PaintKit(){
		paints=new HashMap<>();
	}
}
