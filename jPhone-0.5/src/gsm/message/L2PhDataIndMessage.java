package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class L2PhDataIndMessage {

	private int arfcn;
	private int chan_nr;
	private int link_id;
	private String data;

	public L2PhDataIndMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		arfcn = Integer.parseInt(str.nextToken());
		chan_nr = Integer.parseInt(str.nextToken());
		link_id = Integer.parseInt(str.nextToken());
		data = Utils.chew(3, message);
	}

	public int getArfcn() {
		return arfcn;
	}

	public int getChan_nr() {
		return chan_nr;
	}

	public int getLink_id() {
		return link_id;
	}

	public String getData() {
		return data;
	}
}
