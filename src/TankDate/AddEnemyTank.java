package TankDate;

import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

import GameMap.GameMap1;
import GameMap.GameResource;
import TankWar.GamePanel;

public class AddEnemyTank implements Runnable {

	public int x;
	public int y;
	private int imageX;
	private int imageY;
	private JPanel jp;
	@SuppressWarnings("unused")
	private int sign;
	public boolean istankover = true;

	public AddEnemyTank(int x, int y, int sign, JPanel jp) {
		super();
		this.x = x;
		this.y = y;
		this.sign = sign;
		imageY = (int) (sign);
		imageX = sign;
		this.jp = jp;

	}

	public void Drawenemytank(Graphics g) {
		// System.out.print(imageX);
		g.drawImage(GameResource.enemytank, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32, imageX * 28, imageY * 28,
				(imageX + 1) * 28, (imageY + 1) * 28, jp);

		if (GameMap1.MAP[y][x] == 4) {
			g.drawImage(GameResource.grass, x * 32 - 5, y * 32 - 5, (x + 1) * 32 + 5, (y + 1) * 32 + 5, 0, 0, 83, 81,
					jp);
		}
	}

	@Override
	public void run() {
		while (istankover) {
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (imageX % 2 == 0) {
				imageX += 1;
			} else {
				imageX -= 1;
			}
			ymove();
			enemyfire();
		}
	}

	public void ymove() {
		switch (imageY) {
		case 0:
			if (canmove(x, y - 1)) {
				y--;
			}
			break;
		case 1:
			if (canmove(x + 1, y)) {
				x++;
			}
			break;
		case 2:
			if (canmove(x, y + 1)) {
				y++;
			}
			break;
		case 3:
			if (canmove(x - 1, y)) {
				x--;
			}
			break;

		}
	}

	public void enemyfire() {
		AddBullet ebuttel = new AddBullet(x, y, imageY, 0, jp);
		GamePanel.bullets.add(ebuttel);
		new Thread(ebuttel).start();

	}

	public boolean canmove(int x, int y) {

		if (Arrays.asList(0, 3, 4).contains(GameMap1.MAP[y][x]) && isgo(x, y)) {
			return true;
		} else {
			while (true) {
				int turnto = (int) (Math.random() * 4);
				if (turnto != imageY) {
					imageY = turnto;
					break;
				}
			}

		}
		return false;

	}

	public boolean isgo(int x, int y) {
		for (int i = 0; i < GamePanel.tanknum.size(); i++) {
			AddEnemyTank numts = GamePanel.tanknum.get(i);
			if (x == numts.x && y == numts.y) {
				return false;
			}

		}
		if (Addplayers.playerX == x && Addplayers.playerY == y) {
			return false;
		}

		return true;

	}
}
