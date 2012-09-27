/* (C) 2012 by Richard Tynan
*  (C) 2012 by Privacy International
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
package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class Layer1Message {

	private int type;

	private int flags;

	private String data;

	public Layer1Message(int type, int flags, String data) {
		super();
		this.type = type;
		this.flags = flags;
		this.data = data;
	}
	
	public Layer1Message(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.type = Integer.parseInt(str.nextToken());
		this.flags = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(4, message);
	}

	public int getFlags() {
		return flags;
	}

	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public String toString() {
		return type + " " + flags + " 0 0 " + data;
	}

}
