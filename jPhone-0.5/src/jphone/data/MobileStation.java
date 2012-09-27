package jphone.data;

import java.util.Collections;
import java.util.Vector;

public class MobileStation {

	public static String[] properties = { "ccch_mode" };

	private String[] values;

	private String name;

	private int deleting, shutdown, started;

	private Vector<Sysinfo> cellTowers;

	private Vector<CellTowersListener> listeners;

	public MobileStation(String name) {
		this.name = name;
		this.values = new String[properties.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = "";
		}
		this.cellTowers = new Vector<Sysinfo>();
		this.listeners = new Vector<CellTowersListener>();
	}

	public String getProperty(String property) {
		for (int i = 0; i < properties.length; i++) {
			if (properties[i].equals(property)) {
				return values[i];
			}
		}
		return "ERR";
	}

	public void setProperty(String property, String value) {
		for (int i = 0; i < properties.length; i++) {
			if (properties[i].equals(property)) {
				values[i] = value;
			}
		}
	}

	public void addTower(Sysinfo tower) {
		this.cellTowers.add(tower);
		Collections.sort(this.cellTowers);
		notifyListeners();
	}

	public String getTowerProperty(int arfcn, String property) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				return this.cellTowers.elementAt(i).getProperty(property);
			}
		}
		return "";
	}

	public int[] getTowerFreq(int arfcn) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				return this.cellTowers.elementAt(i).getFreq();
			}
		}
		return null;
	}

	public int[] getTowerHopping(int arfcn) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				return this.cellTowers.elementAt(i).getHopping();
			}
		}
		return null;
	}

	public void rx_l1_fbsb_conf(int arfcn, String rx) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_l1_fbsb_conf(rx);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_l1_pm_conf(int arfcn, String rx) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_l1_pm_conf(rx);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_ph_data_ind(int arfcn, String rx) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_ph_data_ind(rx);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_sysinfo1(int arfcn, String sysinfo) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_sysinfo1(sysinfo);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_sysinfo2(int arfcn, String sysinfo) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_sysinfo2(sysinfo);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_sysinfo2bis(int arfcn, String sysinfo) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_sysinfo2bis(sysinfo);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_sysinfo2ter(int arfcn, String sysinfo) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_sysinfo2ter(sysinfo);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_sysinfo3(int arfcn, String sysinfo) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_sysinfo3(sysinfo);
			}
		}
		this.notifyListenersTower();
	}
	
	public void rx_sysinfo4(int arfcn, String sysinfo) {
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				this.cellTowers.elementAt(i).rx_sysinfo4(sysinfo);
			}
		}
		this.notifyListenersTower();
	}

	public boolean contains(int arfcn) {
		boolean present = false;
		for (int i = 0; i < cellTowers.size(); i++) {
			if (Integer.parseInt(this.cellTowers.elementAt(i).getArfcn()) == arfcn) {
				present = true;
			}
		}
		return present;
	}

	public int getTowerCount() {
		return this.cellTowers.size();
	}

	public int getArfcn(int index) {
		return Integer.parseInt(this.cellTowers.elementAt(index).getArfcn());
	}

	public String getName() {
		return name;
	}

	public int getDeleting() {
		return deleting;
	}

	public void setDeleting(int deleting) {
		this.deleting = deleting;
	}

	public int getShutdown() {
		return shutdown;
	}

	public void setShutdown(int shutdown) {
		this.shutdown = shutdown;
	}

	public int getStarted() {
		return started;
	}

	public void setStarted(int started) {
		this.started = started;
	}

	public void addListener(CellTowersListener listener) {
		this.listeners.add(listener);
	}

	public void notifyListeners() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.elementAt(i).newTower(this);
		}
	}

	public void notifyListenersTower() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.elementAt(i).towerUpdate(this);
		}
	}

}
