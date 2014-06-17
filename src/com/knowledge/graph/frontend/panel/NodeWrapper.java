package com.knowledge.graph.frontend.panel;



public class NodeWrapper {
	private String name;
	private int ID;
	
	public NodeWrapper(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public int ID() {
		return ID;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
