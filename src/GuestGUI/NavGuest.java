package GuestGUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.MainModule;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;

public class NavGuest extends JFrame{

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
	
	
//	public void close() {
//			
//			this.frame.dispose();
//	}
//	
	
	public void addGuestNav(JFrame frame, MainModule mainModule) {
		//System.out.println("IN NAVENQ");
		//System.out.println("in nav current state= "+mainModule);
		this.frame= frame;
		//System.out.println("in nav enq: "+frame);

		frame.getContentPane().setBackground(new Color(204, 255, 255));

		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		
		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

				mainModule.currentState=STATE.GUEST_ACCOUNT;
				mainModule.userState=USER.GUEST;
				MainModule.controller.drawNewView();
//				close();
				frame.dispose();
				
			}
		});
		navBarPanel.add(navHomeButton);
	
		
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.SEARCH;
				mainModule.userState=USER.GUEST;
				MainModule.controller.drawNewView();
//				close();
				frame.dispose();
			}
		});
		navBarPanel.add(navSearchButton);
		
		
		JButton navLogoutButton = new JButton("Logout");
		navLogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOMEPAGE;
				mainModule.userState = USER.ENQUIRER;
				frame.dispose();
				MainModule.controller.drawNewView();
//				close();
				frame.dispose();
			}
		});
		navBarPanel.add(navLogoutButton);

		
	}
	
	
	
	
}
