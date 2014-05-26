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
package gsm;

import gsm.message.AbisRslCChanHdr;
import gsm.message.AbisRslCommonHdr;
import gsm.message.ChanRqdMessage;
import gsm.message.L2PhDataIndMessage;
import gsm.message.SysInfoHeader;

import java.util.Random;

import jphone.Constants;
import jphone.Mobile;

public class GSM48RR {

	private int state;

	/* queue for RSL-SAP message upwards */
	//struct llist_head	rsl_upqueue;

	/* queue for messages while RR connection is built up */
	//struct llist_head       downqueue;

	/* timers */
	//struct osmo_timer_list	t_starting; /* starting time for chan. access */
	//struct osmo_timer_list	t_rel_wait; /* wait for L2 to transmit UA */
	//struct osmo_timer_list	t3110;
	//struct osmo_timer_list	t3122;
	//struct osmo_timer_list	t3124;
	//struct osmo_timer_list	t3126;
	private int t3126_value;

	//#ifndef TODO
	//struct osmo_timer_list	temp_rach_ti; /* temporary timer */
	//#endif

	/* states if RR-EST-REQ was used */
	private int rr_est_req;
	//struct msgb		*rr_est_msg;
	private int est_cause; /* cause used for establishment */
	private int paging_mi_type; /* how did we got paged? */

	/* channel request states */
	private int wait_assign; /* waiting for assignment state */
	private int n_chan_req; /* number left, incl. current */
	private int chan_req_val; /* current request value */ 
	private int chan_req_mask; /* mask of random bits */

	/* state of dedicated mdoe */
	private int dm_est;

	/* cr_hist */
	private int cr_ra; /* stores requested ra until confirmed */
	//struct gsm48_cr_hist	cr_hist[3];

	/* V(SD) sequence numbers */
	private int v_sd; /* 16 PD 1-bit sequence numbers packed */

	/* current channel descriptions */
	//struct gsm48_rr_cd	cd_now;

	/* current cipering */
	private int cipher_on;
	private int cipher_type; /* 10.5.2.9 */

	/* special states when assigning channel */
	private int modify_state;
	private int hando_sync_ind, hando_rot, hando_nci, hando_act;
	//struct gsm48_rr_cd	cd_last; /* store last cd in case of failure */
	//struct gsm48_rr_cd	cd_before; /* before start time */
	//struct gsm48_rr_cd	cd_after; /* after start time */

	/* BA range */
	private int ba_ranges;
	private int[] ba_range; //[16];

	/* measurements */
	//struct osmo_timer_list	t_meas;
	//struct gsm48_rr_meas	meas;
	private int monitor;

	/* audio flow */
	private int audio_mode;

	/* sapi 3 */
	private int sapi3_state;
	private int sapi3_link_id;
	
	private Mobile mobile;

	public GSM48RR(Mobile mobile) {
		this.mobile = mobile;
	}

	public void tx_rand_acc(int arfcn) {
		int ccch_conf = Integer.parseInt(mobile.getMobileStation()
				.getTowerProperty(arfcn, "ccch_conf"));
		int temp = (ccch_conf == 1) ? 1 : 0;
		int slots = getSlots(
				true,
				Integer.parseInt(mobile.getMobileStation().getTowerProperty(
						arfcn, "tx_int")), ccch_conf);
		Random rand = new Random(System.currentTimeMillis());
		int chan_req = rand.nextInt();
		chan_req = chan_req & Integer.parseInt("11111111", 2);

		int chan_req_mask = 0x1f;
		int chan_req_val = 0x00;

		chan_req &= chan_req_mask; // todo check cause
		chan_req |= chan_req_val; // todo check cause

		ChanRqdMessage crm = new ChanRqdMessage(Constants.RSL_IE_REQ_REFERENCE,
				chan_req, ((slots >> 8) | ((temp) << 7)), slots,
				Constants.RSL_IE_ACCESS_DELAY, 0, Constants.RSL_IE_MS_POWER, 5);
		AbisRslCChanHdr cch = new AbisRslCChanHdr(Constants.RSL_IE_CHAN_NR,
				Constants.RSL_CHAN_RACH, crm.toString());
		AbisRslCommonHdr common = new AbisRslCommonHdr(
				Constants.ABIS_RSL_MDISC_COM_CHAN, Constants.RSL_MT_CHAN_RQD,
				cch.toString());
		this.mobile.getLapdm().receiveMessageFromAbove(common.toString());
	}
	
	public void tx_rand_acc(int arfcn, int ccch_conf, int slots, int ra) {
		ChanRqdMessage crm = new ChanRqdMessage(Constants.RSL_IE_REQ_REFERENCE,
				ra, ccch_conf, slots,
				Constants.RSL_IE_ACCESS_DELAY, 0, Constants.RSL_IE_MS_POWER, 5);
		AbisRslCChanHdr cch = new AbisRslCChanHdr(Constants.RSL_IE_CHAN_NR,
				Constants.RSL_CHAN_RACH, crm.toString());
		AbisRslCommonHdr common = new AbisRslCommonHdr(
				Constants.ABIS_RSL_MDISC_COM_CHAN, Constants.RSL_MT_CHAN_RQD,
				cch.toString());
		this.mobile.getLapdm().receiveMessageFromAbove(common.toString());
	}

	public int getSlots(boolean first, int tx_int, int ccch_conf) {
		if (first) {
			return 0;
		} else {
			if (tx_int == 3 || tx_int == 8 || tx_int == 14 || tx_int == 50) {
				if (ccch_conf != 1) /* not combined CCCH */
					return 55;
				else
					return 41;
			} else if (tx_int == 4 || tx_int == 9 || tx_int == 16) {
				if (ccch_conf != 1)
					return 76;
				else
					return 52;
			} else if (tx_int == 5 || tx_int == 10 || tx_int == 20) {
				if (ccch_conf != 1)
					return 109;
				else
					return 58;
			} else if (tx_int == 6 || tx_int == 11 || tx_int == 25) {
				if (ccch_conf != 1)
					return 163;
				else
					return 86;
			} else {
				if (ccch_conf != 1)
					return 217;
				else
					return 115;
			}
		}
	}

	public void rcv_rsl(String data) {

	}

	public void receiveMessageFromLapdm(String message) {
		L2PhDataIndMessage msg = new L2PhDataIndMessage(message);
		// todo check state
		if ((msg.getChan_nr() & 0xf8) == Constants.RSL_CHAN_Bm_ACCHs) {
			rx_acch(msg.getArfcn(), msg.getData());
		} else if ((msg.getChan_nr() & 0xf0) == Constants.RSL_CHAN_Lm_ACCHs) {
			rx_acch(msg.getArfcn(), msg.getData());
		} else if ((msg.getChan_nr() & 0xe0) == Constants.RSL_CHAN_SDCCH4_ACCH) {
			rx_acch(msg.getArfcn(), msg.getData());
		} else if ((msg.getChan_nr() & 0xc0) == Constants.RSL_CHAN_SDCCH8_ACCH) {
			rx_acch(msg.getArfcn(), msg.getData());
		} else if ((msg.getChan_nr() & 0xf8) == Constants.RSL_CHAN_BCCH) {
			rx_bcch(msg.getArfcn(), msg.getData());
		} else if ((msg.getChan_nr() & 0xf8) == Constants.RSL_CHAN_PCH_AGCH) {
			rx_pch_agch(msg.getArfcn(), msg.getData());
		} else if ((msg.getChan_nr() & 0xf8) == Constants.RSL_CHAN_RACH) {
			System.out.println("Unimplemented - gsm48_rr - RSL_CHAN_RACH "
					+ message);
		} else
			System.out.println("RR unknown 1 unit_data_ind " + msg.getData());
	}

	private void rx_pch_agch(int arfcn, String message) {
		SysInfoHeader hdr = new SysInfoHeader(message);
		if (hdr.getSi() == Constants.GSM48_MT_RR_PAG_REQ_1) {
			rx_pag_req_1(hdr.getData());
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_PAG_REQ_2) {
			rx_pag_req_2(hdr.getData());
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_PAG_REQ_3) {
			rx_pag_req_3(hdr.getData());
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_IMM_ASS) {
			rx_imm_ass(hdr.getData());
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_IMM_ASS_EXT) {
			rx_imm_ass_ext(hdr.getData());
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_IMM_ASS_REJ) {
			rx_imm_ass_rej(hdr.getData());
		} else
			System.out.println("RR unknown rx_pch_agch " + message);
	}

	private void rx_imm_ass_rej(String message) {
		System.out.println("Unimplemented - gsm48_rr - rx_imm_ass_rej "
				+ message);
	}

	private void rx_imm_ass_ext(String message) {
		System.out.println("Unimplemented - gsm48_rr - rx_imm_ass_ext "
				+ message);
	}

	private void rx_imm_ass(String message) {
		System.out.println("Unimplemented - gsm48_rr - rx_imm_ass " + message);
	}

	private void rx_pag_req_3(String message) {
		System.out
				.println("Unimplemented - gsm48_rr - rx_pag_req_3 " + message);
	}

	private void rx_pag_req_2(String message) {
		System.out
				.println("Unimplemented - gsm48_rr - rx_pag_req_2 " + message);
	}

	private void rx_pag_req_1(String message) {
		System.out
				.println("Unimplemented - gsm48_rr - rx_pag_req_1 " + message);
	}

	private void rx_acch(int arfcn, String message) {
		System.out.println("Unimplemented - gsm48_rr - rx_acch " + message);
	}

	private void rx_bcch(int arfcn, String message) {
		SysInfoHeader hdr = new SysInfoHeader(message);
		if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_1) {
			this.mobile.getMobileStation().rx_sysinfo1(arfcn, hdr.getData());
			this.new_sysinfo(arfcn, Constants.GSM48_MT_RR_SYSINFO_1);
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_2) {
			this.mobile.getMobileStation().rx_sysinfo2(arfcn, hdr.getData());
			this.new_sysinfo(arfcn, Constants.GSM48_MT_RR_SYSINFO_2);
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_2bis) {
			this.mobile.getMobileStation().rx_sysinfo2bis(arfcn, hdr.getData());
			this.new_sysinfo(arfcn, Constants.GSM48_MT_RR_SYSINFO_2bis);
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_2ter) {
			this.mobile.getMobileStation().rx_sysinfo2ter(arfcn, hdr.getData());
			this.new_sysinfo(arfcn, Constants.GSM48_MT_RR_SYSINFO_2ter);
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_3) {
			this.mobile.getMobileStation().rx_sysinfo3(arfcn, hdr.getData());
			if (this.mobile.getState() != Constants.MOBILE_SCANNING) {
				int ccch_mode = Integer.parseInt(this.mobile.getMobileStation()
						.getProperty("ccch_mode"));
				if (ccch_mode == Constants.CCCH_MODE_NONE) {
					int cch_conf = Integer.parseInt(this.mobile
							.getMobileStation().getTowerProperty(arfcn,
									"si3_cch_conf"));
					if (cch_conf == 1) {
						this.mobile.getMobileStation().setProperty("ccch_mode",
								Constants.CCCH_MODE_COMBINED + "");
						this.mobile.getLayer1().tx_ccch_mode_req(
								Constants.CCCH_MODE_COMBINED);
					} else {
						this.mobile.getMobileStation().setProperty("ccch_mode",
								Constants.CCCH_MODE_NON_COMBINED + "");
						this.mobile.getLayer1().tx_ccch_mode_req(
								Constants.CCCH_MODE_NON_COMBINED);
					}
					System.out.println("Changing ccch_mode to "
							+ this.mobile.getMobileStation().getProperty(
									"ccch_mode"));
				}
			}
			this.new_sysinfo(arfcn, Constants.GSM48_MT_RR_SYSINFO_3);
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_4) {
			this.mobile.getMobileStation().rx_sysinfo4(arfcn, hdr.getData());
			this.new_sysinfo(arfcn, Constants.GSM48_MT_RR_SYSINFO_4);
		} else if (hdr.getSi() == Constants.GSM48_MT_RR_SYSINFO_13) {
			// System.out.println("RR ignoring si13");
		} else
			System.out.println("RR unknown 1 rx_bcch " + message);
	}

	public void new_sysinfo(int arfcn, int type) {
		if ((type == Constants.GSM48_MT_RR_SYSINFO_5
				|| type == Constants.GSM48_MT_RR_SYSINFO_5bis || type == Constants.GSM48_MT_RR_SYSINFO_5ter)
				&& this.mobile.getMobileStation()
						.getTowerProperty(arfcn, "si5").equals("1")
				&& (this.mobile.getMobileStation()
						.getTowerProperty(arfcn, "nb_ext_ind_si5").equals("") || this.mobile
						.getMobileStation().getTowerProperty(arfcn, "si5bis")
						.equals("1"))) {
			System.out
					.println("Unimplemented - gsm48_rr - new_sysinfo - process si5");
		}
	}
}
