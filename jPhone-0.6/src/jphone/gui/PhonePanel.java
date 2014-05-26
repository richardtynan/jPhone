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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PhonePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 3415193631276733666L;
	private JTextField displayTextField;

	public PhonePanel() {
		JPanel digitPanel = new JPanel();
		displayTextField = new JTextField(20);
		JButton sendButton = new JButton("Call");
		sendButton.addActionListener(this);
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		JButton endButton = new JButton("End");
		endButton.addActionListener(this);
		JButton Button1 = new JButton("1");
		Button1.addActionListener(this);
		JButton Button2 = new JButton("2");
		Button2.addActionListener(this);
		JButton Button3 = new JButton("3");
		Button3.addActionListener(this);
		JButton Button4 = new JButton("4");
		Button4.addActionListener(this);
		JButton Button5 = new JButton("5");
		Button5.addActionListener(this);
		JButton Button6 = new JButton("6");
		Button6.addActionListener(this);
		JButton Button7 = new JButton("7");
		Button7.addActionListener(this);
		JButton Button8 = new JButton("8");
		Button8.addActionListener(this);
		JButton Button9 = new JButton("9");
		Button9.addActionListener(this);
		JButton Button0 = new JButton("0");
		Button0.addActionListener(this);
		JButton starButton = new JButton("*");
		starButton.addActionListener(this);
		JButton hashButton = new JButton("#");
		hashButton.addActionListener(this);

		digitPanel.setLayout(new GridLayout(5, 3));
		digitPanel.add(sendButton);
		digitPanel.add(clearButton);
		digitPanel.add(endButton);
		digitPanel.add(Button1);
		digitPanel.add(Button2);
		digitPanel.add(Button3);
		digitPanel.add(Button4);
		digitPanel.add(Button5);
		digitPanel.add(Button6);
		digitPanel.add(Button7);
		digitPanel.add(Button8);
		digitPanel.add(Button9);
		digitPanel.add(starButton);
		digitPanel.add(Button0);
		digitPanel.add(hashButton);

		setLayout(new GridLayout(3, 1));
		add(displayTextField);
		add(digitPanel);
		add(new JLabel("jPhone Interface"));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			this.displayTextField.setText("");
		} else if (e.getActionCommand().equals("Call")) {

		} else if (e.getActionCommand().equals("End")) {

		} else {
			this.displayTextField.setText(this.displayTextField.getText()
					+ e.getActionCommand());
		}
	}

}
