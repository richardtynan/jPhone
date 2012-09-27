package osmocom;

import gsm.message.StackMessage;

import java.io.File;
import java.io.FileInputStream;
import java.util.StringTokenizer;

import jphone.Constants;
import jphone.Mobile;

public class RamUploader {

	private String binFile;

	private long timeout;

	private String ramTest;

	private int promptIndex;

	private int rxState;

	private Mobile mobile;

	public RamUploader(Mobile mobile, String binFile, long timeout) {
		this.mobile = mobile;
		this.binFile = binFile;
		this.timeout = timeout;

		this.ramTest = "";
		this.promptIndex = 0;
		this.rxState = Constants.TESTING;
	}

	public void init() {
		this.ramTest = "";
		StackMessage sm = new StackMessage(Constants.RAM,
				Constants.RAM_PERCENT, 5 + "");
		this.mobile.receiveMessageFromRamUploader(sm.toString());
		StringTokenizer str = new StringTokenizer(Constants.reset);
		while (str.hasMoreTokens()) {
			this.mobile.getSerialForwarder().receiveMessageFromRamUploader(
					str.nextToken());
		}
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!ramTest.contains(Constants.resetConf)) {
			sm = new StackMessage(Constants.RAM, Constants.RAM_PERCENT, 10 + "");
			this.mobile.receiveMessageFromRamUploader(sm.toString());
			this.ramTest = "";
			this.rxState = Constants.WAITING_PROMPT1;
			this.promptIndex = 0;
		} else {
			sm = new StackMessage(Constants.RAM, Constants.RAM_PERCENT,
					100 + "");
			this.mobile.receiveMessageFromRamUploader(sm.toString());
			this.rxState = Constants.FORWARDING;
		}
	}

	public void receiveMessageFromSerialForwarder(String message) {
		int rx = Integer.parseInt(message);
		if (rxState == Constants.TESTING) {
			this.ramTest = this.ramTest + rx + " ";
		} else if (this.rxState == Constants.WAITING_PROMPT1) {
			if (Constants.phone_prompt1[this.promptIndex] == rx) {
				this.promptIndex++;
			} else {
				this.promptIndex = 0;
			}
			if (this.promptIndex == Constants.phone_prompt1.length) {
				StackMessage sm = new StackMessage(Constants.RAM,
						Constants.RAM_PERCENT, 15 + "");
				this.mobile.receiveMessageFromRamUploader(sm.toString());
				for (int i = 0; i < Constants.dnload_cmd.length; i++) {
					this.mobile.getSerialForwarder()
							.receiveMessageFromRamUploader(
									Constants.dnload_cmd[i] + "");
				}
				this.rxState = Constants.WAITING_PROMPT2;
				this.promptIndex = 0;
			}
		} else if (this.rxState == Constants.WAITING_PROMPT2) {
			if (Constants.phone_prompt2[this.promptIndex] == rx) {
				this.promptIndex++;
			} else {
				this.promptIndex = 0;
			}
			if (this.promptIndex == Constants.phone_prompt2.length) {
				StackMessage sm = new StackMessage(Constants.RAM,
						Constants.RAM_PERCENT, 20 + "");
				this.mobile.receiveMessageFromRamUploader(sm.toString());
				try {
					this.mobile.getSerialForwarder()
							.receiveMessageFromRamUploader(
									Constants.xor_init + "");
					File layer1 = new File(binFile);
					int running_xor = 0x02;
					int length = (int) (layer1.length() + Constants.data_hdr_c123.length);
					byte low = (byte) length;
					int high = length >> 8;
					this.mobile.getSerialForwarder()
							.receiveMessageFromRamUploader(high + "");
					this.mobile.getSerialForwarder()
							.receiveMessageFromRamUploader(low + "");
					running_xor ^= high;
					running_xor ^= low;
					for (int i = 0; i < Constants.data_hdr_c123.length; i++) {
						running_xor ^= Constants.data_hdr_c123[i];
						this.mobile.getSerialForwarder()
								.receiveMessageFromRamUploader(
										Constants.data_hdr_c123[i] + "");
					}
					FileInputStream reader = new FileInputStream(binFile);
					int read = reader.read();
					while (read != -1) {
						this.mobile.getSerialForwarder()
								.receiveMessageFromRamUploader(read + "");
						running_xor ^= read;
						read = reader.read();
					}
					reader.close();
					sm = new StackMessage(Constants.RAM, Constants.RAM_PERCENT,
							95 + "");
					this.mobile.receiveMessageFromRamUploader(sm.toString());
					this.mobile.getSerialForwarder()
							.receiveMessageFromRamUploader(running_xor + "");
					this.rxState = Constants.WAITING_ACK;
					this.promptIndex = 0;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (this.rxState == Constants.WAITING_ACK) {
			if (Constants.phone_ack[this.promptIndex] == rx) {
				this.promptIndex++;
			} else {
				this.promptIndex = 0;
			}
			if (this.promptIndex == Constants.phone_ack.length) {
				StackMessage sm = new StackMessage(Constants.RAM,
						Constants.RAM_PERCENT, 100 + "");
				this.mobile.receiveMessageFromRamUploader(sm.toString());
				this.rxState = Constants.FORWARDING;
			}
		} else if (rxState == Constants.FORWARDING) {
			this.mobile.getOsmocomForwarder().receiveMessageFromRamUploader(
					rx + "");
		}
	}

	public void receiveMessageFromOsmocomForwarder(String message) {
		this.mobile.getSerialForwarder().receiveMessageFromRamUploader(message);
	}
}
