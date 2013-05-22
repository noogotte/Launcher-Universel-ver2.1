package fr.eelite.mclauncher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class GameUpdaterThread extends Thread
{
	private URLConnection urlconnection;
	private InputStream[] is;

	public GameUpdaterThread(InputStream[] is, URLConnection urlconnection)
	{
		this.is = is;
		this.urlconnection = urlconnection;
	}

	public void run()
	{
		try
		{
			is[0] = urlconnection.getInputStream();
		}
		catch (IOException localIOException)
		{
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