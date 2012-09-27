package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class StackMessage {
	
	private int layer;
	
	private int type;
	
	private String data;

	public StackMessage(int layer, int type, String data) {
		this.layer = layer;
		this.type = type;
		this.data = data;
	}
	
	public StackMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.layer = Integer.parseInt(str.nextToken());
		this.type = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(2, message);
	}

	public String toString() {
		return this.layer + " " + this.type + " " + this.data;
	}
	
	public int getLayer() {
		return layer;
	}

	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}

}
