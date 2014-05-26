/* (C) 2012 by Richard Tynan
*  (C) 2012 by Privacy International
*
* All Rights Reserved
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
*/
package gsm.message;

import java.util.StringTokenizer;

public class L1PhDataIndMessage {

	private int ph_chan_nr;
	private int ph_link_id;
	private int ph_band_arfcn;
	private int ph_frame_nr;
	private int ph_rx_level;
	private int ph_snr;
	private int ph_num_biterr;
	private int ph_fire_crc;
	private int ph_t1_rach;
	private int ph_t2_rach;
	private int ph_t3_rach;
	private int ph_tc_rach;
	private String data;
	private int[] ccch;

	public L1PhDataIndMessage(String message) {
		StringTokenizer str = new StringTokenizer(message);
		ph_chan_nr = Integer.parseInt(str.nextToken());
		ph_link_id = Integer.parseInt(str.nextToken());

		int band_arfcn_high = Integer.parseInt(str.nextToken());
		int band_arfcn_low = Integer.parseInt(str.nextToken());

		ph_band_arfcn = (band_arfcn_high << 8) + band_arfcn_low;

		int frame_nr_3 = Integer.parseInt(str.nextToken());
		int frame_nr_2 = Integer.parseInt(str.nextToken());
		int frame_nr_1 = Integer.parseInt(str.nextToken());
		int frame_nr_0 = Integer.parseInt(str.nextToken());

		ph_frame_nr = (frame_nr_3 << 24) + (frame_nr_2 << 16)
				+ (frame_nr_1 << 8) + frame_nr_0;

		ph_rx_level = Integer.parseInt(str.nextToken());
		ph_snr = Integer.parseInt(str.nextToken());
		ph_num_biterr = Integer.parseInt(str.nextToken());
		ph_fire_crc = Integer.parseInt(str.nextToken());

		ph_t1_rach = ph_frame_nr / (26 * 51);
		ph_t2_rach = ph_frame_nr % 26;
		ph_t3_rach = ph_frame_nr % 51;
		ph_tc_rach = (ph_frame_nr / 51) % 8;
		
		ccch = new int[23];
		data = "";
		for (int i = 0; i < ccch.length; i++) {
			ccch[i] = Integer.parseInt(str.nextToken());
			data = data + ccch[i] + " ";
		}
	}

	public int getPh_chan_nr() {
		return ph_chan_nr;
	}

	public int getPh_link_id() {
		return ph_link_id;
	}

	public int getPh_band_arfcn() {
		return ph_band_arfcn;
	}

	public int getPh_frame_nr() {
		return ph_frame_nr;
	}

	public int getPh_rx_level() {
		return ph_rx_level;
	}

	public int getPh_snr() {
		return ph_snr;
	}

	public int getPh_num_biterr() {
		return ph_num_biterr;
	}

	public int getPh_fire_crc() {
		return ph_fire_crc;
	}

	public int getPh_t1_rach() {
		return ph_t1_rach;
	}

	public int getPh_t2_rach() {
		return ph_t2_rach;
	}

	public int getPh_t3_rach() {
		return ph_t3_rach;
	}

	public int getPh_tc_rach() {
		return ph_tc_rach;
	}

	public String getData() {
		return data;
	}

	public int[] getCcch() {
		return ccch;
	}
	
}
