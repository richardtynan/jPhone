package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class Layer1Message {

	private int type;

	private int flags;

	private String data;

	public Layer1Message(int type, int flags, String data) {
		super();
		this.type = type;
		this.flags = flags;
		this.data = data;
	}
	
	public Layer1Message(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.type = Integer.parseInt(str.nextToken());
		this.flags = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(4, message);
	}

	public int getFlags() {
		return flags;
	}

	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public String toString() {
		return type + " " + flags + " 0 0 " + data;
	}

}
