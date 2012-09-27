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
package jphone.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jphone.Constants;
import jphone.Mobile;
import jphone.data.CellTowersListener;
import jphone.data.MobileStation;
import jphone.data.Sysinfo;

public class TunePanel extends JPanel implements ActionListener,
		CellTowersListener {

	private static final long serialVersionUID = -77209731750024140L;

	private Mobile mobile;

	private JTextField tower;

	public TunePanel(Mobile mobile) {
		this.mobile = mobile;

		GridBagLayout controllayout = new GridBagLayout();
		this.setLayout(controllayout);

		GridBagConstraints controlconstraints = new GridBagConstraints();
		controlconstraints.fill = GridBagConstraints.BOTH;
		controlconstraints.gridwidth = 1;
		controlconstraints.gridheight = 1;
		controlconstraints.weightx = 1;
		controlconstraints.weighty = 0;

		controlconstraints.gridx = 0;
		controlconstraints.gridy = 0;
		JButton reset = new JButton("Reset");
		reset.addActionListener(this);
		this.add(reset, controlconstraints);

		controlconstraints.gridx = 1;
		controlconstraints.gridy = 0;
		JLabel towerLabel = new JLabel("Tower: ");
		this.add(towerLabel, controlconstraints);

		controlconstraints.gridx = 2;
		controlconstraints.gridy = 0;
		tower = new JTextField();
		this.add(tower, controlconstraints);

		controlconstraints.gridx = 3;
		controlconstraints.gridy = 0;
		JButton tune = new JButton("Tune");
		tune.addActionListener(this);
		this.add(tune, controlconstraints);

		int x = 0;
		int y = 1;

		for (int i = 0; i < Sysinfo.properties.length; i++) {
			if (i != 1) {
				for (int j = 0; j < Sysinfo.properties[i].length; j++) {
					controlconstraints.gridx = x;
					controlconstraints.gridy = y;
					JLabel newlabel = new JLabel(Sysinfo.properties[i][j]);
					this.add(newlabel, controlconstraints);
					x++;
				}
				x = 0;
				y = y + 1;
				for (int j = 0; j < Sysinfo.properties[i].length; j++) {
					controlconstraints.gridx = x;
					controlconstraints.gridy = y;
					JLabel valuelabel = new JLabel("X");
					this.add(valuelabel, controlconstraints);
					x++;
				}
				x = 0;
				y = y + 1;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Reset") {
			this.mobile.getLayer1().tx_reset_req(Constants.L1CTL_RES_T_FULL);
		} else if (e.getActionCommand() == "Tune") {
			this.pm_req(Integer.parseInt(this.tower.getText()),
					Integer.parseInt(this.tower.getText()));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			this.mobile.getMobileStation().setProperty("ccch_mode",
					Constants.CCCH_MODE_NONE + "");
			this.tune(Integer.parseInt(this.tower.getText()));
			this.mobile.setState(Constants.MOBILE_TUNING);
		}
	}

	public void pm_req(int start, int end) {
		this.mobile.getLayer1().tx_reset_req(Constants.L1CTL_RES_T_FULL);
		int startLow = (start & 0xFF);
		int startHigh = ((start >> 8) & 0xFF);
		int endLow = (end & 0xFF);
		int endHigh = ((end >> 8) & 0xFF);
		this.mobile.getLayer1().tx_pm_req_range(startHigh, startLow, endHigh,
				endLow);
	}

	public void tune(int arfcn) {
		System.out.println("Tuning to " + arfcn);
		int timeout = 100;
		int flag = Constants.L1CTL_FBSB_F_FB01SB;
		int index = 0;
		int ccch_mode = Integer.parseInt(this.mobile.getMobileStation()
				.getProperty("ccch_mode"));

		int arfcnLow = (arfcn & 0xFF);
		int arfcnHigh = ((arfcn >> 8) & 0xFF);

		int timeLow = (timeout & 0xFF);
		int timeHigh = ((timeout >> 8) & 0xFF);

		this.mobile.getLayer1().tx_fbsb_req(arfcnHigh, arfcnLow, flag,
				timeHigh, timeLow, index, ccch_mode);
	}

	public void newTower(MobileStation ms) {
		this.towerUpdate(ms);
	}

	public synchronized void towerUpdate(MobileStation ms) {
		if (!this.tower.getText().equals("")) {
			int count = 4;
			for (int i = 0; i < Sysinfo.properties.length; i++) {
				if (i != 1) {
					count = count + Sysinfo.properties[i].length;
					for (int j = 0; j < Sysinfo.properties[i].length; j++) {
						JLabel label = (JLabel) this.getComponent(count);
						label.setText(" "
								+ ms.getTowerProperty(
										Integer.parseInt(this.tower.getText()),
										Sysinfo.properties[i][j]) + " ");
						count++;
					}
				}
			}
		}
	}
}
