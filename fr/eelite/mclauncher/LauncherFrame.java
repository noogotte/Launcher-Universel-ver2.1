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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class LauncherFrame extends Frame
{
	public static final int VERSION = 13;
	private static final long serialVersionUID = 1L;
	public Map<String, String> customParameters = new HashMap<String, String>();
	public Launcher launcher;
	public LoginForm loginForm;
	
	public LauncherFrame()
	{
		super("Minecraft");
		
		setTitle(Theme.getProperty("launchertitle"));
		
		setBackground(Color.BLACK);
		this.loginForm = new LoginForm(this);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(this.loginForm, "Center");
		p.setPreferredSize(new Dimension(Integer.parseInt(Theme.getProperty("width")), Integer.parseInt(Theme.getProperty("height"))));
		setLayout(new BorderLayout());
		add(p, "Center");
		
		pack();
		setLocationRelativeTo(null);
		try
		{
			setIconImage(ImageIO.read(LauncherFrame.class.getResourceAsStream("/theme"+"/"+Config.getProperty("theme")+"/"+Theme.getProperty("favicon"))));
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0)
			{
				new Thread(){
					public void run()
					{
						try
						{
							Thread.sleep(30000L);
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
						System.out.println("FORCING EXIT!");
						System.exit(0);
					}
				}.start();
				if(LauncherFrame.this.launcher != null)
				{
					LauncherFrame.this.launcher.stop();
					LauncherFrame.this.launcher.destroy();
				}
				System.exit(0);
			}
		});
	}
	
	public void playCached(String userName)
	{
		try
		{
			if((userName == null) || (userName.length() <= 0))
			{
				userName = "Player";
			}
			this.launcher = new Launcher();
			this.launcher.customParameters.putAll(this.customParameters);
			this.launcher.customParameters.put("userName", userName);
			this.launcher.customParameters.put("latestVersion", "2");
			this.launcher.init();
			removeAll();
			add(this.launcher, "Center");
			validate();
			this.launcher.start();
			this.loginForm = null;
			setTitle(Theme.getProperty("gametitle"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			showError(e.toString());
		}
	}
	
	public void login(String userName, String password)
	{
		try
		{
			String parameters = "user=" + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&version=" + 13;
			String result = Util.excutePost("https://login.minecraft.net/", parameters);
			if(result == null)
			{
				showError("Impossible de se connecter ра minecraft.net");
				this.loginForm.setNoNetwork();
				return;
			}
			if(!result.contains(":"))
			{
				if(result.trim().equals("Mauvais mot de passe"))
				{
					showError("Echec de l'identification");
				}
				else if(result.trim().equals("Trop vieille version du Launcher"))
				{
					this.loginForm.setOutdated();
					showError("Trop vieille version du Launcher");
				}
				else
				{
					showError(result);
				}
				this.loginForm.setNoNetwork();
				return;
			}
			String[] values = result.split(":");
			
			this.launcher = new Launcher();
			this.launcher.customParameters.putAll(this.customParameters);
			this.launcher.customParameters.put("userName", values[2].trim());
			this.launcher.customParameters.put("latestVersion", values[0].trim());
			this.launcher.customParameters.put("downloadTicket", values[1].trim());
			this.launcher.customParameters.put("sessionId", values[3].trim());
			this.launcher.init();
			
			removeAll();
			add(this.launcher, "Center");
			validate();
			this.launcher.start();
			this.loginForm.loginOk();
			this.loginForm = null;
			setTitle(Config.getProperty("gametitle"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			showError(e.toString());
			this.loginForm.setNoNetwork();
		}
	}
	
	private void showError(String error)
	{
		removeAll();
		add(this.loginForm);
		this.loginForm.setError(error);
		validate();
	}
	
	public boolean canPlayOffline(String userName)
	{
		if(Config.getProperty("canplayoffline").equals("true"))
		{
			Launcher launcher = new Launcher();
			launcher.customParameters.putAll(this.customParameters);
			launcher.init(userName, null, null, null);
			return launcher.canPlayOffline();
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception localException)
		{
		}
		LauncherFrame launcherFrame = new LauncherFrame();
		launcherFrame.setVisible(true);
		launcherFrame.customParameters.put("stand-alone", "true");
		
		if(Config.getProperty("autoconnect").equals("true"))
		{
			launcherFrame.customParameters.put("server", Config.getProperty("server-ip"));
			launcherFrame.customParameters.put("port", Config.getProperty("server-port"));
		}
		
		if(args.length >= 1)
		{
			launcherFrame.loginForm.userName.setText(args[0]);
			if(args.length >= 2)
			{
				launcherFrame.loginForm.password.setText(args[1]);
				launcherFrame.loginForm.doLogin();
			}
		}
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
