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
import java.util.StringTokenizer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jphone.Mobile;
import osmocom.OsmocomMessage;

public class RxTxPanel extends JPanel {

	private static final long serialVersionUID = -4813561873377204262L;

	JTextArea phoneOutput;
	JTextArea layer1Output;

	public RxTxPanel(Mobile mobile) {
		GridBagLayout outputlayout = new GridBagLayout();
		this.setLayout(outputlayout);
		GridBagConstraints outputconstraints = new GridBagConstraints();
		outputconstraints.fill = GridBagConstraints.BOTH;
		outputconstraints.weightx = 0.5;
		outputconstraints.weighty = 1.0;
		outputconstraints.gridheight = 1;
		outputconstraints.gridwidth = 1;

		outputconstraints.gridx = 0;
		outputconstraints.gridy = 0;

		phoneOutput = new JTextArea();
		JScrollPane otherpane = new JScrollPane(phoneOutput);
		otherpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		otherpane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.add(otherpane, outputconstraints);

		outputconstraints.gridx = 1;
		outputconstraints.gridy = 0;

		layer1Output = new JTextArea();
		JScrollPane dlci5pane = new JScrollPane(layer1Output);
		dlci5pane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		dlci5pane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.add(dlci5pane, outputconstraints);
	}

	public void receiveMessageFromPhone(String message) {
		OsmocomMessage msg = new OsmocomMessage(message);
		StringTokenizer str = new StringTokenizer(msg.getData());
		String output = "";
		while (str.hasMoreTokens()) {
			output = output + (char) Integer.parseInt(str.nextToken());
		}
		this.phoneOutput.append(output);
	}

	public void receiveMessage(String message) {
		this.layer1Output.append("L1:Rx: " + message + "\n");
	}

	public void sendMessage(String message) {
		this.layer1Output.append("L1:Tx: " + message + "\n");
	}

}
