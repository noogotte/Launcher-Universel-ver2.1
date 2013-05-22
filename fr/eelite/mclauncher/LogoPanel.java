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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Image bgImage;

	public LogoPanel()
	{
		setOpaque(true);
		try
		{
			BufferedImage src = ImageIO.read(LoginForm.class.getResourceAsStream("/theme"+"/"+Config.getProperty("theme")+"/"+Theme.getProperty("logo")));
			int w = src.getWidth();
			int h = src.getHeight();
			this.bgImage = src.getScaledInstance(w, h, 16);
			setPreferredSize(new Dimension(w + 32, h + 32));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paintComponent(Graphics g2)
	{
		g2.drawImage(this.bgImage, 24, 15, null);
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