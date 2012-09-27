package gsm.message;

import java.util.StringTokenizer;

import jphone.Utils;

public class ImmediateAssignmentMessage {

	private int l2_plen;
	private int proto_discr;
	private int msg_type;
	private int page_mode;

	private int cd_chan_nr;
	private int maio_high;
	private int h;
	private int tsc;
	private int hsn;
	private int maio_low;
	private int arfcn_high;
	private int spare;
	private int arfcn_low;

	private int ra;
	private int t3_high;
	private int t1;
	private int t2;
	private int t3_low;

	private int timing_advance;
	private int mob_alloc_len;

	private String mob_alloc;

	public ImmediateAssignmentMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		arfcn_low = Integer.parseInt(str.nextToken());
		l2_plen = Integer.parseInt(str.nextToken());
		proto_discr = Integer.parseInt(str.nextToken());
		msg_type = Integer.parseInt(str.nextToken());
		page_mode = Integer.parseInt(str.nextToken());

		cd_chan_nr = Integer.parseInt(str.nextToken());
		int union_1 = Integer.parseInt(str.nextToken());
		int union_2 = Integer.parseInt(str.nextToken());

		maio_high = union_1 >> 4;
		h = (union_1 & Integer.parseInt("00001000", 2)) >> 3;
		tsc = (union_1 & Integer.parseInt("00000111", 2));
		hsn = union_2 >> 2;
		maio_low = (union_2 & Integer.parseInt("00000011", 2));

		arfcn_high = union_1 >> 6;
		spare = (union_1 & Integer.parseInt("00110000", 2)) >> 4;
		arfcn_low = union_2;

		ra = Integer.parseInt(str.nextToken());

		int req_ref_1 = Integer.parseInt(str.nextToken());
		int req_ref_2 = Integer.parseInt(str.nextToken());

		t3_high = req_ref_1 >> 5;
		t1 = (union_1 & Integer.parseInt("00011111", 2));

		t2 = req_ref_2 >> 3;
		t3_low = (union_1 & Integer.parseInt("00000111", 2));

		timing_advance = Integer.parseInt(str.nextToken());
		mob_alloc_len = Integer.parseInt(str.nextToken());

		mob_alloc = Utils.chew(12, message);
	}

	public int getL2_plen() {
		return l2_plen;
	}

	public int getProto_discr() {
		return proto_discr;
	}

	public int getMsg_type() {
		return msg_type;
	}

	public int getPage_mode() {
		return page_mode;
	}

	public int getCd_chan_nr() {
		return cd_chan_nr;
	}

	public int getMaio_high() {
		return maio_high;
	}

	public int getH() {
		return h;
	}

	public int getTsc() {
		return tsc;
	}

	public int getHsn() {
		return hsn;
	}

	public int getMaio_low() {
		return maio_low;
	}

	public int getArfcn_high() {
		return arfcn_high;
	}

	public int getSpare() {
		return spare;
	}

	public int getArfcn_low() {
		return arfcn_low;
	}

	public int getRa() {
		return ra;
	}

	public int getT3_high() {
		return t3_high;
	}

	public int getT1() {
		return t1;
	}

	public int getT2() {
		return t2;
	}

	public int getT3_low() {
		return t3_low;
	}

	public int getTiming_advance() {
		return timing_advance;
	}

	public int getMob_alloc_len() {
		return mob_alloc_len;
	}

	public String getMob_alloc() {
		return mob_alloc;
	}
}
