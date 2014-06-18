package com.knowledge.graph.frontend;

import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Page extends JFrame {

  public Page() {
    setTitle("The Complete Student Knowledge Base");
    setSize( 800 , 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  } 
  
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	protected boolean isValidSID(String s) {
		if ( s!="" && isInteger(s)) {
			return true;
		} else {
			return false;
		}
	}

}