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

import gsm.GSM322;
import gsm.GSM48MM;
import gsm.GSM48RR;
import gsm.LAPDm;
import gsm.Layer1;
import jphone.data.MobileStation;
import osmocom.OsmocomForwarder;
import osmocom.RamUploader;
import osmocom.SerialForwarder;

public class Mobile {

	private MobileStation ms;

	private SerialForwarder sf;
	private RamUploader ru;
	private OsmocomForwarder of;

	private Layer1 layer1;
	private LAPDm lapdm;
	private GSM322 gsm322;
	private GSM48RR gsm48rr;
	private GSM48MM gsm48mm;

	private GUI gui;

	// todo logging
	
	private int state;

	public Mobile(String name, String serialPort, String binFile, long timeout) {
		this.ms = new MobileStation(name);

		this.sf = new SerialForwarder(this, serialPort);
		this.ru = new RamUploader(this, binFile, timeout);
		this.of = new OsmocomForwarder(this);

		this.layer1 = new Layer1(this);
		this.lapdm = new LAPDm(this);
		this.gsm322 = new GSM322(this);
		this.gsm48rr = new GSM48RR(this);
		this.gsm48mm = new GSM48MM(this);

		this.gui = new GUI(this);
		
		this.state = Constants.MOBILE_IDLE;
	}

	public void init() {
		(new Thread(sf)).start();
		ru.init();
	}

	public void receiveMessageFromRamUploader(String message) {
		this.gui.receiveMessageFromRamUploader(message);
	}

	public void receiveMessageFromPhone(String message) {
		this.gui.receiveMessageFromPhone(message);
	}

	public void receiveMessage(String message) {
		this.gui.receiveMessage(message);
	}

	public void sendMessage(String message) {
		this.gui.sendMessage(message);
	}

	public MobileStation getMobileStation() {
		return ms;
	}

	public SerialForwarder getSerialForwarder() {
		return sf;
	}

	public RamUploader getRamUploader() {
		return ru;
	}

	public OsmocomForwarder getOsmocomForwarder() {
		return of;
	}

	public Layer1 getLayer1() {
		return layer1;
	}

	public LAPDm getLapdm() {
		return lapdm;
	}

	public GSM322 getGsm322() {
		return gsm322;
	}

	public GSM48RR getGsm48rr() {
		return gsm48rr;
	}

	public GSM48MM getGsm48mm() {
		return gsm48mm;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
