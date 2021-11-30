package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.MainModule.STATE;

public class NavEnquirer extends JFrame{
	public NavEnquirer() {
	}

	public JFrame frame;
	private MainModule mainModule;

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}
	public void setMainModule(MainModule mainModule) {
		this.mainModule = mainModule;
	}

	public MainModule getMainModule() {
		return mainModule;
	}

	public void addNavBeforeLogin(JFrame frame, MainModule mainModule) {
		this.frame= frame;
		frame.getContentPane().setBackground(new Color(204, 255, 255));

		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);

		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.HOMEPAGE;
				MainModule.controller.drawNewView();
				frame.dispose();
			}
		});
		navBarPanel.add(navHomeButton);

		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.SEARCH;
				MainModule.controller.drawNewView();
				frame.dispose();
			}
		});
		navBarPanel.add(navSearchButton);

		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.SELF_REGISTRATION;
				MainModule.controller.drawNewView();
				frame.dispose();
			}
		});
		navBarPanel.add(navRegisterButton);

		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.LOGIN;
				MainModule.controller.drawNewView();
				frame.dispose();
			}
		});
		navBarPanel.add(navLoginButton);

	}
}
