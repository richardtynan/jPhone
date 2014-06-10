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
package jphone;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import jphone.data.CellTowersListener;
import jphone.data.MobileStation;
import jphone.gui.CatcherPanel;
import jphone.gui.ChannelPanel;
import jphone.gui.RamProgress;
import jphone.gui.RxTxPanel;
import jphone.gui.ScanPanel;
import jphone.gui.TunePanel;

public class GUI implements CellTowersListener {

	private Vector<CellTowersListener> listeners;
	private JFrame frame;
	private RxTxPanel rxtxPanel;
	private RamProgress rp;

	public GUI(Mobile mobile) {
		mobile.getMobileStation().addListener(this);
		this.listeners = new Vector<CellTowersListener>();

		frame = new JFrame("jPhone");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabs = new JTabbedPane();

		ChannelPanel channelPanel = new ChannelPanel(mobile);
		JScrollPane channelPane = new JScrollPane(channelPanel);
		channelPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		channelPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabs.addTab("Channel", channelPane);

		TunePanel tunePanel = new TunePanel(mobile);
		this.listeners.addElement(tunePanel);
		JScrollPane tunePane = new JScrollPane(tunePanel);
		tunePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tunePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabs.addTab("Tune", tunePane);

		ScanPanel scanPanel = new ScanPanel(mobile);
		this.listeners.addElement(scanPanel);
		JScrollPane scanPane = new JScrollPane(scanPanel);
		scanPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scanPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabs.addTab("Scan", scanPane);

		CatcherPanel catcherPanel = new CatcherPanel(mobile);
		this.listeners.addElement(catcherPanel);
		JScrollPane catcherPane = new JScrollPane(catcherPanel);
		catcherPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		catcherPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabs.addTab("Catcher", catcherPane);
		
		rxtxPanel = new RxTxPanel(mobile);
		tabs.addTab("RxTx", rxtxPanel);

		frame.getContentPane().add(tabs);

		rp = new RamProgress(mobile.getRamUploader(), frame);
		rp.openDialog();

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public void receiveMessageFromRamUploader(String message) {
		this.rp.receiveMessageFromRamUploader(message);
	}

	public void receiveMessageFromPhone(String message) {
		this.rxtxPanel.receiveMessageFromPhone(message);
	}

	public void receiveMessage(String message) {
		this.rxtxPanel.receiveMessage(message);
	}

	public void sendMessage(String message) {
		this.rxtxPanel.sendMessage(message);
	}

	public void newTower(MobileStation ms) {
		for (int i = 0; i < this.listeners.size(); i++) {
			this.listeners.elementAt(i).newTower(ms);
		}
		this.frame.validate();
	}

	public void towerUpdate(MobileStation ms) {
		for (int i = 0; i < this.listeners.size(); i++) {
			this.listeners.elementAt(i).towerUpdate(ms);
		}
		this.frame.validate();
	}
}
