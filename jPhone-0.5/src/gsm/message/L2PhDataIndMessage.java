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

public class L2PhDataIndMessage {

	private int arfcn;
	private int chan_nr;
	private int link_id;
	private String data;

	public L2PhDataIndMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		arfcn = Integer.parseInt(str.nextToken());
		chan_nr = Integer.parseInt(str.nextToken());
		link_id = Integer.parseInt(str.nextToken());
		data = Utils.chew(3, message);
	}

	public int getArfcn() {
		return arfcn;
	}

	public int getChan_nr() {
		return chan_nr;
	}

	public int getLink_id() {
		return link_id;
	}

	public String getData() {
		return data;
	}
}
