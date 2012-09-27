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

public class PagingRequest1 {
	
	private int l2plen;
	private int disc;
	private int type;
	
	private int pag_mode;
	private int spare;
	private int cneed1;
	private int cneed2;

	private String data;

	public PagingRequest1(String message) {
		StringTokenizer str = new StringTokenizer(message);

		l2plen = Integer.parseInt(str.nextToken());
		disc = Integer.parseInt(str.nextToken());
		type = Integer.parseInt(str.nextToken());
		
		int temp = Integer.parseInt(str.nextToken());
		pag_mode = (temp & Integer.parseInt("11000000", 2)) >> 6;
		spare = (temp & Integer.parseInt("00110000", 2)) >> 4;
		cneed1 = (temp & Integer.parseInt("00001100", 2)) >> 2;
		cneed2 = (temp & Integer.parseInt("00000011", 2));
		
		data = Utils.chew(4, message);
	}
	
	public int getL2plen() {
		return l2plen;
	}

	public int getDisc() {
		return disc;
	}

	public int getType() {
		return type;
	}

	public int getPag_mode() {
		return pag_mode;
	}

	public int getSpare() {
		return spare;
	}

	public int getCneed1() {
		return cneed1;
	}

	public int getCneed2() {
		return cneed2;
	}

	public String getData() {
		return data;
	}
}
