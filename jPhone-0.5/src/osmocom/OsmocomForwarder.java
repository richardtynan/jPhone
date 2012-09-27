package osmocom;

import gsm.message.StackMessage;

import java.util.StringTokenizer;

import jphone.Constants;
import jphone.Mobile;

public class OsmocomForwarder {

	private Mobile mobile;

	private int escapeState;

	private int flagState;

	private String rxMessage;

	public OsmocomForwarder(Mobile mobile) {
		this.mobile = mobile;

		this.flagState = 0;
		this.escapeState = 0;
		this.rxMessage = "";
	}

	public void receiveMessageFromRamUploader(String message) {
		int rx = Integer.parseInt(message);
		if (rx == Constants.HDLC_FLAG) {
			if (flagState == 0) {
				flagState = 1;
			} else {
				OsmocomMessage msg = new OsmocomMessage(this.rxMessage);
				if (msg.getDlci() == 5) {
					this.mobile.getLayer1().receiveMessageFromOsmocomForwarder(
							msg.getData());
					this.mobile.receiveMessage(msg.getData());
				} else {
					StackMessage sm = new StackMessage(Constants.OSMOCOM,
							Constants.OSMOCOM_RADIO, msg.getData());
					this.mobile.receiveMessageFromPhone(sm
							.toString());
				}
				flagState = 0;
				this.rxMessage = "";
			}
		} else if (rx == Constants.HDLC_ESCAPE) {
			escapeState = 1;
		} else if (this.escapeState == 1) {
			int nm = rx ^ (1 << 5);
			this.rxMessage = this.rxMessage + nm + " ";
			escapeState = 0;
		} else {
			this.rxMessage = this.rxMessage + rx + " ";
		}
	}

	public void receiveMessageFromLayer1(String message) {
		this.mobile.sendMessage(message);
		this.mobile.getRamUploader().receiveMessageFromOsmocomForwarder(
				Constants.HDLC_FLAG + "");

		StringTokenizer header = new StringTokenizer(Constants.osmocomHeader);
		while (header.hasMoreTokens()) {
			this.mobile.getRamUploader().receiveMessageFromOsmocomForwarder(
					header.nextToken());
		}

		StringTokenizer body = new StringTokenizer(message);
		while (body.hasMoreTokens()) {
			String tkn = body.nextToken();
			int val = Integer.parseInt(tkn);
			if (val == 0 || val == Constants.HDLC_FLAG
					|| val == Constants.HDLC_ESCAPE) {
				this.mobile.getRamUploader()
						.receiveMessageFromOsmocomForwarder(
								Constants.HDLC_ESCAPE + "");
				this.mobile.getRamUploader()
						.receiveMessageFromOsmocomForwarder(
								(val ^ (1 << 5)) + "");
			} else {
				this.mobile.getRamUploader()
						.receiveMessageFromOsmocomForwarder(val + "");
			}
		}
		this.mobile.getRamUploader().receiveMessageFromOsmocomForwarder(
				Constants.HDLC_FLAG + "");
	}

}
