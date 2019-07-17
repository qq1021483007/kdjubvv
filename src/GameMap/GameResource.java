package GameMap;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameResource {
	public static int bossImageY;
	public static int bossImageX;

	public static Image grass;
	public static Image brick;
	public static Image fe;
	public static Image water;
	public static Image ice;
	public static Image gray;
	public static Image tank1;
	public static Image tank2;
	public static Image boss;
	public static Image boss1;
	public static Image enemytank;
	public static Image tank0;
	public static Image bullet1;
	public static Image baozha;
	public static Image gameover;
	public static Image blase1;
	public static Image win;
	static {
		try {
			grass = ImageIO.read(new File("img/grass.png"));
			brick = ImageIO.read(new File("img/brick.png"));
			fe = ImageIO.read(new File("img/fe.png"));
			water = ImageIO.read(new File("img/water.jpg"));
			ice = ImageIO.read(new File("img/ice.png"));
			gray = ImageIO.read(new File("img/gray.png"));
			boss = ImageIO.read(new File("img/boss.png"));
			boss1 = ImageIO.read(new File("img/boss1.gif"));
			tank1 = ImageIO.read(new File("img/tank1.jpg"));
			tank2 = ImageIO.read(new File("img/tank2.png"));
			tank0 = ImageIO.read(new File("img/tank0.png"));
			bullet1=ImageIO.read(new File("img/bullet1.png"));
			enemytank = ImageIO.read(new File("img/enemytank.bmp"));
			baozha = ImageIO.read(new File("img/baozha.png"));
			gameover = ImageIO.read(new File("img/gameover.bmp"));
			blase1=ImageIO.read(new File("img/blase1.png"));
			win = ImageIO.read(new File("img/win.bmp"));
			//s  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
