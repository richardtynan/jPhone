package gsm.message;

import java.util.StringTokenizer;

public class FBSBConfMessage {

	private int l1_chan_nr;
	private int l1_link_id;
	private int l1_band_arfcn;
	private int l1_frame_nr;
	private int l1_rx_level;
	private int l1_snr;
	private int l1_num_biterr;
	private int l1_fire_crc;
	private int l1_initial_freq_err;
	private int l1_result;
	private int l1_bsic;
	private int l1_t1_rach;
	private int l1_t2_rach;
	private int l1_t3_rach;
	private int l1_tc_rach;

	public FBSBConfMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);

		l1_chan_nr = Integer.parseInt(str.nextToken());
		l1_link_id = Integer.parseInt(str.nextToken());

		int band_arfcn_high = Integer.parseInt(str.nextToken());
		int band_arfcn_low = Integer.parseInt(str.nextToken());

		l1_band_arfcn = (band_arfcn_high << 8) + band_arfcn_low;

		int frame_nr_3 = Integer.parseInt(str.nextToken());
		int frame_nr_2 = Integer.parseInt(str.nextToken());
		int frame_nr_1 = Integer.parseInt(str.nextToken());
		int frame_nr_0 = Integer.parseInt(str.nextToken());

		l1_frame_nr = (frame_nr_3 << 24) + (frame_nr_2 << 16)
				+ (frame_nr_1 << 8) + frame_nr_0;

		l1_rx_level = Integer.parseInt(str.nextToken());
		l1_snr = Integer.parseInt(str.nextToken());
		l1_num_biterr = Integer.parseInt(str.nextToken());
		l1_fire_crc = Integer.parseInt(str.nextToken());

		int initial_freq_err_high = Integer.parseInt(str.nextToken());
		int initial_freq_err_low = Integer.parseInt(str.nextToken());

		l1_initial_freq_err = (initial_freq_err_high << 8)
				+ initial_freq_err_low;

		l1_result = Integer.parseInt(str.nextToken());
		l1_bsic = Integer.parseInt(str.nextToken());

		l1_t1_rach = l1_frame_nr / (26 * 51);
		l1_t2_rach = l1_frame_nr % 26;
		l1_t3_rach = l1_frame_nr % 51;
		l1_tc_rach = (l1_frame_nr / 51) % 8;
	}

	public int getL1_chan_nr() {
		return l1_chan_nr;
	}

	public int getL1_link_id() {
		return l1_link_id;
	}

	public int getL1_band_arfcn() {
		return l1_band_arfcn;
	}

	public int getL1_frame_nr() {
		return l1_frame_nr;
	}

	public int getL1_rx_level() {
		return l1_rx_level;
	}

	public int getL1_snr() {
		return l1_snr;
	}

	public int getL1_num_biterr() {
		return l1_num_biterr;
	}

	public int getL1_fire_crc() {
		return l1_fire_crc;
	}

	public int getL1_initial_freq_err() {
		return l1_initial_freq_err;
	}

	public int getL1_result() {
		return l1_result;
	}

	public int getL1_bsic() {
		return l1_bsic;
	}

	public int getL1_t1_rach() {
		return l1_t1_rach;
	}

	public int getL1_t2_rach() {
		return l1_t2_rach;
	}

	public int getL1_t3_rach() {
		return l1_t3_rach;
	}

	public int getL1_tc_rach() {
		return l1_tc_rach;
	}

}
