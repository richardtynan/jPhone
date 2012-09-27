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

public class ScanPanel extends JPanel implements ActionListener,
		CellTowersListener {

	private static final long serialVersionUID = -7438357925976188173L;

	private String[] scanProperties = { "rx_level", "si1", "si2", "si2bis",
			"si2ter", "si3", "si4" };

	private JTextField scanFrom;
	private JTextField scanTo;

	private JTextField waitTime;
	private JTextField numTowers;

	private Mobile mobile;

	public ScanPanel(Mobile mobile) {
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
		JLabel scanFromLabel = new JLabel("From: ");
		this.add(scanFromLabel, controlconstraints);

		controlconstraints.gridx = 2;
		controlconstraints.gridy = 0;
		scanFrom = new JTextField();
		scanFrom.setText("0");
		this.add(scanFrom, controlconstraints);

		controlconstraints.gridx = 3;
		controlconstraints.gridy = 0;
		JLabel scanToLabel = new JLabel("To: ");
		this.add(scanToLabel, controlconstraints);

		controlconstraints.gridx = 4;
		controlconstraints.gridy = 0;
		scanTo = new JTextField();
		scanTo.setText("100");
		this.add(scanTo, controlconstraints);

		controlconstraints.gridx = 5;
		controlconstraints.gridy = 0;
		JButton scan = new JButton("Scan");
		scan.addActionListener(this);
		this.add(scan, controlconstraints);

		controlconstraints.gridx = 6;
		controlconstraints.gridy = 0;
		JButton autoTune = new JButton("Auto");
		autoTune.addActionListener(this);
		this.add(autoTune, controlconstraints);

		controlconstraints.gridx = 7;
		controlconstraints.gridy = 0;
		JLabel num = new JLabel("Num: ");
		this.add(num, controlconstraints);

		controlconstraints.gridx = 8;
		controlconstraints.gridy = 0;
		numTowers = new JTextField();
		this.add(numTowers, controlconstraints);

		controlconstraints.gridx = 9;
		controlconstraints.gridy = 0;
		JLabel wait = new JLabel("Wait (ms): ");
		this.add(wait, controlconstraints);

		controlconstraints.gridx = 10;
		controlconstraints.gridy = 0;
		waitTime = new JTextField();
		this.add(waitTime, controlconstraints);

		controlconstraints.gridx = 0;
		controlconstraints.gridy = 1;
		JLabel arfcn = new JLabel("arfcn");
		this.add(arfcn, controlconstraints);

		for (int i = 0; i < scanProperties.length; i++) {
			controlconstraints.gridx = i + 1;
			controlconstraints.gridy = 1;
			JLabel newlabel = new JLabel(scanProperties[i]);
			this.add(newlabel, controlconstraints);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Reset") {
			this.mobile.getLayer1().tx_reset_req(Constants.L1CTL_RES_T_FULL);
		} else if (e.getActionCommand() == "Scan") {
			int start = Integer.parseInt(this.scanFrom.getText());
			int end = Integer.parseInt(this.scanTo.getText());
			int startLow = (start & 0xFF);
			int startHigh = ((start >> 8) & 0xFF);
			int endLow = (end & 0xFF);
			int endHigh = ((end >> 8) & 0xFF);
			this.mobile.getLayer1().tx_pm_req_range(startHigh, startLow,
					endHigh, endLow);
		} else if (e.getActionCommand() == "Auto") {
			int num = Integer.parseInt(this.numTowers.getText());
			int wait = Integer.parseInt(this.waitTime.getText());
			ScanThread scan = new ScanThread(this.mobile, num, wait);
			this.mobile.setState(Constants.MOBILE_SCANNING);
			scan.start();
		}
	}

	public void newTower(MobileStation ms) {
		GridBagConstraints controlconstraints = new GridBagConstraints();
		controlconstraints.fill = GridBagConstraints.BOTH;
		controlconstraints.gridwidth = 1;
		controlconstraints.gridheight = 1;
		controlconstraints.weightx = 1;
		controlconstraints.weighty = 0;

		for (int i = 0; i < scanProperties.length + 1; i++) {
			controlconstraints.gridx = i;
			controlconstraints.gridy = ms.getTowerCount() + 1;
			JLabel newlabel = new JLabel();
			this.add(newlabel, controlconstraints);
		}
		this.towerUpdate(ms);
	}

	public void towerUpdate(MobileStation ms) {
		int topRowSize = 11;
		int start = topRowSize + scanProperties.length + 1;
		int num = scanProperties.length + 1;
		int count = 0;
		int i = start;
		while (count < ms.getTowerCount()) {
			JLabel arfcn = (JLabel) this.getComponent(i);
			arfcn.setText(ms.getArfcn(count) + "");
			for (int j = 0; j < scanProperties.length; j++) {
				JLabel label = (JLabel) this.getComponent(i + j + 1);
				label.setText(ms.getTowerProperty(ms.getArfcn(count),
						scanProperties[j]));
			}
			count++;
			i = i + num;
		}
	}
}
