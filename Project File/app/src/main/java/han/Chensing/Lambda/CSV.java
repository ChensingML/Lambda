package han.Chensing.Lambda;
import android.view.*;
import android.graphics.*;
import android.content.*;
import android.util.*;
import android.os.*;
import han.Chensing.Lambda.util.*;
import java.util.*;

public class CSV extends SurfaceView
					implements SurfaceHolder.Callback
{
	public static float WIDTH_X, HEIGHT_X,
	WIDTH, HEIGHT;

	public CSV(Context con,AttributeSet as){
		super(con,as);
		WindowManager windowManager =
			(WindowManager) getContext().getSystemService(
			Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
		float density=dm.density;
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        if (Build.VERSION.SDK_INT >= 19) {
            display.getRealSize(outPoint);
        } else {
            display.getSize(outPoint);
        }
        int mRealSizeWidth;
        int mRealSizeHeight;
        mRealSizeHeight = outPoint.y;
        mRealSizeWidth = outPoint.x;
        CSV.WIDTH_X = mRealSizeWidth;
        CSV.HEIGHT_X = mRealSizeHeight;
		CSV.WIDTH=mRealSizeWidth/density;
		CSV.HEIGHT=mRealSizeHeight/density;
		INIT();
		getHolder().addCallback(this);
	}
	
	private void INIT(){
		DensityUtil.get().init(Objects.requireNonNull(getContext()));
		PaintKit pk=PaintKit.getInstance();
		Paint lineP=new Paint();
		lineP.setStrokeWidth(5f);
		lineP.setColor(Color.parseColor("#FFFFFF"));
		pk.add("lineP",lineP);
		Paint clickInP=new Paint();
		clickInP.setAntiAlias(true);
		clickInP.setStrokeWidth(1f);
		clickInP.setColor(Color.rgb(11,195,255));
		pk.add("clickInP",clickInP);
		Paint clickP=new Paint();
		clickP.setStrokeWidth(1f);
		clickP.setColor(Color.parseColor("#FFFFFF"));
		pk.add("clickP",clickP);
	}

	@Override
	public void surfaceCreated(SurfaceHolder p1)
	{
		new WorkThread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder p1)
	{
	}

	@Override
	public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4)
	{
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		try{
		TouchContainer.get().solve(event);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
}
