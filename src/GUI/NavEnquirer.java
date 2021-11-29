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
		System.out.println("getting frame from navenq");
		return frame;
	}
	public void setMainModule(MainModule mainModule) {
		this.mainModule = mainModule;
	}

	public MainModule getMainModule() {
		System.out.println("getting main module from navenq");
		return mainModule;
	}


//	public void close() {
//
//			this.frame.dispose();
//	}
//

	public void addNavBeforeLogin(JFrame frame, MainModule mainModule) {
		//System.out.println("IN NAVENQ");
		//System.out.println("in nav current state= "+mainModule);
		this.frame= frame;
		//System.out.println("in nav enq: "+frame);

		frame.getContentPane().setBackground(new Color(204, 255, 255));

		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);

		System.out.println("----------------^^^^^^^^^^^^^^^^^^-----------Initialise homepage");

		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

				mainModule.currentState=STATE.HOMEPAGE;
				MainModule.controller.drawNewView();
//				close();
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
//				close();
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
//				close();
				frame.dispose();
						//Login sp = new Login();
				System.out.println("************in the nav enquirere file at login button**********");
			}
		});
		navBarPanel.add(navLoginButton);

		JButton navContactButton = new JButton("Contact");
		navContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				mainModule.currentState=STATE.CONTACT_US;
				MainModule.controller.drawNewView();
//				close();
				frame.dispose();
				//Register sp = new Register();
			}
		});
		navBarPanel.add(navContactButton);
	}




}
