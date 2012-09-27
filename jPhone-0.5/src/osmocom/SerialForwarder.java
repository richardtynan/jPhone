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
