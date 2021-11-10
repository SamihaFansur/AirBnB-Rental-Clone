package GUI;
import Controller.*;
import GUI.MainModule.STATE;
import Model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Account {

	//private Controller controller;
	private Model model;
	private Controller controller;
	private MainModule mainModule;
	private JFrame frame;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	
	
	public Account(MainModule mainModule, Controller controller, Model model) {
		//initialize();
		this.mainModule=mainModule;
		this.model=model;
		this.controller=controller;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeAccount() {
		mainModule.currentState = STATE.ACCOUNT;
		try {
			frame = new JFrame();
			System.out.println("in register: "+frame);
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);
			System.out.println("after nav in register = "+mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}	
		
	}

}
