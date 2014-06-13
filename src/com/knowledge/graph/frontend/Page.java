package com.knowledge.graph.frontend;

import javax.swing.JFrame;

public abstract class Page extends JFrame {

  public Page() {
    setTitle("The Complete Student Knowledge Base");
    setSize( 800 , 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  } 
}