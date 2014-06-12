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
package jphone.data;

import gsm.MCCMNC;
import gsm.message.FBSBConfMessage;
import gsm.message.L1PhDataIndMessage;

import java.util.StringTokenizer;

import jphone.Constants;

public class Sysinfo implements Comparable<Sysinfo> {

	public static String[] siAvail = { "si1", "si2", "si2bis", "si2ter", "si3",
			"si4", "si5", "si5bis", "si5ter", "si6" };

	public static String[] siMsg = { "si1_msg", "si2_msg", "si2bis_msg",
			"si2ter_msg", "si3_msg", "si4_msg", "si5_msg", "si5bis_msg",
			"si5ter_msg", "si6_msg" };

	public static String[] servingCell = { "bsic", "cell_id", "mcc", "mnc",
			"lac", "max_tx", "tx_int", "reest_denied", "cell_bar", "class_barr" };

	public static String[] si1rest = { "si1_re", "si1_t2", "si1_t3", "nch",
			"nch_position", "band_ind" };

	public static String[] si2rest = { "si2_re", "si2_t2", "si2_t3" };

	public static String[] si2trest = {};

	public static String[] si2brest = {};

	public static String[] si3rest = { "si3_cch_conf", "si3_bs_ag", "si3_att",
			"si3_spare1", "si3_bs_pa", "si3_spare2", "si3_options",
			"si3_radio_link_timeout", "si3_dtx", "si3_pwrc", "si3_spare",
			"si3_ms_txpwr_max_ccch", "si3_cell_resel_hyst",
			"si3_rxlev_acc_min", "si3_re", "si3_t2", "si3_t3", "sp", "sp_cbq",
			"sp_cro", "sp_to", "sp_pt", "po", "po_value", "si2ter_ind", "ecsm",
			"sched", "sched_where", "gprs", "gprs_ra_colour", "gprs_si13_pos" };

	public static String[] si4rest = { "si4_ms_txpwr_max_ccch",
			"si4_cell_resel_hyst", "si4_rxlev_acc_min", "si4_re", "si4_t2",
			"si4_t3", "si4_chan_nr" };

	public static String[] cellSel = { "ms_txpwr_max_cch",
			"cell_resel_hyst_db", "rxlev_acc_min_db", "neci", "acs" };

	public static String[] bcch = { "bcch_radio_link_timeout", "bcch_dtx",
			"bcch_pwrc" };

	public static String[] sacch = { "sacch_radio_link_timeout", "sacch_dtx",
			"sacch_pwrc" };

	public static String[] cch = { "ccch_conf", "bs_ag_blks_res",
			"att_allowed", "pag_mf_periods", "t3212" };

	public static String[] channelDesc = { "tsc", "h", "chan_desc_arfcn",
			"maio", "hsn", "chan_nr" };

	public static String[] nbCell = { "nb_ext_ind_si2", "nb_ba_ind_si2",
			"nb_ext_ind_si2bis", "nb_ba_ind_si2bis", "nb_multi_rep_si2ter",
			"nb_ba_ind_si2ter", "nb_ext_ind_si5", "nb_ba_ind_si5",
			"nb_ext_ind_si5bis", "nb_ba_ind_si5bis", "nb_multi_rep_si5ter",
			"nb_ba_ind_si5ter", "nb_ncc_permitted_si2", "nb_ncc_permitted_si6",
			"nb_max_retrans", "nb_tx_integer", "nb_reest_denied",
			"nb_cell_barr", "nb_class_barr" };

	public static String[] misc = { "rx_level", "hopp_len", "mcc_text" , "mnc_text" };

	public static String[] l1_fbsb_conf = { "l1_t1_rach", "l1_t2_rach",
			"l1_t3_rach", "l1_tc_rach", "l1_chan_nr", "l1_link_id",
			"l1_frame_nr", "l1_rx_level", "l1_snr", "l1_num_biterr",
			"l1_fire_crc", "l1_initial_freq_err", "l1_result", "l1_bsic" };

	public static String[] ph_data_ind = { "ph_t1_rach", "ph_t2_rach",
			"ph_t3_rach", "ph_tc_rach", "ph_chan_nr", "ph_link_id",
			"ph_frame_nr", "ph_rx_level", "ph_snr", "ph_num_biterr",
			"ph_fire_crc" };

	public static String[][] properties = new String[][] { siAvail, siMsg,
			servingCell, cellSel, bcch, sacch, cch, channelDesc, nbCell, misc,
			l1_fbsb_conf, ph_data_ind, si1rest, si2rest, si2trest, si2brest,
			si3rest, si4rest };

	private String[] siAvailValues;

	private String[] siMsgValues;

	private String[] servingCellValues;

	private String[] si1restValues;

	private String[] si2restValues;

	private String[] si2trestValues;

	private String[] si2brestValues;

	private String[] si3restValues;

	private String[] si4restValues;

	private String[] cellSelValues;

	private String[] bcchValues;

	private String[] sacchValues;

	private String[] cchValues;

	private String[] channelDescValues;

	private String[] nbCellValues;

	private String[] miscValues;

	private String[] l1_fbsb_confValues;

	private String[] ph_data_indValues;

	private String[][] values;

	private String arfcn;

	private int[] freq;

	private int[] hopping;

	public Sysinfo(String arfcn) {
		this.arfcn = arfcn;

		this.siAvailValues = new String[siAvail.length];
		this.siMsgValues = new String[siMsg.length];
		this.servingCellValues = new String[servingCell.length];
		this.si1restValues = new String[si1rest.length];
		this.si2restValues = new String[si2rest.length];
		this.si2brestValues = new String[si2brest.length];
		this.si2trestValues = new String[si2trest.length];
		this.si3restValues = new String[si3rest.length];
		this.si4restValues = new String[si4rest.length];
		this.cellSelValues = new String[cellSel.length];
		this.bcchValues = new String[bcch.length];
		this.sacchValues = new String[sacch.length];
		this.cchValues = new String[cch.length];
		this.channelDescValues = new String[channelDesc.length];
		this.nbCellValues = new String[nbCell.length];
		this.miscValues = new String[misc.length];
		this.l1_fbsb_confValues = new String[l1_fbsb_conf.length];
		this.ph_data_indValues = new String[ph_data_ind.length];

		this.values = new String[][] { siAvailValues, siMsgValues,
				servingCellValues, cellSelValues, bcchValues, sacchValues,
				cchValues, channelDescValues, nbCellValues, miscValues,
				l1_fbsb_confValues, ph_data_indValues, si1restValues,
				si2restValues, si2brestValues, si2trestValues, si3restValues,
				si4restValues };

		for (int i = 0; i < properties.length; i++) {
			for (int j = 0; j < properties[i].length; j++) {
				values[i][j] = "";
			}
		}

		this.freq = new int[1024];
		this.hopping = new int[64];
	}

	public String getProperty(String property) {
		for (int i = 0; i < properties.length; i++) {
			for (int j = 0; j < properties[i].length; j++) {
				if (properties[i][j].equals(property))
					return values[i][j];
			}
		}
		return "ERR";
	}

	public void setProperty(String property, String value) {
		boolean found = false;
		for (int i = 0; i < properties.length; i++) {
			for (int j = 0; j < properties[i].length; j++) {
				if (properties[i][j].equals(property)) {
					values[i][j] = value;
					found = true;
				}
			}
		}
		if (!found)
			System.out.println("Warning: property - " + property
					+ " not in sysinfo");
	}

	public int compareTo(Sysinfo o) {
		if (Integer.parseInt(o.getProperty("rx_level")) > Integer.parseInt(this
				.getProperty("rx_level"))) {
			return -1;
		} else {
			return 1;
		}
	}

	public void rx_l1_fbsb_conf(String rx) {
		FBSBConfMessage msg = new FBSBConfMessage(rx);
		this.setProperty("l1_t1_rach", msg.getL1_t1_rach() + "");
		this.setProperty("l1_t2_rach", msg.getL1_t2_rach() + "");
		this.setProperty("l1_t3_rach", msg.getL1_t3_rach() + "");
		this.setProperty("l1_tc_rach", msg.getL1_tc_rach() + "");
		this.setProperty("l1_chan_nr", msg.getL1_chan_nr() + "");
		this.setProperty("l1_link_id", msg.getL1_link_id() + "");
		this.setProperty("l1_frame_nr", msg.getL1_frame_nr() + "");
		this.setProperty("l1_rx_level", (110 - msg.getL1_rx_level()) + "");
		this.setProperty("l1_snr", msg.getL1_snr() + "");
		this.setProperty("l1_num_biterr", msg.getL1_num_biterr() + "");
		this.setProperty("l1_fire_crc", msg.getL1_fire_crc() + "");
		this.setProperty("l1_initial_freq_err", msg.getL1_initial_freq_err()
				+ "");
		this.setProperty("l1_result", msg.getL1_result() + "");
		this.setProperty("l1_bsic", msg.getL1_bsic() + "");
	}

	public void rx_l1_pm_conf(String rx) {
		this.setProperty("rx_level", rx);
	}

	public void rx_ph_data_ind(String rx) {
		L1PhDataIndMessage msg = new L1PhDataIndMessage(rx);
		this.setProperty("ph_t1_rach", msg.getPh_t1_rach() + "");
		this.setProperty("ph_t2_rach", msg.getPh_t2_rach() + "");
		this.setProperty("ph_t3_rach", msg.getPh_t3_rach() + "");
		this.setProperty("ph_tc_rach", msg.getPh_tc_rach() + "");
		this.setProperty("ph_chan_nr", msg.getPh_chan_nr() + "");
		this.setProperty("ph_link_id", msg.getPh_link_id() + "");
		this.setProperty("ph_frame_nr", msg.getPh_frame_nr() + "");
		this.setProperty("ph_rx_level", (110 - msg.getPh_rx_level()) + "");
		this.setProperty("ph_snr", msg.getPh_snr() + "");
		this.setProperty("ph_num_biterr", msg.getPh_num_biterr() + "");
		this.setProperty("ph_fire_crc", msg.getPh_fire_crc() + "");
	}

	public void rx_sysinfo1(String message) {
		this.setProperty("si1", "1");
		this.setProperty("si1_msg", message);
		StringTokenizer str = new StringTokenizer(message);
		int[] ccd = new int[16];
		for (int i = 0; i < ccd.length; i++) {
			ccd[i] = Integer.parseInt(str.nextToken());
		}
		decode_freq_list(ccd, 16, 0xce, Constants.FREQ_TYPE_SERV);

		int temp = Integer.parseInt(str.nextToken());

		int re = (temp & Integer.parseInt("10000000", 2)) >> 7;
		int cell_bar = (temp & Integer.parseInt("01000000", 2)) >> 6;
		int tx_integer = (temp & Integer.parseInt("00111100", 2)) >> 2;
		int max_trans = (temp & Integer.parseInt("00000011", 2));

		int tx_int = Constants.gsm48_tx_integer[tx_integer];
		int max_tx = Constants.gsm48_max_retrans[max_trans];

		int t2 = Integer.parseInt(str.nextToken());
		int t3 = Integer.parseInt(str.nextToken());

		this.setProperty("si1_re", re + "");
		this.setProperty("cell_bar", cell_bar + "");
		this.setProperty("tx_int", tx_int + "");
		this.setProperty("max_tx", max_tx + "");
		this.setProperty("si1_t2", t2 + "");
		this.setProperty("si1_t3", t3 + "");

		String rest = "";
		while (str.hasMoreTokens()) {
			rest = rest + str.nextToken() + " ";
		}
		this.decode_sysinfo1_rest(rest);

		if (this.getProperty("si4").equals("1")) {
			this.rx_sysinfo4(this.getProperty("si4_msg"));
		}
	}

	public void rx_sysinfo2(String message) {
		this.setProperty("si2", "1");
		this.setProperty("si2_msg", message);
		StringTokenizer str = new StringTokenizer(message);
		int[] ccd = new int[16];
		for (int i = 0; i < ccd.length; i++) {
			ccd[i] = Integer.parseInt(str.nextToken());
		}
		int nb_ext_ind_si2 = (ccd[0] >> 6) & 1;
		int nb_ba_ind_si2 = (ccd[0] >> 5) & 1;

		decode_freq_list(ccd, 16, 0xce, Constants.FREQ_TYPE_NCELL_2);

		int ncc = Integer.parseInt(str.nextToken());
		int temp = Integer.parseInt(str.nextToken());

		int re = (temp & Integer.parseInt("10000000", 2)) >> 7;
		int cell_bar = (temp & Integer.parseInt("01000000", 2)) >> 6;
		int tx_integer = (temp & Integer.parseInt("00111100", 2)) >> 2;
		int max_trans = (temp & Integer.parseInt("00000011", 2));

		int tx_int = Constants.gsm48_tx_integer[tx_integer];
		int max_tx = Constants.gsm48_max_retrans[max_trans];

		int t2 = Integer.parseInt(str.nextToken());
		int t3 = Integer.parseInt(str.nextToken());

		this.setProperty("nb_ncc_permitted_si2", ncc + "");
		this.setProperty("si2_re", re + "");
		this.setProperty("cell_bar", cell_bar + "");
		this.setProperty("tx_int", tx_int + "");
		this.setProperty("max_tx", max_tx + "");
		this.setProperty("si2_t2", t2 + "");
		this.setProperty("si2_t3", t3 + "");

		this.setProperty("nb_ext_ind_si2", nb_ext_ind_si2 + "");
		this.setProperty("nb_ba_ind_si2", nb_ba_ind_si2 + "");

		String rest = "";
		while (str.hasMoreTokens()) {
			rest = rest + str.nextToken() + " ";
		}
		this.decode_sysinfo2_rest(rest);
	}

	public void rx_sysinfo2ter(String message) {
		this.setProperty("si2ter", "1");
		this.setProperty("si2ter_msg", message);
		StringTokenizer str = new StringTokenizer(message);
		int[] ccd = new int[16];
		for (int i = 0; i < ccd.length; i++) {
			ccd[i] = Integer.parseInt(str.nextToken());
		}
		int nb_multi_rep_si2ter = (ccd[0] >> 6) & 3;
		int nb_ba_ind_si2ter = (ccd[0] >> 5) & 1;

		decode_freq_list(ccd, 16, 0x8e, Constants.FREQ_TYPE_NCELL_2ter);

		this.setProperty("nb_multi_rep_si2ter", nb_multi_rep_si2ter + "");
		this.setProperty("nb_ba_ind_si2ter", nb_ba_ind_si2ter + "");

		String rest = "";
		while (str.hasMoreTokens()) {
			rest = rest + str.nextToken() + " ";
		}
		this.decode_sysinfo2t_rest(rest);
	}

	public void rx_sysinfo2bis(String message) {
		this.setProperty("si2bis", "1");
		this.setProperty("si2bis_msg", message);
		StringTokenizer str = new StringTokenizer(message);
		int[] ccd = new int[16];
		for (int i = 0; i < ccd.length; i++) {
			ccd[i] = Integer.parseInt(str.nextToken());
		}
		int nb_ext_ind_si2bis = (ccd[0] >> 6) & 1;
		int nb_ba_ind_si2bis = (ccd[0] >> 5) & 1;

		this.setProperty("nb_ext_ind_si2bis", nb_ext_ind_si2bis + "");
		this.setProperty("nb_ba_ind_si2bis", nb_ba_ind_si2bis + "");

		decode_freq_list(ccd, 16, 0xce, Constants.FREQ_TYPE_NCELL_2bis);

		int temp = Integer.parseInt(str.nextToken());

		int re = (temp & Integer.parseInt("10000000", 2)) >> 7;
		int cell_bar = (temp & Integer.parseInt("01000000", 2)) >> 6;
		int tx_integer = (temp & Integer.parseInt("00111100", 2)) >> 2;
		int max_trans = (temp & Integer.parseInt("00000011", 2));

		int tx_int = Constants.gsm48_tx_integer[tx_integer];
		int max_tx = Constants.gsm48_max_retrans[max_trans];

		int t2 = Integer.parseInt(str.nextToken());
		int t3 = Integer.parseInt(str.nextToken());

		this.setProperty("re", re + "");
		this.setProperty("cell_bar", cell_bar + "");
		this.setProperty("tx_int", tx_int + "");
		this.setProperty("max_tx", max_tx + "");
		this.setProperty("t2", t2 + "");
		this.setProperty("t3", t3 + "");

		String rest = "";
		while (str.hasMoreTokens()) {
			rest = rest + str.nextToken() + " ";
		}
		this.decode_sysinfo2b_rest(rest);
	}

	public void rx_sysinfo3(String message) {
		this.setProperty("si3", "1");
		this.setProperty("si3_msg", message);
		StringTokenizer str = new StringTokenizer(message);

		int cell_id_high = Integer.parseInt(str.nextToken());
		int cell_id_low = Integer.parseInt(str.nextToken());

		int cell_id = (cell_id_high << 8) + cell_id_low;

		int lai_1 = Integer.parseInt(str.nextToken());
		int lai_2 = Integer.parseInt(str.nextToken());
		int lai_3 = Integer.parseInt(str.nextToken());

		int mcc = ((lai_1 & 0x0f) << 8) | (lai_1 & 0xf0) | (lai_2 & 0x0f);

		int mnc = ((lai_3 & 0x0f) << 8) | (lai_3 & 0xf0)
				| ((lai_2 & 0xf0) >> 4);

		int lac_high = Integer.parseInt(str.nextToken());
		int lac_low = Integer.parseInt(str.nextToken());

		int lac = (lac_high << 8) + lac_low;

		int ccd_1 = Integer.parseInt(str.nextToken());

		int cch_conf = (ccd_1 & Integer.parseInt("11100000", 2)) >> 5;
		int bs_ag = (ccd_1 & Integer.parseInt("00011100", 2)) >> 2;
		int att = (ccd_1 & Integer.parseInt("00000010", 2)) >> 1;
		int spare1 = (ccd_1 & Integer.parseInt("00000001", 2));

		int ccd_2 = Integer.parseInt(str.nextToken());

		int bs_pa = (ccd_2 & Integer.parseInt("11100000", 2)) >> 5;
		int spare2 = (ccd_2 & Integer.parseInt("00011111", 2));

		int t3212 = Integer.parseInt(str.nextToken());

		int options = Integer.parseInt(str.nextToken());

		int radio_link_timeout = (options & Integer.parseInt("11110000", 2)) >> 4;
		int dtx = (options & Integer.parseInt("00001100", 2)) >> 2;
		int pwrc = (options & Integer.parseInt("00000010", 2)) >> 1;
		int spare = (options & Integer.parseInt("00000001", 2));

		int csp_1 = Integer.parseInt(str.nextToken());

		int ms_txpwr_max_ccch = (csp_1 & Integer.parseInt("11111000", 2)) >> 3;
		int cell_resel_hyst = (csp_1 & Integer.parseInt("00000111", 2));

		int csp_2 = Integer.parseInt(str.nextToken());

		int rxlev_acc_min = (csp_2 & Integer.parseInt("11111100", 2)) >> 2;
		int neci = (csp_2 & Integer.parseInt("00000010", 2)) >> 1;
		int acs = (csp_2 & Integer.parseInt("00000001", 2));

		int temp = Integer.parseInt(str.nextToken());

		int re = (temp & Integer.parseInt("10000000", 2)) >> 7;
		int cell_bar = (temp & Integer.parseInt("01000000", 2)) >> 6;
		int tx_integer = (temp & Integer.parseInt("00111100", 2)) >> 2;
		int max_trans = (temp & Integer.parseInt("00000011", 2));

		int tx_int = Constants.gsm48_tx_integer[tx_integer];
		int max_tx = Constants.gsm48_max_retrans[max_trans];

		int t2 = Integer.parseInt(str.nextToken());
		int t3 = Integer.parseInt(str.nextToken());

		this.setProperty("cell_id", cell_id + "");
		this.setProperty("mcc", mcc + "");
		String[] vals = MCCMNC.getMCCMNC(mcc, mnc);
		this.setProperty("mcc_text", vals[0] + "");
		this.setProperty("mnc", mnc + "");
		this.setProperty("mnc_text", vals[1] + "");
		this.setProperty("lac", lac + "");
		this.setProperty("si3_cch_conf", cch_conf + "");
		this.setProperty("si3_bs_ag", bs_ag + "");
		this.setProperty("si3_att", att + "");
		this.setProperty("si3_spare1", spare1 + "");
		this.setProperty("si3_bs_pa", bs_pa + "");
		this.setProperty("si3_spare2", spare2 + "");
		this.setProperty("t3212", t3212 + "");
		this.setProperty("si3_options", options + "");
		this.setProperty("si3_radio_link_timeout", radio_link_timeout + "");
		this.setProperty("si3_dtx", dtx + "");
		this.setProperty("si3_pwrc", pwrc + "");
		this.setProperty("si3_spare", spare + "");
		this.setProperty("ms_txpwr_max_cch", ms_txpwr_max_ccch + "");
		this.setProperty("si3_cell_resel_hyst", cell_resel_hyst + "");
		this.setProperty("si3_rxlev_acc_min", rxlev_acc_min + "");
		this.setProperty("neci", neci + "");
		this.setProperty("acs", acs + "");
		this.setProperty("si3_re", re + "");
		this.setProperty("cell_bar", cell_bar + "");
		this.setProperty("tx_int", tx_int + "");
		this.setProperty("max_tx", max_tx + "");
		this.setProperty("si3_t2", t2 + "");
		this.setProperty("si3_t3", t3 + "");

		String rest = "";
		while (str.hasMoreTokens()) {
			rest = rest + str.nextToken() + " ";
		}
		this.decode_sysinfo3_rest(rest);
	}

	public void rx_sysinfo4(String message) {
		this.setProperty("si4", "1");
		this.setProperty("si4_msg", message);
		StringTokenizer str = new StringTokenizer(message);

		int lai_1 = Integer.parseInt(str.nextToken());
		int lai_2 = Integer.parseInt(str.nextToken());
		int lai_3 = Integer.parseInt(str.nextToken());

		int mcc = ((lai_1 & 0x0f) << 8) | (lai_1 & 0xf0) | (lai_2 & 0x0f);

		int mnc = ((lai_3 & 0x0f) << 8) | (lai_3 & 0xf0)
				| ((lai_2 & 0xf0) >> 4);

		int lac_high = Integer.parseInt(str.nextToken());
		int lac_low = Integer.parseInt(str.nextToken());

		int lac = (lac_high << 8) + lac_low;

		int csp_1 = Integer.parseInt(str.nextToken());

		int ms_txpwr_max_ccch = (csp_1 & Integer.parseInt("11111000", 2)) >> 3;
		int cell_resel_hyst = (csp_1 & Integer.parseInt("00000111", 2));

		int csp_2 = Integer.parseInt(str.nextToken());

		int rxlev_acc_min = (csp_2 & Integer.parseInt("11111100", 2)) >> 2;
		int neci = (csp_2 & Integer.parseInt("00000010", 2)) >> 1;
		int acs = (csp_2 & Integer.parseInt("00000001", 2));

		int temp = Integer.parseInt(str.nextToken());

		int re = (temp & Integer.parseInt("10000000", 2)) >> 7;
		int cell_bar = (temp & Integer.parseInt("01000000", 2)) >> 6;
		int tx_integer = (temp & Integer.parseInt("00111100", 2)) >> 2;
		int max_trans = (temp & Integer.parseInt("00000011", 2));

		int tx_int = Constants.gsm48_tx_integer[tx_integer];
		int max_tx = Constants.gsm48_max_retrans[max_trans];

		int t2 = Integer.parseInt(str.nextToken());
		int t3 = Integer.parseInt(str.nextToken());

		this.setProperty("mcc", mcc + "");
		String[] vals = MCCMNC.getMCCMNC(mcc, mnc);
		this.setProperty("mcc_text", vals[0] + "");
		this.setProperty("mnc", mnc + "");
		this.setProperty("mnc_text", vals[1] + "");
		this.setProperty("lac", lac + "");
		this.setProperty("ms_txpwr_max_cch", ms_txpwr_max_ccch + "");
		this.setProperty("si4_cell_resel_hyst", cell_resel_hyst + "");
		this.setProperty("si4_rxlev_acc_min", rxlev_acc_min + "");
		this.setProperty("neci", neci + "");
		this.setProperty("acs", acs + "");
		this.setProperty("si4_re", re + "");
		this.setProperty("cell_bar", cell_bar + "");
		this.setProperty("tx_int", tx_int + "");
		this.setProperty("max_tx", max_tx + "");
		this.setProperty("si4_t2", t2 + "");
		this.setProperty("si4_t3", t3 + "");

		int type = Integer.parseInt(str.nextToken());

		if (type == Constants.GSM48_IE_CBCH_CHAN_DESC) {
			int si4_chan_nr = Integer.parseInt(str.nextToken());
			this.setProperty("si4_chan_nr", si4_chan_nr + "");

			int union_1 = Integer.parseInt(str.nextToken());
			int union_2 = Integer.parseInt(str.nextToken());

			int h = (union_1 & Integer.parseInt("00001000", 2)) >> 3;

			if (h == 1) {
				int maio_high = union_1 >> 4;
				int tsc = (union_1 & Integer.parseInt("00000111", 2));
				int hsn = union_2 >> 2;
				int maio_low = (union_2 & Integer.parseInt("00000011", 2));
				int maio = maio_low | (maio_high << 2);
				this.setProperty("h", 1 + "");
				this.setProperty("tsc", tsc + "");
				this.setProperty("hsn", hsn + "");
				this.setProperty("maio", maio + "");
			} else {
				int arfcn_high = union_1 >> 6;
				int tsc = (union_1 & Integer.parseInt("00000111", 2));
				int arfcn_low = union_2;
				int arf = arfcn_low | (arfcn_high << 8);
				this.setProperty("chan_desc_arfcn", arf + "");
				this.setProperty("h", 0 + "");
				this.setProperty("tsc", tsc + "");
			}
		}
		type = Integer.parseInt(str.nextToken());
		if (type == Constants.GSM48_IE_CBCH_MOB_AL) {
			if (!this.getProperty("si1").equals("1")) {
				int len = Integer.parseInt(str.nextToken());
				int[] ma = new int[len];
				for (int i = 0; i < ma.length; i++) {
					ma[i] = Integer.parseInt(str.nextToken());
				}
				decode_mobile_alloc(ma, len, 1);
			}
		}

		String rest = "";
		while (str.hasMoreTokens()) {
			rest = rest + str.nextToken() + " ";
		}
		this.decode_sysinfo4_rest(rest);
	}

	private void decode_freq_list(int[] cd, int len, int mask, int frqt) {
		for (int i = 0; i < 1024; i++)
			this.setFreq(i, this.freq[i] & ~frqt);

		if ((cd[0] & 0xc0 & mask) == 0x00) {
			for (int i = 1; i <= 124; i++)
				if ((cd[15 - ((i - 1) >> 3)] & (1 << ((i - 1) & 7))) != 0)
					this.setFreq(i, this.freq[i] | frqt);
		} else if ((cd[0] & 0xc8 & mask) == 0x80) {
			System.out.println("Decode " + 0x80);
		} else if ((cd[0] & 0xce & mask) == 0x88) {
			System.out.println("Decode " + 0x88);
		} else if ((cd[0] & 0xce & mask) == 0x8a) {
			System.out.println("Decode " + 0x8a);
		} else if ((cd[0] & 0xce & mask) == 0x8c) {
			System.out.println("Decode " + 0x8c);
		} else if ((cd[0] & 0xce & mask) == 0x8e) {

			int orig_arfcn_hi = (cd[0] & Integer.parseInt("10000000", 2)) >> 7;
			// int form_id = (cd[0] & Integer.parseInt("01111111", 2));
			int orig_arfcn_mid = cd[1];
			// int rrfcn1_7 = (cd[0] & Integer.parseInt("11111110", 2)) >> 1;
			int orig_arfcn_lo = (cd[0] & Integer.parseInt("00000001", 2));

			int orig = (orig_arfcn_hi << 9) | (orig_arfcn_mid << 1)
					| orig_arfcn_lo;
			this.setFreq(orig, this.freq[orig] | frqt);
			for (int i = 1; 2 + (i >> 3) < len; i++)
				if ((cd[2 + (i >> 3)] & (0x80 >> (i & 7))) != 0)
					this.setFreq((orig + i) % 1024,
							this.freq[(orig + i) % 1024] | frqt);

		} else
			System.out.println("Warning: decode unrecognised mask " + mask);

	}

	private void decode_mobile_alloc(int[] ma, int len, int si4) {
		this.setProperty("hopp_len", 0 + "");
		int j = 0;
		int[] f = new int[len << 3];

		if (this.getProperty("si4").equals("1")) {
			for (int i = 0; i < 1024; i++)
				this.setFreq(i, this.freq[i] & ~Constants.FREQ_TYPE_HOPP);
		}
		for (int i = 1; i <= 1024; i++) {
			if ((this.freq[i & 1023] & Constants.FREQ_TYPE_SERV) != 0) {
				j++;
				f[j] = i & 1023;
				if (j == (len << 3))
					break;
			}
		}

		for (int i = 0; i < (len << 3); i++) {
			if ((ma[len - 1 - (i >> 3)] & (1 << (i & 7))) != 0) {
				if (i >= j)
					break;

				int hopp = Integer.parseInt(this.getProperty("hopp_len"));
				hopp++;
				this.setHopping(hopp, f[i]);
				this.setProperty("hopp_len", hopp + "");
				if (si4 != 0)
					this.setFreq(f[i], this.freq[f[i]]
							| Constants.FREQ_TYPE_HOPP);
			}
		}
	}

	public void decode_sysinfo1_rest(String rest) {
		System.out.println("Unimplemented - gsm48_rr - si1 process rest "
				+ rest);
	}

	public void decode_sysinfo2_rest(String rest) {
		System.out.println("Unimplemented - gsm48_rr - si2 process rest "
				+ rest);
	}

	public void decode_sysinfo2b_rest(String rest) {
		System.out.println("Unimplemented - gsm48_rr - si2b process rest "
				+ rest);
	}

	public void decode_sysinfo2t_rest(String rest) {
		System.out.println("Unimplemented - gsm48_rr - si2t process rest "
				+ rest);
	}

        public void decode_sysinfo3_rest(String rest) {

            MessageAsBits bits = new MessageAsBits(rest);

            // Optional Selection Parameters
            if (bits.getBitHigh() == 'H') {
                System.out.println("hi");
                this.setProperty("sp", 1 + "");
                this.setProperty("sp_cbq", bits.getUint(1) + "");
                this.setProperty("sp_cro", bits.getUint(6) + "");
                this.setProperty("sp_to", bits.getUint(3) + "");
                this.setProperty("sp_pt", bits.getUint(5) + "");
            } else {
                this.setProperty("sp", 0 + "");
            }

            // Optional Power Offset
            if (bits.getBitHigh() == 'H') {
                this.setProperty("po", 1 + "");
                this.setProperty("po_value", bits.getUint(2) + "");
            } else {
                this.setProperty("po", 0 + "");
            }

            // System Onformation 2ter Indicator
            if (bits.getBitHigh() == 'H') {
                this.setProperty("si2ter_ind", 1 + "");
            } else {
                this.setProperty("si2ter_ind", 0 + "");
            }

            // Early Classark Sending Control
            if (bits.getBitHigh() == 'H') {
                this.setProperty("ecsm", 1 + "");
            } else {
                this.setProperty("ecsm", 0 + "");
            }

            // Scheduling if and where
            if (bits.getBitHigh() == 'H') {
                this.setProperty("sched", 1 + "");
                this.setProperty("sched_where", bits.getUint(3) + "");
            } else {
                this.setProperty("sched", 0 + "");
            }

            // GPRS Indicator
            if (bits.getBitHigh() == 'H') {
                this.setProperty("gprs", 1 + "");
                this.setProperty("gprs_ra_colour", bits.getUint(3) + "");
                this.setProperty("gprs_si13_pos", bits.getUint(1) + "");
            } else {
                this.setProperty("gprs", 0 + "");
            }

        }

	public void decode_sysinfo4_rest(String rest) {

            MessageAsBits bits = new MessageAsBits(rest);

            // Optional Selection Parameters
            if (bits.getBitHigh() == 'H') {
                System.out.println("hi");
                this.setProperty("sp", 1 + "");
                this.setProperty("sp_cbq", bits.getUint(1) + "");
                this.setProperty("sp_cro", bits.getUint(6) + "");
                this.setProperty("sp_to", bits.getUint(3) + "");
                this.setProperty("sp_pt", bits.getUint(5) + "");
            } else {
                this.setProperty("sp", 0 + "");
            }

            // Optional Power Offset
            if (bits.getBitHigh() == 'H') {
                this.setProperty("po", 1 + "");
                this.setProperty("po_value", bits.getUint(2) + "");
            } else {
                this.setProperty("po", 0 + "");
            }

            // GPRS Indicator
            if (bits.getBitHigh() == 'H') {
                this.setProperty("gprs", 1 + "");
                this.setProperty("gprs_ra_colour", bits.getUint(3) + "");
                this.setProperty("gprs_si13_pos", bits.getUint(1) + "");
            } else {
                this.setProperty("gprs", 0 + "");
            }

	}

	public String getArfcn() {
		return arfcn;
	}

	public int[] getFreq() {
		return freq;
	}

	public int[] getHopping() {
		return hopping;
	}

	public void setFreq(int freq, int mask) {
		this.freq[freq] = mask;
	}

	public void setHopping(int index, int value) {
		this.hopping[index] = value;
	}

	/*
	 * Calculate C1
	 * http://www.ehanworld.com/GSM/GSM.html
	 */
	private int calculate_c1() {
	    // maximum RF output power of the MS, usually 33dBm for a handheld
	    // GSM900 and 30dBm for a handheld GSM1800 MS
	    // TODO: use a more accurate value by looking at power classes
	    int p = 30;

	    int a = Integer.parseInt(this.getProperty("rx_level")) - Integer.parseInt(this.getProperty("rxlev_acc_min"));
	    int b = Integer.parseInt(this.getProperty("ms_txpwr_max_cch")) - p;

	    return a - Math.max(b, 0);
	}
}
