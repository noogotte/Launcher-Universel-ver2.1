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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OptionsPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton forceButton;

	public OptionsPanel(Frame parent) {
		super(parent);

		setModal(true);

		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Options", 0);
		label.setBorder(new EmptyBorder(0, 0, 16, 0));
		label.setFont(new Font("Default", 1, 16));
		panel.add(label, "North");

		JPanel optionsPanel = new JPanel(new BorderLayout());
		JPanel labelPanel = new JPanel(new GridLayout(0, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(0, 1));
		optionsPanel.add(labelPanel, "West");
		optionsPanel.add(fieldPanel, "Center");

		this.forceButton = new JButton("Forcer la mise ра jour");
		this.forceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				GameUpdater.forceUpdate = true;
				OptionsPanel.this.forceButton.setText("Ce sera fait !");
				OptionsPanel.this.forceButton.setEnabled(false);
			}
		});
		labelPanel.add(new JLabel("Forcer la mise ра jour: ", 4));
		fieldPanel.add(this.forceButton);

		labelPanel.add(new JLabel("Emplacement des fichiers: ", 4));
		TransparentLabel dirLink = new TransparentLabel(Util.getWorkingDirectory().toString()){
			private static final long serialVersionUID = 0L;

			public void paint(Graphics g) {

				super.paint(g);

				int x = 0;
				int y = 0;

				FontMetrics fm = g.getFontMetrics();
				int width = fm.stringWidth(getText());
				int height = fm.getHeight();

				if(getAlignmentX() == 2.0F)
					x = 0;
				else if(getAlignmentX() == 0.0F)
					x = getBounds().width / 2 - width / 2;
				else if(getAlignmentX() == 4.0F)
					x = getBounds().width - width;
				y = getBounds().height / 2 + height / 2 - 1;

				g.drawLine(x + 2, y, x + width - 2, y);

			}

			public void update(Graphics g) {
				paint(g);
			}
		};
		dirLink.setCursor(Cursor.getPredefinedCursor(12));
		dirLink.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent arg0) {
				try {
					Util.openLink(new URL("file://" + Util.getWorkingDirectory().getAbsolutePath()).toURI());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		dirLink.setForeground(new Color(2105599));

		fieldPanel.add(dirLink);

		panel.add(optionsPanel, "Center");

		JPanel buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.add(new JPanel(), "Center");
		JButton doneButton = new JButton("Terminer");
		doneButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				OptionsPanel.this.setVisible(false);
			}
		});
		JLabel credits = new JLabel("Theme : "+(Theme.getProperty("theme-name")));
		buttonsPanel.add(doneButton, "East");
		buttonsPanel.add(credits, "West");
		buttonsPanel.setBorder(new EmptyBorder(16, 0, 0, 0));

		panel.add(buttonsPanel, "South");

		add(panel);
		panel.setBorder(new EmptyBorder(16, 24, 24, 24));
		pack();
		setLocationRelativeTo(parent);


		JLabel credits1 = new JLabel("Langue : "+(Language.getProperty("language.name")));
		buttonsPanel.add(doneButton, "East");
		buttonsPanel.add(credits1, "West");
		buttonsPanel.setBorder(new EmptyBorder(50,0, 0, 0));

		panel.add(buttonsPanel, "South");

		add(panel);
		panel.setBorder(new EmptyBorder(16, 24, 24, 24));
		pack();
		setLocationRelativeTo(parent);

		JLabel credits2 = new JLabel("Launcher Universel - " + (Config.getProperty("launchVer")) + "  par " + (Config.getProperty("author")));
		buttonsPanel.add(doneButton, "East");
		buttonsPanel.add(credits2, "West");
		buttonsPanel.setBorder(new EmptyBorder(75,0, 0, 0));

		panel.add(buttonsPanel, "South");

		add(panel);
		panel.setBorder(new EmptyBorder(16, 24, 24, 24));
		pack();
		setLocationRelativeTo(parent);
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