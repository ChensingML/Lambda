package han.Chensing.Lambda;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
