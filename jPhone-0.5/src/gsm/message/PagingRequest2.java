package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class PagingRequest2 {

	private int l2plen;
	private int disc;
	private int type;

	private int pag_mode;
	private int spare;
	private int cneed1;
	private int cneed2;

	private int tmsi1;
	private int tmsi2;

	private String data;

	public PagingRequest2(String message) {
		StringTokenizer str = new StringTokenizer(message);

		l2plen = Integer.parseInt(str.nextToken());
		disc = Integer.parseInt(str.nextToken());
		type = Integer.parseInt(str.nextToken());

		int temp = Integer.parseInt(str.nextToken());
		pag_mode = (temp & Integer.parseInt("11000000", 2)) >> 6;
		spare = (temp & Integer.parseInt("00110000", 2)) >> 4;
		cneed1 = (temp & Integer.parseInt("00001100", 2)) >> 2;
		cneed2 = (temp & Integer.parseInt("00000011", 2));

		int tmsi1a = Integer.parseInt(str.nextToken());
		int tmsi1b = Integer.parseInt(str.nextToken());
		int tmsi1c = Integer.parseInt(str.nextToken());
		int tmsi1d = Integer.parseInt(str.nextToken());

		tmsi1 = (tmsi1a << 24) + (tmsi1b << 16) + (tmsi1c << 8) + tmsi1d;

		int tmsi2a = Integer.parseInt(str.nextToken());
		int tmsi2b = Integer.parseInt(str.nextToken());
		int tmsi2c = Integer.parseInt(str.nextToken());
		int tmsi2d = Integer.parseInt(str.nextToken());

		tmsi2 = (tmsi2a << 24) + (tmsi2b << 16) + (tmsi2c << 8) + tmsi2d;

		data = Utils.chew(12, message);
	}

	public int getL2plen() {
		return l2plen;
	}

	public int getDisc() {
		return disc;
	}

	public int getType() {
		return type;
	}

	public int getPag_mode() {
		return pag_mode;
	}

	public int getSpare() {
		return spare;
	}

	public int getCneed1() {
		return cneed1;
	}

	public int getCneed2() {
		return cneed2;
	}

	public String getData() {
		return data;
	}

	public int getTmsi1() {
		return tmsi1;
	}

	public int getTmsi2() {
		return tmsi2;
	}
}
