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
import gsm.message.LAPDmMessage;
import jphone.Constants;
import jphone.Mobile;

public class LAPDm {

	private Mobile mobile;

	public LAPDm(Mobile mobile) {
		this.mobile = mobile;
	}

	public void receiveMessageFromLayer1(String message) {
		LAPDmMessage lm = new LAPDmMessage(message);
		if (lm.getPrimitive() == Constants.PRIM_PH_DATA) {
			l2_ph_data_ind(lm.getData());
		} else if (lm.getPrimitive() == Constants.PRIM_PH_RTS) {
			l2_ph_data_conf(lm.getData());
		} else if (lm.getPrimitive() == Constants.PRIM_PH_RACH) {
			if (lm.getOp() == Constants.PRIM_OP_INDICATION) {
				l2_ph_rach_ind(lm.getData());
			} else if (lm.getOp() == Constants.PRIM_OP_CONFIRM) {
				l2_ph_chan_conf(lm.getData());
			} else
				System.out.println("L2 unknown");
		} else
			System.out.println("L2 unknown");
	}

	public void receiveMessageFromAbove(String message) {
		AbisRslCommonHdr rm = new AbisRslCommonHdr(message);
		if (rm.getDiscr() == Constants.ABIS_RSL_MDISC_RLL) {
			this.rslms_rx_rll(rm.getData());
		} else if (rm.getDiscr() == Constants.ABIS_RSL_MDISC_COM_CHAN) {
			this.rslms_rx_com_chan(rm.getData());
		} else
			System.out.println("L2 unknown RSL " + message);
	}

	private void l2_ph_data_ind(String message) {
		L2PhDataIndMessage msg = new L2PhDataIndMessage(message);
		int format = -1;
		int cbits = msg.getChan_nr() >> 3;
		if (cbits == 0x10 || cbits == 0x12) {
			format = Constants.LAPDm_FMT_Bbis;
		} else {
			System.out.println("Unimplemented - lapdm - l2_ph_data_ind");
		}
		if (format == Constants.LAPDm_FMT_Bbis) {
			this.mobile.getGsm48rr().receiveMessageFromLapdm(message);
		}
	}

	private void l2_ph_data_conf(String message) {
		System.out
				.println("Unimplemented - lapdm - l2_ph_data_conf " + message);
	}

	private void l2_ph_rach_ind(String message) {
		System.out.println("Unimplemented - lapdm - l2_ph_rach_ind " + message);
	}

	private void l2_ph_chan_conf(String message) {
		System.out
				.println("Unimplemented - lapdm - l2_ph_chan_conf " + message);
	}

	private void rslms_rx_rll(String message) {
		System.out.println("Unimplemented - lapdm - rslms_rx_rll " + message);
	}

	private void rslms_rx_com_chan(String message) {
		AbisRslCommonHdr common = new AbisRslCommonHdr(message);
		AbisRslCChanHdr cch = new AbisRslCChanHdr(common.getData());
		int type = common.getType();
		if (type == Constants.RSL_MT_CHAN_RQD) {
			rslms_rx_chan_rqd(cch.getData());
		}
	}
	
	private void rslms_rx_chan_rqd(String message) {
		ChanRqdMessage msg = new ChanRqdMessage(message);

		LAPDmMessage lm = new LAPDmMessage(Constants.SAP_GSM_PH, Constants.PRIM_PH_RACH,
				Constants.PRIM_OP_REQUEST, msg.toString());
		
		this.mobile.getLayer1().receiveMessageFromLapdm(lm.toString());
		
	}

}
