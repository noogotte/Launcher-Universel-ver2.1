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

import java.io.IOException;
import java.util.Properties;

public class Language
{

		public static String getProperty(String string)
		
		
		{
			Properties config = new Properties();
			try
			{
				config.load(Config.class.getResourceAsStream("/fr/eelite/lang/"+Config.getProperty("lang")+".yml"));
				
				}
		 
			catch(IOException e)
			{
				e.printStackTrace();
				}
			return config.getProperty(string);
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