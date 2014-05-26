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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.StringTokenizer;
import java.util.Vector;

public class PMConfMessage {

	private Vector<Integer> arfcn;

	private Vector<Integer> rx_lev;

	public PMConfMessage(String message) {
		this.arfcn = new Vector<Integer>();
		this.rx_lev = new Vector<Integer>();

		StringTokenizer str = new StringTokenizer(message);
		while (str.hasMoreTokens()) {
			int ahigh = Integer.parseInt(str.nextToken());
			int alow = Integer.parseInt(str.nextToken());
			int lhigh = Integer.parseInt(str.nextToken());
			Integer.parseInt(str.nextToken());
			ByteBuffer bb = ByteBuffer.allocate(2);
			bb.order(ByteOrder.LITTLE_ENDIAN);
			bb.put((byte) alow);
			bb.put((byte) ahigh);
			int ar = bb.getShort(0);
			int lev = (110 - lhigh);
			this.arfcn.addElement(ar);
			this.rx_lev.addElement(lev);
		}
	}

	public Vector<Integer> getArfcn() {
		return arfcn;
	}

	public Vector<Integer> getRx_lev() {
		return rx_lev;
	}

}
