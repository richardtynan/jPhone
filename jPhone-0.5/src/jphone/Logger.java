package jphone;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import osmocom.OsmocomMessage;

public class Logger {

	private FileWriter phone;
	private FileWriter rxtx;

	public Logger(String logDir) {
		try {
			long startTime = System.currentTimeMillis();
			Date d = new Date(startTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			phone = new FileWriter(logDir + sdf.format(d) + " phone.txt");
			rxtx = new FileWriter(logDir + sdf.format(d) + " rxtx.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logMessageFromPhone(String message) {
		try {
			OsmocomMessage msg = new OsmocomMessage(message);
			StringTokenizer str = new StringTokenizer(msg.getData());
			String output = "";
			while (str.hasMoreTokens()) {
				output = output + (char) Integer.parseInt(str.nextToken());
			}
			phone.write(System.currentTimeMillis() + ": " + output);
			phone.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logReceiveMessage(String message) {
		try {
			rxtx.write(System.currentTimeMillis() + ":Rx: " + message + "\n");
			rxtx.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logSendMessage(String message) {
		try {
			rxtx.write(System.currentTimeMillis() + ":Tx: " + message + "\n");
			rxtx.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
