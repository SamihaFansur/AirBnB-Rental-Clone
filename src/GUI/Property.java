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

public class Property {

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;
	
	public Property(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeProperty() {
		mainModule.currentState = STATE.PROPERTIES;
		try {
			frame = new JFrame();
			System.out.println("in register: "+frame);
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);
			System.out.println("after nav in register = "+mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}	
		
		frame.setVisible(true);
	}

}
