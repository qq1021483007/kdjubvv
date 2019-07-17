package TankDate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JPanel;

import GameMap.GameMap1;
import GameMap.GameResource;
import TankWar.GamePanel;

public class Addplayers implements KeyListener {
	// 玩家X坐标
	public static int playerX = 11;
	// 玩家Y坐标
	public static int playerY = 23;
	// 源图X、Y坐标
	public static int balseY = 11;
	public static int balseX = 15;
	public static int balse = 0;
	private int playerImageY;
	public  static boolean isplayer = true;
	private JPanel jp;

	public Addplayers(JPanel jp) {
		this.jp = jp;

	}

	public void Drawplayers(Graphics g) {
		g.drawImage(GameResource.tank0, playerX * 32, playerY * 32, (playerX + 1) * 32, (playerY + 1) * 32, 0,
				(playerImageY) * 512, 520, (playerImageY + 1) * 512, jp);

		if (GameMap1.MAP[playerY][playerX] == 4) {
			g.drawImage(GameResource.grass, playerX * 32 - 5, playerY * 32 - 5, (playerX + 1) * 32 + 5,
					(playerY + 1) * 32 + 5, 0, 0, 87, 83, jp);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (isplayer) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (Arrays.asList(0, 3, 4).contains(GameMap1.MAP[playerY - 1][playerX])
						&& playermove(playerX, playerY - 1)) {
					playerY--;
				}
				playerImageY = 0;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if (Arrays.asList(0, 3, 4).contains(GameMap1.MAP[playerY + 1][playerX])
						&& playermove(playerX, playerY + 1)) {
					playerY++;
				}
				playerImageY = 2;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if (Arrays.asList(0, 3, 4).contains(GameMap1.MAP[playerY][playerX - 1])
						&& playermove(playerX - 1, playerY)) {
					playerX--;
				}
				playerImageY = 3;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if (Arrays.asList(0, 3, 4).contains(GameMap1.MAP[playerY][playerX + 1])
						&& playermove(playerX + 1, playerY)) {
					playerX++;
				}
				playerImageY = 1;
				break;
			case KeyEvent.VK_J:
			case KeyEvent.VK_SPACE:
				fire();
				break;
			case KeyEvent.VK_U:
				Allkill();
				break;
			}
			jp.repaint();
			
		}
	}
	public void Allkill() {
		AddBullet numk = new AddBullet( balseX, balseY, balse, 3, jp);
		GamePanel.bullets.add(numk);
		new Thread(numk).start();
	}
	private void fire() {
		AddBullet numb = new AddBullet(playerX, playerY, playerImageY, 1, jp);
		GamePanel.bullets.add(numb);
		new Thread(numb).start();

	}

	public boolean playermove(int x, int y) {
		for (int i = 0; i < GamePanel.tanknum.size(); i++) {
			AddEnemyTank tanks = GamePanel.tanknum.get(i);
			if (x == tanks.x && y == tanks.y) {
				return false;
			}

		}
		return true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}