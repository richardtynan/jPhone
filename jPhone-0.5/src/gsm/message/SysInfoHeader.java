package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class SysInfoHeader {
	
	private int plen;
	private int disc;
	private int skip;
	private int si;
	private String data;

	public SysInfoHeader(String message) {
		StringTokenizer str = new StringTokenizer(message);

		plen = Integer.parseInt(str.nextToken());
		int temp = Integer.parseInt(str.nextToken());

		disc = (temp & Integer.parseInt("11110000", 2)) >> 4;
		skip = (temp & Integer.parseInt("00001111", 2));

		si = Integer.parseInt(str.nextToken());
		
		data = Utils.chew(3, message);
	}
	
	public int getPlen() {
		return plen;
	}

	public int getDisc() {
		return disc;
	}

	public int getSkip() {
		return skip;
	}

	public int getSi() {
		return si;
	}
	
	public String getData() {
		return data;
	}
}
