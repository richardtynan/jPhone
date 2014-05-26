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

public class AbisRslCChanHdr {

	private int ie_chan;

	private int chan_nr;

	private String data;

	public AbisRslCChanHdr(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.ie_chan = Integer.parseInt(str.nextToken());
		this.chan_nr = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(2, message);
	}

	public AbisRslCChanHdr(int ie_chan, int chan_nr, String data) {
		this.ie_chan = ie_chan;
		this.chan_nr = chan_nr;
		this.data = data;
	}

	public String toString() {
		return this.ie_chan + " " + this.chan_nr + " " + this.data;
	}
	
	public int getIe_chan() {
		return ie_chan;
	}

	public int getChan_nr() {
		return chan_nr;
	}

	public String getData() {
		return data;
	}
}
