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
package jphone.gui;

import jphone.Constants;
import jphone.Mobile;

public class ScanThread extends Thread {

	private Mobile mobile;

	private int num;
	
	private int wait;

	public ScanThread(Mobile mobile, int num, int wait) {
		this.mobile = mobile;
		this.num = num;
		this.wait = wait;
	}

	public void run() {
		for (int i = 0; i < num; i++) {
			this.mobile.getLayer1().tx_reset_req(Constants.L1CTL_RES_T_FULL);
			this.tune(this.mobile.getMobileStation().getArfcn(i));
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		this.mobile.getLayer1().tx_reset_req(Constants.L1CTL_RES_T_FULL);
		this.mobile.setState(Constants.MOBILE_IDLE);
	}

	public void tune(int arfcn) {
		System.out.println("Tuning to " + arfcn);
		int timeout = 100;
		int flags = Constants.L1CTL_FBSB_F_FB01SB;
		int index = 0;
		int ccch_mode = Constants.CCCH_MODE_NONE;

		int arfcnLow = (arfcn & 0xFF);
		int arfcnHigh = ((arfcn >> 8) & 0xFF);

		int timeLow = (timeout & 0xFF);
		int timeHigh = ((timeout >> 8) & 0xFF);

		this.mobile.getLayer1().tx_fbsb_req(arfcnHigh, arfcnLow, flags,
				timeHigh, timeLow, index, ccch_mode);
	}
}
