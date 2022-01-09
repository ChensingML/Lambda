package han.Chensing.Lambda.elements;

public abstract class DyingElement extends Element
{
	protected float dyingRate;
	protected float dying;

	public void setDying(float dying){
		this.dying = dying;
	}

	public float getDying(){
		return dying;
	}
	
	public boolean isDead(){
		return dying>=1;
	}

	@Override
	public void logic()
	{
		super.logic();
		if(!isDead()){
			float nd=dying+dyingRate;
			dying=nd>=1?1:nd;
		}
	}

}
