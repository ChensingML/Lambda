package han.Chensing.Lambda.elements;
import android.graphics.*;
import han.Chensing.Lambda.*;

public class ClickNote extends Note
{

	public ClickNote(){
		currentPaints=new Paint[]{
			PaintKit.getInstance().get("clickP"),
			PaintKit.getInstance().get("clickInP")
		};
		currentPaths=new Path[]{
			new Path(cpath),new Path(cpathIn)
		};
	}
	
	@Override
	public void draw(Canvas can)
	{
		if(isClicked())
			return;
		super.draw(can);
	}

	@Override
	public void logic()
	{
		super.logic();
	}
	
}
