package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class AbisRslCChanHdr {

	private int ie_chan;

	private int chan_nr;

	private String data;

	public AbisRslCChanHdr(String message) {
		StringTokenizer str = new StringTokenizer(message);
		this.ie_chan = Integer.parseInt(str.nextToken());
		this.chan_nr = Integer.parseInt(str.nextToken());
		this.data = Utils.chew(2, message);
	}

	public AbisRslCChanHdr(int ie_chan, int chan_nr, String data) {
		this.ie_chan = ie_chan;
		this.chan_nr = chan_nr;
		this.data = data;
	}

	public String toString() {
		return this.ie_chan + " " + this.chan_nr + " " + this.data;
	}
	
	public int getIe_chan() {
		return ie_chan;
	}

	public int getChan_nr() {
		return chan_nr;
	}

	public String getData() {
		return data;
	}
}
