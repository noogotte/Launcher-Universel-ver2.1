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

import java.util.ArrayList;

public class MinecraftLauncher
{
	public static void main(String[] args) throws Exception
	{
		float heapSizeMegs = (float) (Runtime.getRuntime().maxMemory() / 1024L / 1024L);

		if (heapSizeMegs > 511.0F)
			LauncherFrame.main(args);
		else
			try
			{
				String pathToJar = MinecraftLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

				ArrayList<String> params = new ArrayList<String>();

				params.add("javaw");
				params.add("-Xmx" + Config.getProperty("maxsize_memory") + "m");
				params.add("-Dsun.java2d.noddraw=true");
				params.add("-Dsun.java2d.d3d=false");
				params.add("-Dsun.java2d.opengl=false");
				params.add("-Dsun.java2d.pmoffscreen=false");

				params.add("-classpath");
				params.add(pathToJar);
				params.add("net.minecraft.LauncherFrame");
				ProcessBuilder pb = new ProcessBuilder(params);
				Process process = pb.start();
				if (process == null)
					throw new Exception("!");
				System.exit(0);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				LauncherFrame.main(args);
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