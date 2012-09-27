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
