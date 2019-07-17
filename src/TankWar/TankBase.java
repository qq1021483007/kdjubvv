package TankWar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;



@SuppressWarnings("serial")
public class TankBase extends JFrame {

	public TankBase() {
		//获取当前显示屏幕
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//当前显示屏幕属性
		int height = (int) dim.height;
		int weight = (int) dim.width;
		//设置屏幕属性值
		setTitle("坦克大战");
		setBounds((weight-1024)/2,(height-786)/2,1056,832);
		//讲绘制的放进JFram
		GamePanel gp=new GamePanel();
		this.setContentPane(gp);
		
		this.addKeyListener(gp.drawplayer);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TankBase tkBase = new TankBase();
	}

}