package TankDate;

import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

import GameMap.GameMap1;
import GameMap.GameResource;
import TankWar.GamePanel;

public class AddBullet implements Runnable {
	public int x;
	public int y;
	private int imageX;
	public boolean isbulletover = true;
	private JPanel jp;
	public int sign;
	public boolean iseffect = true;

	public AddBullet() {
		super();
	}

	public AddBullet(int x, int y, int imageX, int sign, JPanel jp) {
		super();
		this.x = x;
		this.y = y;
		this.sign = sign;
		this.imageX = imageX;
		this.jp = jp;
	}

	public void drawbullel(Graphics g) {
		if (sign == 3) {
			g.drawImage(GameResource.blase1, x * 32, y * 32, (x + 4) * 32, (y + 4) * 32, 0, 0, 500, 457, jp);
		} else if (sign == 0 || sign == 1) {
			int imageX = change(this.imageX);
			g.drawImage(GameResource.bullet1, x * 32, 32 * y, (x + 1) * 32, (y + 1) * 32, imageX * 32, 0,
					(imageX + 1) * 32, 32, jp);
		}
	}

	private int change(int x) {
		switch (x) {
		case 0:
			return 3;
		case 1:
			return 0;
		case 2:
			return 1;
		case 3:
			return 2;
		default:
			break;
		}
		return x;
	}

	@Override
	public void run() {
		while (isbulletover) {
			move();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public void move() {
		switch (imageX) {
		case 0:
			y--;
			break;
		case 1:
			x++;
			break;
		case 2:
			y++;
			break;
		case 3:
			x--;
			break;
		}
		bulletshoot();

	}

	public void bulletshoot() {

		if (Arrays.asList(1, 2, 6, 7).contains(GameMap1.MAP[y][x])) {
			if (GameMap1.MAP[y][x] == 1) {
				GameMap1.MAP[y][x] = 0;
			}
			if (GameMap1.MAP[y][x] == 7) {
				GameMap1.MAP[y][x] = 0;
				Addplayers.isplayer = false;
				GamePanel.bullets.remove(this);
				GamePanel.GameOver();
			}
			isbulletover = false;
			GamePanel.bullets.remove(this);
		} else {
			if (sign == 0) {
				shootplayer();
			} else if (sign == 1) {
				shootenemy();
			} else if (sign == 3) {
				Allkill();
			}
		}

	}

	/**
	 *  射击敌方坦克 sign==1
	 */
	public void shootenemy() {
		for (int i = 0; i < GamePanel.tanknum.size(); i++) {
			AddEnemyTank etank = GamePanel.tanknum.get(i);
			if (x == etank.x && y == etank.y) {
				isbulletover = false;
				etank.istankover = false;
				GamePanel.bullets.remove(this);
				GamePanel.tanknum.remove(etank);
				return;
			}
		}
	}

	/**
	 *  敌方坦克攻击玩家 sign==0
	 */
	public void shootplayer() {
		if (x == Addplayers.playerX && y == Addplayers.playerY) {
			isbulletover = true;
			Addplayers.isplayer = false;
			GamePanel.GameOver();
		}
	}

	// 消灭敌方所有坦克 sign==3
	@SuppressWarnings("unused")
	public void Allkill() {
		isbulletover = false;

		for (int u = 0; u < GamePanel.bullets.size(); u++) {
			AddBullet allb = GamePanel.bullets.get(u);
			allb.isbulletover=false;
			GamePanel.bullets.remove(u);
		}
		for (int i = 0; i < GamePanel.tanknum.size(); i++) {
			AddEnemyTank allt = GamePanel.tanknum.get(i);
			allt.istankover = false;
			GamePanel.tanknum.remove(i);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GamePanel.GameWin();

	}

	// 无效子弹 sign==2
	public void dongshoot() {

	}
}