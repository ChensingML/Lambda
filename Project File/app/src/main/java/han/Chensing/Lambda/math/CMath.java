package han.Chensing.Lambda.math;
import han.Chensing.Lambda.*;
import han.Chensing.Lambda.elements.*;
import han.Chensing.Lambda.util.*;

public class CMath
{
	//Result as dp[x,y], use RAD
	//计算射线与屏幕边缘的交点
	public static double[] crossPointWithScreen(double x,double y,double direction){
		//写个注释怕自己忘了(doge)
		double[] res=new double[2];
		//计算角度卦限
		int gx=getAngGX(direction);
		switch(gx){
			case 1:{
				//在卦限I，tan可取无限小
				//且取屏幕右边缘坐标
				if(x>=CSV.WIDTH)//在右边缘之外的点排掉
					return null;
				double tan=Math.sin(direction)/Math.cos(direction);
				//离右边缘的距离(+)
				double dist=CSV.WIDTH-x;
				//y的增长量
				//因Android坐标系，需要反转
				double dy=-(tan*dist);
				res[0]=CSV.WIDTH;
				res[1]=y+dy;
				break;
			}
			case 2:{
				//在卦限II，cot可取无限小
				//且取屏幕上边缘坐标
				if(y<=0)//在上边缘之外的点排掉
					return null;
				double cot=Math.cos(direction)/Math.sin(direction);
				//离上边缘的距离(+)(即y)
				//x的增长量，横坐标无需反转
				double dx=cot*y;
				res[0]=x+dx;
				res[1]=0;
				break;
			}
			case 3:{
				//在卦限III，tan可取无限小
				//且取屏幕左边缘坐标
				if(x<=0)//在左边缘之外的点排掉
					return null;
				double tan=Math.sin(direction)/Math.cos(direction);
				//离左边缘的距离(+)(即x)
				//y的增长量，需要反转
				double dy=(tan*x);
				res[0]=0;
				res[1]=y+dy;
				break;
			}
			case 4:{
				//在卦限IV，cot可取无限小
				//且取屏幕下边缘坐标
				if(y>=CSV.HEIGHT)//在下边缘之外的点排掉
					return null;
				double cot=Math.cos(direction)/Math.sin(direction);
				//离下边缘的距离
				double dist=CSV.HEIGHT-y;
				//x的增长量，无需反转
				double dx=-(cot*dist);
				res[0]=x+dx;
				res[1]=CSV.HEIGHT;
				break;
			}
			default:
				res=null;
		}
		return res;
	}
	
	//将任意角转换为0-2pi的角
	public static double toNormalAng(double orn){
		double ted=orn;
		if(ted>=0&&ted<Math.PI*2){//是否在0-2pi(不含2pi)
			return ted;
		}
		if(orn<0){//负角
			ted+=Math.PI*2;
		}else if(orn>=Math.PI*2){//正角(含2pi)
			ted-=Math.PI*2;
		}
		//防止角太大，再递归
		return toNormalAng(ted);
	}
	
	//判断角度(RAD)所在卦限（角度必须在0-2pi）
	public static int getAngGX(double direction){
		if(
			(direction>=0&&direction<=(Math.PI/4))||
			(direction>=(Math.PI*7)/4&&direction<=Math.PI*2)
		){//第一卦限：pi/4 - -pi/4
			return 1;
		}else if(
			(direction>=(Math.PI/4)&&direction<(Math.PI*3)/4)
		){//第二卦限：pi/4 - (3/4)pi
			return 2;
		}else if(
			(direction>=(Math.PI*3)/4&&direction<(Math.PI*5)/4)
		){//第三卦限：(3/4)pi - (5/4)pi
			return 3;
		}else if(
			(direction>=(Math.PI*5)/4&&direction<(Math.PI*7)/4)
		){//第四卦限：(5/4)pi - (7/4)pi
			return 4;
		}else{
			return -1;
		}
	}
	
	//判断角度(RAD)所在象限（角度必须在0-2pi）
	public static int getAngXX(double direction){
		if(direction>0&&direction<Math.PI/2){
			return 1;
		}else if(direction>Math.PI/2&&direction<Math.PI){
			return 2;
		}else if(direction>Math.PI&&direction<(Math.PI*3)/2){
			return 3;
		}else if(direction>(Math.PI*3)/2&&direction<Math.PI*2){
			return 4;
		}else if(direction==0){
			return 0;
		}else if(direction==Math.PI/2){
			return 90;
		}else if(direction==Math.PI){
			return 180;
		}else if(direction==(Math.PI*3)/2){
			return 270;
		}else{
			return -1;
		}
	}
	
	//判定音符是否被点击
	public static boolean isTouchOn(float x,float y,Note note){
		double a=note.isUsingLineSet()?
			note.getPositionLine()>=0?
				note.getBindLineSet().getRight().getDirection():
				note.getBindLineSet().getLeft().getDirection()
			:note.getBindLine().getDirection();
		int gx=getAngGX(a);
		double pxx=DensityUtil.get().dp2px(note.getX());
		double pxy=DensityUtil.get().dp2px(note.getY());
		double d=DensityUtil.get().dp2px(Note.clickDistance);
		double sina=Math.sin(a);
		double cosa=Math.cos(a);
		switch(gx){
			case 1:{
				double tana=sina/cosa;
				double bc=(d*2)/cosa;
				double bd=cosa*d;
				double ad=sina*d;
				double by=pxy+ad;
				double be=y-by;
				double ef=tana*be;
				double bx=pxx-bd;
				double fx=bx+ef;
				double gxa=fx+bc;
				return (fx<=x&&gxa>=x);
			}
			case 2:{
				double cota=cosa/sina;
				double bc=(2*d)/sina;
				double ad=cosa*d;
				double bd=sina*d;
				double bx=pxx+ad;
				double by=pxy-bd;
				double be=x-bx;
				double eh=cota*be;
				double hy=by+eh;
				double iy=hy+bc;
				return (hy<=y&&iy>=y);
			}
			case 3:{
				double tana=sina/cosa;
				double bc=(d*2)/cosa;
				double bd=cosa*d;
				double ad=sina*d;
				double by=pxy+ad;
				double be=y-by;
				double ef=tana*be;
				double bx=pxx-bd;
				double fx=bx+ef;
				double gxa=fx+bc;
				return (fx>=x&&gxa<=x);
			}
			case 4:{
				double cota=cosa/sina;
				double bc=(2*d)/sina;
				double ad=cosa*d;
				double bd=sina*d;
				double bx=pxx+ad;
				double by=pxy-bd;
				double be=x-bx;
				double eh=cota*be;
				double hy=by+eh;
				double iy=hy+bc;
				return (hy>=y&&iy<=y);
			}
			default:{
				System.out.println();
			}
		}
		return false;
	}
}
