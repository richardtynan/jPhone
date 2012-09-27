package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class AbisRslCommonHdr {

	private int discr;

	private int type;

	private String data;

	public AbisRslCommonHdr(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.discr = Integer.parseInt(str.nextToken());
		this.type = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(2, message);
	}

	public AbisRslCommonHdr(int discr, int type, String data) {
		this.discr = discr;
		this.type = type;
		this.data = data;
	}

	public String toString() {
		return this.discr + " " + this.type + " " + this.data;
	}

	public int getDiscr() {
		return discr;
	}

	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}
}
