package jphone;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import jphone.data.CellTowersListener;
import jphone.data.MobileStation;
import jphone.gui.ChannelPanel;
import jphone.gui.PhonePanel;
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

		PhonePanel phonePanel = new PhonePanel();
		tabs.addTab("Phone", phonePanel);

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
