package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class LAPDmMessage {

	private int sap;
	private int primitive;
	private int op;
	private String data;

	public LAPDmMessage(int sap, int primitive, int op, String data) {
		this.sap = sap;
		this.primitive = primitive;
		this.op = op;
		this.data = data;
	}

	public LAPDmMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		sap = Integer.parseInt(str.nextToken());
		primitive = Integer.parseInt(str.nextToken());
		op = Integer.parseInt(str.nextToken());
		data = Utils.chew(3, message);
	}

	public String toString() {
		return sap + " " + primitive + " " + op + " " + data;
	}

	public int getSap() {
		return sap;
	}

	public int getPrimitive() {
		return primitive;
	}

	public int getOp() {
		return op;
	}

	public String getData() {
		return data;
	}

}
