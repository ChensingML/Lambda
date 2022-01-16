package han.Chensing.Lambda.level;
import java.io.*;

public class Level implements Serializable
{
	private int difficulty;
	private String message;
	private byte[] script;
	
	private transient LevelScript lScript;
	
	public void solveScript() throws Exception{
		ByteArrayInputStream bai=new ByteArrayInputStream(script);
		ObjectInputStream ois=new ObjectInputStream(bai);
		this.lScript=(LevelScript)ois.readObject();
	}
}
