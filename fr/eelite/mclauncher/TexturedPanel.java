/*
 * Launcher Universel / Universal Launcher By EElite Magister
 * 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package fr.eelite.mclauncher;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TexturedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;
	private Image bgImage;

	public TexturedPanel() {
		setOpaque(true);
		try {
			this.bgImage = ImageIO.read(LoginForm.class.getResourceAsStream("/theme"+"/"+Config.getProperty("theme")+"/"+Theme.getProperty("bgimage1"))).getScaledInstance(32, 32, 16);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paintComponent(Graphics g2) {
		int w = getWidth() / 2 + 1;
		int h = getHeight() / 2 + 1;
		if ((this.img == null) || (this.img.getWidth(null) != w) || (this.img.getHeight(null) != h)) {
			this.img = createImage(w, h);

			Graphics g = this.img.getGraphics();
			for (int x = 0; x <= w / 32; x++) {
				for (int y = 0; y <= h / 32; y++)
					g.drawImage(this.bgImage, x * 32, y * 32, null);
			}

			if ((g instanceof Graphics2D)) {
				Graphics2D gg = (Graphics2D) g;
				int gh = 1;
				gg.setPaint(new GradientPaint(new Point2D.Float(0.0F, 0.0F), new Color(553648127, true), new Point2D.Float(0.0F, gh), new Color(0, true)));
				gg.fillRect(0, 0, w, gh);

				gh = h;
				gg.setPaint(new GradientPaint(new Point2D.Float(0.0F, 0.0F), new Color(0, true), new Point2D.Float(0.0F, gh), new Color(1610612736, true)));
				gg.fillRect(0, 0, w, gh);
			}
			g.dispose();
		}
		g2.drawImage(this.img, 0, 0, w * 2, h * 2, null);
	}
}

/*
 *             ______  ______  __      __  __________   ______                                   
 *            / ____/ / ____/ / /     / / /___  ____/  / ____/                                   
 *           / /___  / /___  / /     / /     / /      / /___                                     
 *          / ____/ / ____/ / /     / /     / /      / ____/                                     
 *    __   / /___  / /___  / /___  / /     / /      / /___                                       
 *   / /  /_____/ /_____/ /_____/ /_/     /_/      /_____/                                       
 *  / /______________________________________________________                                    
 * /________________________________________________________/   M   A   G   I   S   T   E   R 
 * 
 * 
 */