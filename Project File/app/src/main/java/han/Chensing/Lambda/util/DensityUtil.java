package han.Chensing.Lambda.util;
import android.content.*;

public class DensityUtil
{
	private static DensityUtil i;
	private float scale;
	
	public static DensityUtil get(){
		if(i==null)
			i=new DensityUtil();
		return i;
	}
	
	public void init(Context con){
		scale = con.getResources().getDisplayMetrics().density;
	}
    public int dp2px(float dpValue) { 
		return (int) (dpValue * scale + 0.5f); 
    } 

	public int px2dp(float pxValue){
		return (int) (pxValue / scale + 0.5f);
	}
	private DensityUtil(){
		
	}
}
