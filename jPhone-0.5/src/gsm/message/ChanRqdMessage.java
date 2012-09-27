package gsm.message;

import java.util.StringTokenizer;

public class ChanRqdMessage {

	private int reqRef;
	private int ra;
	private int offset;
	private int combined;

	private int accessDelay;
	private int ta;

	private int msPower;
	private int txPower;

	public ChanRqdMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.reqRef = Integer.parseInt(str.nextToken());
		this.ra = Integer.parseInt(str.nextToken());
		int data2 = Integer.parseInt(str.nextToken());
		int data3 = Integer.parseInt(str.nextToken());
		this.offset = ((data2 & 0x7f) << 8) | data3;
		this.combined = data2 >> 7;
		this.accessDelay = Integer.parseInt(str.nextToken());
		this.ta = 0 - Integer.parseInt(str.nextToken());
		this.msPower = Integer.parseInt(str.nextToken());
		this.txPower = Integer.parseInt(str.nextToken());
	}

	public ChanRqdMessage(int reqRef, int ra, int offset, int combined,
			int accessDelay, int ta, int msPower, int txPower) {
		super();
		this.reqRef = reqRef;
		this.ra = ra;
		this.offset = offset;
		this.combined = combined;
		this.accessDelay = accessDelay;
		this.ta = ta;
		this.msPower = msPower;
		this.txPower = txPower;
	}

	public String toString() {
		return reqRef + " " + ra + " " + offset + " " + combined + " "
				+ accessDelay + " " + ta + " " + msPower + " " + txPower;
	}

	public int getReqRef() {
		return reqRef;
	}

	public int getRa() {
		return ra;
	}

	public int getOffset() {
		return offset;
	}

	public int getCombined() {
		return combined;
	}

	public int getAccessDelay() {
		return accessDelay;
	}

	public int getTa() {
		return ta;
	}

	public int getMsPower() {
		return msPower;
	}

	public int getTxPower() {
		return txPower;
	}

}
