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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;


public class Util {
	private static File workDir = null;

	public static File getWorkingDirectory() {
		if (workDir == null)
			workDir = getWorkingDirectory(GamePath.getProperty("gamepath"));
		return workDir;
	}

	public static File getWorkingDirectory(String applicationName) {
		String dot = "";
		if(Config.USE_DOT)
			dot = ".";

		String userHome = System.getProperty("user.home", dot);
		File workingDirectory;
		if (getPlatform() == OS.solaris || getPlatform() == OS.linux) {
			workingDirectory = new File(userHome, dot + applicationName + '/');
		} else if (getPlatform() == OS.windows) {
			String applicationData = System.getenv("APPDATA");
			if (applicationData != null)
				workingDirectory = new File(applicationData, dot + applicationName + '/');
			else
				workingDirectory = new File(userHome, dot + applicationName + '/');
		} else if (getPlatform() == OS.macos) {
			workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
		} else {
			workingDirectory = new File(userHome, applicationName + '/');
		}

		if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs()))
			throw new RuntimeException("Le répertoire de travail n'a pas pu être créé: " + workingDirectory);
		return workingDirectory;
	}

	private static OS getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win"))
			return OS.windows;
		if (osName.contains("mac"))
			return OS.macos;
		if (osName.contains("solaris"))
			return OS.solaris;
		if (osName.contains("sunos"))
			return OS.solaris;
		if (osName.contains("linux"))
			return OS.linux;
		if (osName.contains("unix"))
			return OS.linux;
		return OS.unknown;
	}

	public static String executePost(String targetURL, String urlParameters) {
		HttpsURLConnection connection = null;
		try {
			URL url = new URL(targetURL);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			connection.connect();
			Certificate[] certs = connection.getServerCertificates();

			byte[] bytes = new byte[294];
			DataInputStream dis = new DataInputStream(Util.class.getResourceAsStream("minecraft.key"));
			dis.readFully(bytes);
			dis.close();

			Certificate c = certs[0];
			PublicKey pk = c.getPublicKey();
			byte[] data = pk.getEncoded();

			for (int i = 0; i < data.length; i++) {
				if (data[i] == bytes[i])
					continue;
				throw new RuntimeException("Les clés publics sont différentes");
			}

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			StringBuffer response = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String str1 = response.toString();
			return str1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null)
				connection.disconnect();
		}
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static void openLink(URI uri) {
		try {
			Object o = Class.forName("java.awt.Desktop").getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
			o.getClass().getMethod("browse", new Class[] { URI.class }).invoke(o, new Object[] { uri });
		} catch (Throwable e) {
			System.out.println("Erreur Ã  l'ouverture du lien " + uri.toString());
		}
	}

	private static enum OS {
		linux, solaris, windows, macos, unknown;
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