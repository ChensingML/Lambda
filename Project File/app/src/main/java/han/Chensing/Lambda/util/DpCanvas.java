package han.Chensing.Lambda.util;
import android.graphics.*;
import android.view.*;

public class DpCanvas
{
	public static void drawLine(Canvas canvas,float startX, float startY, float stopX, float stopY, Paint paint)
	{
		DensityUtil d=DensityUtil.get();
		canvas.drawLine(
			d.dp2px(startX),
			d.dp2px(startY),
			d.dp2px(stopX),
			d.dp2px(stopY), paint);
	}

	public static void drawPaths(Canvas canvas,Path[] path, Paint[] paint)
	{
		if(path.length!=paint.length)
			return;
		for(int i=0;i<path.length;i++){
			canvas.drawPath(path[i],paint[i]);
		}
	}
}
