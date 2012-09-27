package gsm;

import gsm.message.StackMessage;
import jphone.Constants;
import jphone.Mobile;

public class GSM322 {

	private Mobile mobile;

	public GSM322(Mobile mobile) {
		this.mobile = mobile;
	}

	public void receiveMessageFromLayer1(String message) {
		StackMessage sm = new StackMessage(message);
		if (sm.getType() == Constants.S_L1CTL_PM_RES) {
			this.l1_pm_res(sm.getData());
		} else if (sm.getType() == Constants.S_L1CTL_PM_DONE) {
			this.l1_pm_done(sm.getData());
		} else if (sm.getType() == Constants.S_L1CTL_FBSB_RESP) {
			this.l1_fbsb_resp(sm.getData());
		} else if (sm.getType() == Constants.S_L1CTL_FBSB_ERR) {
			this.l1_fbsb_err(sm.getData());
		} else if (sm.getType() == Constants.S_L1CTL_LOSS_IND) {
			this.l1_loss_ind(sm.getData());
		} else if (sm.getType() == Constants.S_L1CTL_RESET) {
			this.l1_reset(sm.getData());
		} else if (sm.getType() == Constants.S_L1CTL_NEIGH_PM_IND) {
			this.l1_neigh_pm_ind(sm.getData());
		} else
			System.out.println("322 - unrecognised l1 message");
	}

	private void l1_pm_res(String data) {
		System.out.println("Unimplemented - gsm322 - l1_pm_res - " + data);
	}

	private void l1_pm_done(String data) {
		System.out.println("Unimplemented - gsm322 - l1_pm_done - " + data);
	}

	private void l1_fbsb_resp(String data) {
		System.out.println("Unimplemented - gsm322 - l1_fbsb_resp - " + data);
	}

	private void l1_fbsb_err(String data) {
		System.out.println("Unimplemented - gsm322 - l1_fbsb_err - " + data);
	}

	private void l1_loss_ind(String data) {
		System.out.println("Unimplemented - gsm322 - l1_loss_ind - " + data);
	}

	private void l1_reset(String data) {
		System.out.println("Unimplemented - gsm322 - l1_reset - " + data);
	}

	private void l1_neigh_pm_ind(String data) {
		System.out
				.println("Unimplemented - gsm322 - l1_neigh_pm_ind - " + data);
	}

}
