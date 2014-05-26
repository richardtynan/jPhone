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
package osmocom;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jphone.Mobile;

public class SerialForwarder implements Runnable {

	InputStream in;

	OutputStream out;

	private Mobile mobile;

	public SerialForwarder(Mobile mobile, String port) {
		this.mobile = mobile;
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier
					.getPortIdentifier(port);
			SerialPort serialPort = (SerialPort) portIdentifier.open(this
					.getClass().getName(), 2000);
			serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.FLOWCONTROL_NONE);
			this.in = serialPort.getInputStream();
			this.out = serialPort.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			int read = in.read();
			while (read != -1) {
				mobile.getRamUploader().receiveMessageFromSerialForwarder(read + "");
				read = in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveMessageFromRamUploader(String message) {
		try {
			this.out.write(Integer.parseInt(message));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
