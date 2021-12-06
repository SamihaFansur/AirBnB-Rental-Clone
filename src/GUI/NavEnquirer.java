package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.MainModule.STATE;

/*
 * Creates a NavBar for an enquirer
 */
public class NavEnquirer extends JFrame{
	public NavEnquirer() {
	}

	//Initiliases the frame for the GUI
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
	
	//Function to add a navBar for enquirer when no user is logged in
	public void addNavBeforeLogin(JFrame frame, MainModule mainModule) {
		this.frame= frame;
		frame.getContentPane().setBackground(new Color(204, 255, 255));

		//Creates and adds the navBarPanel
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		//Creates and adds the home button to the navBar
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
		
		//Creates and adds the searchPage button to the navbar
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
		
		//Creates and adds the register page button to the navbar
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
		
		//Creates and adds the login page button to the navbar
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
