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
