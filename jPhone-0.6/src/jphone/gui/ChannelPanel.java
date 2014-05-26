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

public class ChannelPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5941160263913521742L;

	private Mobile mobile;

	private JTextField arfcn;
	private JTextField ra;
	private JTextField slots;
	private JTextField conf;

	public ChannelPanel(Mobile mobile) {
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
		JLabel arfcnLabel = new JLabel("ARFCN: ");
		this.add(arfcnLabel, controlconstraints);

		controlconstraints.gridx = 2;
		controlconstraints.gridy = 0;
		arfcn = new JTextField();
		this.add(arfcn, controlconstraints);

		controlconstraints.gridx = 3;
		controlconstraints.gridy = 0;
		JLabel raLabel = new JLabel("RA: ");
		this.add(raLabel, controlconstraints);

		controlconstraints.gridx = 4;
		controlconstraints.gridy = 0;
		ra = new JTextField();
		this.add(ra, controlconstraints);

		controlconstraints.gridx = 5;
		controlconstraints.gridy = 0;
		JLabel slotsLabel = new JLabel("Slots: ");
		this.add(slotsLabel, controlconstraints);

		controlconstraints.gridx = 6;
		controlconstraints.gridy = 0;
		slots = new JTextField();
		this.add(slots, controlconstraints);

		controlconstraints.gridx = 7;
		controlconstraints.gridy = 0;
		JLabel confLabel = new JLabel("Conf: ");
		this.add(confLabel, controlconstraints);

		controlconstraints.gridx = 8;
		controlconstraints.gridy = 0;
		conf = new JTextField();
		this.add(conf, controlconstraints);

		controlconstraints.gridx = 9;
		controlconstraints.gridy = 0;
		JButton send = new JButton("Send");
		send.addActionListener(this);
		this.add(send, controlconstraints);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Reset")) {
			this.mobile.getLayer1().tx_reset_req(Constants.L1CTL_RES_T_FULL);
		} else if (e.getActionCommand().equals("Send")) {
			//System.out.println("send");
			int arfcn = Integer.parseInt(this.arfcn.getText());
			int ra = Integer.parseInt(this.ra.getText());
			int slots = Integer.parseInt(this.slots.getText());
			int conf = Integer.parseInt(this.conf.getText());
			this.mobile.getGsm48rr().tx_rand_acc(arfcn, conf, slots, ra);
		}
	}

}
