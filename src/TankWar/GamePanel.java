package TankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;



import GameMap.GameResource;
import TankDate.AddBullet;
import TankDate.AddEnemyTank;
import TankDate.Addplayers;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Vector<AddBullet> bullets = new Vector<AddBullet>();
	public static Vector<AddEnemyTank> tanknum = new Vector<AddEnemyTank>();
	// public static Vector<Blast> blasts = new Vector<Blast>();
	public static boolean isgameover = false;
	public static boolean iswin = false;
	public static PanelMap drawmap;
	public Addplayers drawplayer;

	public void addtank() {
		for (int i = 0; i < 10; i++) {
			AddEnemyTank numt = new AddEnemyTank((int) (Math.random() * 30 + 1), 1, (int) (Math.random() * 4), this);
			tanknum.add(numt);

			new Thread(numt).start();
		}

	}

	public GamePanel() {
		setBackground(Color.BLACK);
		drawmap = new PanelMap(this);
		drawplayer = new Addplayers(this);
		addtank();
		new Thread(drawmap).start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 绘制地图
		drawmap.DrawMap(g);
		// 绘制玩家
		drawplayer.Drawplayers(g);
		// 绘制敌方坦克
		if (isgameover) {
			g.drawImage(GameResource.gameover, 0, 0, 1056, 832, 0, 0, 256, 160, this);
		} else if (iswin) {
			//g.drawImage(GameResource.win, 0, 0, 1056, 832, 0, 0, 256, 160, this);
		} else if(!isgameover){
			for (int u = 0; u < tanknum.size(); u++) {
				tanknum.get(u).Drawenemytank(g);
			}
			// 绘制子弹
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).drawbullel(g);
			}
		}
	}

	// 游戏胜利
	public static void GameWin() {
			iswin=true;
		
	}

	// 结束游戏后结果
	public static void GameOver() {
		isgameover = true;

		for (int i = 0; i < tanknum.size(); i++) {
			AddEnemyTank a = tanknum.get(i);
			a.istankover = true;
			tanknum.remove(a);
		}
		for (int u = 0; u < bullets.size(); u++) {
			bullets.get(u).isbulletover = true;
			bullets.remove(u);
		}
	}

}
