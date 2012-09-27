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

public class StackMessage {
	
	private int layer;
	
	private int type;
	
	private String data;

	public StackMessage(int layer, int type, String data) {
		this.layer = layer;
		this.type = type;
		this.data = data;
	}
	
	public StackMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.layer = Integer.parseInt(str.nextToken());
		this.type = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(2, message);
	}

	public String toString() {
		return this.layer + " " + this.type + " " + this.data;
	}
	
	public int getLayer() {
		return layer;
	}

	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}

}
