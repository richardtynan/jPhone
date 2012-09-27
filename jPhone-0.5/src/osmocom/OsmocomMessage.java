package osmocom;

import java.util.StringTokenizer;

import jphone.Utils;

public class OsmocomMessage {

	private int dlci;

	private int control;

	private String data;

	public OsmocomMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.dlci = Integer.parseInt(str.nextToken());
		this.control = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(2, message);
	}

	public int getDlci() {
		return dlci;
	}

	public int getControl() {
		return control;
	}

	public String getData() {
		return data;
	}

}
