/* (C) 2012 by Richard Tynan
 *
 * All Rights Reserved
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Copyright {

	public static String copyright = "/* (C) 2012 by Richard Tynan\n"
			+ "*\n"
			+ "* All Rights Reserved\n"
			+ "*\n"
			+ "* This program is free software; you can redistribute it and/or modify\n"
			+ "* it under the terms of the GNU General Public License as published by\n"
			+ "* the Free Software Foundation; either version 2 of the License, or\n"
			+ "* (at your option) any later version.\n"
			+ "*\n"
			+ "* This program is distributed in the hope that it will be useful,\n"
			+ "* but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
			+ "* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
			+ "* GNU General Public License for more details.\n"
			+ "*\n"
			+ "* You should have received a copy of the GNU General Public License along\n"
			+ "* with this program; if not, write to the Free Software Foundation, Inc.,\n"
			+ "* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.\n"
			+ "*\n" + "*/\n";

	public static void main(String[] args) throws Exception {
		String dir = "src/";
		File file = new File(dir);
		processDirectory(file);
	}
	
	public static void processDirectory(File dir) throws Exception {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				processDirectory(files[i]);
			} else {
				FileReader reader = new FileReader(files[i]);
				String contents = "";
				int read = reader.read();
				while (read != -1) {
					contents += (char) read;
					read = reader.read();
				}
				reader.close();
				if(!contents.startsWith(copyright)) {
					contents = copyright + contents;
					FileWriter writer = new FileWriter(files[i]);
					writer.write(contents);
					writer.close();
				}
			}
		}
	}
}
