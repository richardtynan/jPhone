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

import gsm.message.ChanRqdMessage;
import gsm.message.FBSBConfMessage;
import gsm.message.LAPDmMessage;
import gsm.message.Layer1Message;
import gsm.message.PMConfMessage;
import gsm.message.L1PhDataIndMessage;
import gsm.message.StackMessage;
import jphone.Constants;
import jphone.Mobile;
import jphone.data.Sysinfo;

public class Layer1 {

	private Mobile mobile;

	public Layer1(Mobile mobile) {
		this.mobile = mobile;
	}

	public void receiveMessageFromOsmocomForwarder(String message) {
		Layer1Message msg = new Layer1Message(message);
		if (msg.getType() == Constants.L1CTL_FBSB_CONF)
			this.rx_l1_fbsb_conf(msg.getData());
		else if (msg.getType() == Constants.L1CTL_DATA_IND)
			this.rx_ph_data_ind(msg.getData());
		else if (msg.getType() == Constants.L1CTL_DATA_CONF)
			this.rx_ph_data_conf(msg.getData());
		else if (msg.getType() == Constants.L1CTL_RESET_IND)
			this.rx_l1_reset(msg.getData());
		else if (msg.getType() == Constants.L1CTL_RESET_CONF)
			this.rx_l1_reset(msg.getData());
		else if (msg.getType() == Constants.L1CTL_PM_CONF) {
			this.rx_l1_pm_conf(msg.getData());
			if (msg.getFlags() == Constants.L1CTL_F_DONE) {
				StackMessage sm = new StackMessage(Constants.LAYER1,
						Constants.S_L1CTL_PM_DONE, "");
				this.mobile.getGsm322().receiveMessageFromLayer1(sm.toString());
			}
		} else if (msg.getType() == Constants.L1CTL_RACH_CONF)
			this.rx_l1_rach_conf(msg.getData());
		else if (msg.getType() == Constants.L1CTL_CCCH_MODE_CONF)
			this.rx_l1_ccch_mode_conf(msg.getData());
		else if (msg.getType() == Constants.L1CTL_TCH_MODE_CONF)
			this.rx_l1_tch_mode_conf(msg.getData());
		else if (msg.getType() == Constants.L1CTL_SIM_CONF)
			this.rx_l1_sim_conf(msg.getData());
		else if (msg.getType() == Constants.L1CTL_NEIGH_PM_IND)
			this.rx_l1_neigh_pm_ind(msg.getData());
		else if (msg.getType() == Constants.L1CTL_TRAFFIC_IND)
			this.rx_l1_traffic_ind(msg.getData());
		else if (msg.getType() == Constants.L1CTL_TRAFFIC_CONF)
			this.rx_l1_traffic_conf(msg.getData());
		else
			System.out.println("L1 unknown message from below " + message);
	}

	public void receiveMessageFromLapdm(String message) {
		LAPDmMessage msg = new LAPDmMessage(message);
		if (msg.getPrimitive() == Constants.PRIM_PH_DATA) {
			this.tx_data_req(msg.getData());
		} else if (msg.getPrimitive() == Constants.PRIM_PH_RACH) {
			ChanRqdMessage crm = new ChanRqdMessage(msg.getData());
			this.tx_param_req(crm.getTa() + " " + crm.getTxPower());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.tx_rach_req(crm.getRa() + " " + crm.getOffset() + " " + crm.getCombined());
		} else
			System.out.println("L1 unknown message from above " + message);
	}

	// Rx phone messages

	private void rx_l1_fbsb_conf(String rx) {
		FBSBConfMessage msg = new FBSBConfMessage(rx);
		String fr = msg.getL1_snr() + " " + msg.getL1_bsic() + " "
				+ msg.getL1_band_arfcn();
		StackMessage sm = new StackMessage(Constants.LAYER1,
				Constants.S_L1CTL_FBSB_RESP, fr);
		this.mobile.getGsm322().receiveMessageFromLayer1(sm.toString());
	}

	private void rx_l1_reset(String rx) {
		StackMessage sm = new StackMessage(Constants.LAYER1,
				Constants.S_L1CTL_PM_RES, "");
		this.mobile.getGsm322().receiveMessageFromLayer1(sm.toString());
	}

	private void rx_l1_pm_conf(String rx) {
		PMConfMessage pcm = new PMConfMessage(rx);
		for (int i = 0; i < pcm.getArfcn().size(); i++) {
			int ar = pcm.getArfcn().elementAt(i);
			int lev = pcm.getRx_lev().elementAt(i);
			if (this.mobile.getMobileStation().contains(ar)) {
				this.mobile.getMobileStation().rx_l1_pm_conf(ar, lev +"");
			} else {
				Sysinfo ct = new Sysinfo(ar + "");
				ct.setProperty("rx_level", lev + "");
				this.mobile.getMobileStation().addTower(ct);
			}
			String mr = ar + " " + lev;
			StackMessage sm = new StackMessage(Constants.LAYER1,
					Constants.S_L1CTL_PM_RES, mr);
			this.mobile.getGsm322().receiveMessageFromLayer1(sm.toString());
		}
	}

	private void rx_l1_rach_conf(String rx) {
		System.out.println("Unimplemented - layer1 - rx_l1_rach_conf - " + rx);
	}

	private void rx_l1_ccch_mode_conf(String rx) {
		System.out.println("Unimplemented - layer1 - rx_l1_ccch_mode_conf - "
				+ rx);
	}

	private void rx_l1_tch_mode_conf(String rx) {
		System.out.println("Unimplemented - layer1 - rx_l1_tch_mode_conf - "
				+ rx);
	}

	private void rx_l1_sim_conf(String rx) {
		System.out.println("Unimplemented - layer1 - rx_l1_sim_conf - " + rx);
	}

	private void rx_l1_neigh_pm_ind(String rx) {
		System.out.println("Unimplemented - layer1 - rx_l1_neigh_pm_ind - "
				+ rx);
	}

	private void rx_l1_traffic_ind(String rx) {
		System.out
				.println("Unimplemented - layer1 - rx_l1_traffic_ind - " + rx);
	}

	private void rx_l1_traffic_conf(String rx) {
		System.out.println("Unimplemented - layer1 - rx_l1_traffic_conf - "
				+ rx);
	}

	private void rx_ph_data_ind(String rx) {
		L1PhDataIndMessage msg = new L1PhDataIndMessage(rx);
		this.mobile.getMobileStation().rx_ph_data_ind(msg.getPh_band_arfcn(), rx);
		// todo: update loss measurements
		LAPDmMessage lm = new LAPDmMessage(Constants.SAP_GSM_PH,
				Constants.PRIM_PH_DATA, Constants.PRIM_OP_INDICATION,
				msg.getPh_band_arfcn() + " " + msg.getPh_chan_nr() + " "
						+ msg.getPh_link_id() + " " + msg.getData());
		this.mobile.getLapdm().receiveMessageFromLayer1(lm.toString());
	}

	private void rx_ph_data_conf(String rx) {
		System.out.println("Unimplemented - layer1 - rx_ph_data_conf - " + rx);
	}

	// Tx

	public void tx_fbsb_req(int arfcn_high, int arfcn_low, int flags,
			int timeout_high, int timeout_low, int sync_info_idx, int ccch_mode) {
		int freq_err_thresh1 = 11000 - 1000;
		int freq_err_thresh1Low = (freq_err_thresh1 & 0xFF);
		int freq_err_thresh1High = ((freq_err_thresh1 >> 8) & 0xFF);
		int freq_err_thresh2 = 1000 - 200;
		int freq_err_thresh2Low = (freq_err_thresh2 & 0xFF);
		int freq_err_thresh2High = ((freq_err_thresh2 >> 8) & 0xFF);
		int num_freqerr_avg = 3;
		Layer1Message lm = new Layer1Message(Constants.L1CTL_FBSB_REQ, 0,
				arfcn_high + " " + arfcn_low + " " + timeout_high + " "
						+ timeout_low + " " + freq_err_thresh1High + " "
						+ freq_err_thresh1Low + " " + freq_err_thresh2High
						+ " " + freq_err_thresh2Low + " " + num_freqerr_avg
						+ " " + flags + " " + sync_info_idx + " " + ccch_mode);
		this.mobile.getOsmocomForwarder().receiveMessageFromLayer1(
				lm.toString());
	}

	public void tx_ccch_mode_req(int ccch_mode) {
		Layer1Message lm = new Layer1Message(Constants.L1CTL_CCCH_MODE_REQ, 0,
				ccch_mode + " " + 0 + " " + 0 + " " + 0);
		this.mobile.getOsmocomForwarder().receiveMessageFromLayer1(
				lm.toString());
	}

	public void tx_reset_req(int type) {
		Layer1Message lm = new Layer1Message(Constants.L1CTL_RESET_REQ, 0, type
				+ " " + 0 + " " + 0 + " " + 0);
		this.mobile.getOsmocomForwarder().receiveMessageFromLayer1(
				lm.toString());
	}

	public void tx_pm_req_range(int arfcn_from_high, int arfcn_from_low,
			int arfcn_to_high, int arfcn_to_low) {
		int type = 1;
		Layer1Message lm = new Layer1Message(Constants.L1CTL_PM_REQ, 0, type
				+ " " + 0 + " " + 0 + " " + 0 + " " + arfcn_from_high + " "
				+ arfcn_from_low + " " + arfcn_to_high + " " + arfcn_to_low);
		this.mobile.getOsmocomForwarder().receiveMessageFromLayer1(
				lm.toString());
	}

	private void tx_data_req(String message) {
		System.out.println("Unimplemented - layer1 - tx_data_req " + message);
	}

	private void tx_param_req(String message) {
		System.out.println("Unimplemented - layer1 - tx_param_req " + message);
	}

	private void tx_rach_req(String message) {
		System.out.println("Unimplemented - layer1 - tx_rach_req " + message);
	}
}
