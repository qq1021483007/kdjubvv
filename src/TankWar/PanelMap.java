package TankWar;

import java.awt.Graphics;

import javax.swing.JPanel;

import GameMap.GameResource;
import GameMap.GameMap1;

//地图绘制
public class PanelMap implements Runnable {
	private int bossImageY = 0;
	private JPanel jp;

	public PanelMap(JPanel jp) {
		this.jp = jp;
	}

	public void DrawMap(Graphics g) {
		for (int y = 0; y < GameMap1.MAP.length; y++) {
			for (int x = 0; x < GameMap1.MAP[0].length; x++) {
				int num = GameMap1.MAP[y][x];
				switch (num) {
				case 0:
					// 不绘制
					break;
				case 1:
					// 砖头
					g.drawImage(GameResource.brick, +32 * x, +32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 32, 32, jp);
					break;
				case 2:
					// 铁
					g.drawImage(GameResource.fe, +32 * x, +32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 32, 32, jp);
					break;
				case 3:
					// 冰
					g.drawImage(GameResource.ice, +32 * x, +32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 64, 64, jp);
					break;
				case 4:
					// 草
					g.drawImage(GameResource.grass, +32 * x, +32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 87, 83, jp);
					break;
				case 5:
					// 水
					g.drawImage(GameResource.water, +32 * x, +32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 122, 122, jp);
					break;
				case 6:
					// 边框
					g.drawImage(GameResource.gray, +32 * x, +32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 74, 78, jp);
					break;
				case 7:
					// boss

					g.drawImage(GameResource.boss1, +32 * x, +32 * y, 32 * (x + 1), 32 * (y + 1), 0, bossImageY * 34,
							41, (bossImageY + 1) * 34, jp);
					break;
				}
			}
		}
	}

	@Override
	public void run() {

		while (!GamePanel.isgameover) {
			bossImageY++;

			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bossImageY == 11) {
				bossImageY = 0;
			}
			jp.repaint();
		}
	}

}
