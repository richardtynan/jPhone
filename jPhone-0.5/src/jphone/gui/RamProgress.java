package jphone.gui;

import gsm.message.StackMessage;

import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import osmocom.RamUploader;

public class RamProgress {

	private JProgressBar uploadProgress;

	private JDialog dialog;

	private JFrame frame;

	public RamProgress(RamUploader ru, JFrame frame) {
		this.frame = frame;
	}

	public void openDialog() {
		uploadProgress = new JProgressBar();
		dialog = new JDialog(frame, "Upload Progress", false);
		dialog.setLocation(200, 200);
		dialog.setSize(200, 50);
		dialog.getContentPane().add(uploadProgress);
		uploadProgress.setValue(0);
		dialog.setVisible(true);
	}

	public void receiveMessageFromRamUploader(String message) {
		StackMessage sm = new StackMessage(message);
		StringTokenizer str = new StringTokenizer(sm.getData());
		int percent = Integer.parseInt(str.nextToken());
		this.uploadProgress.setValue(percent);
		if (percent == 100) {
			this.dialog.setVisible(false);
		}
	}
}
