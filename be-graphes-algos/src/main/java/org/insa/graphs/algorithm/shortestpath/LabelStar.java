package org.insa.graphs.algorithm.shortestpath;

public class LabelStar extends Label implements Comparable<Label>{
	
	private double coutEstime;
	
	public LabelStar(int node, double coutEstime) {
		super (node);
		this.coutEstime = coutEstime;
	}
	
	public double getCoutEstime() {
		return this.coutEstime;
	}
	
	@Override
	public double getTotalCost() {
		return this.getCost() + this.getCoutEstime();
	}
	
	public double compareTo(LabelStar label) {
		if (this.getTotalCost() != label.getTotalCost()) {
			return Double.compare(this.getTotalCost(), label.getTotalCost());
		}
		else {
			return Double.compare(this.getCoutEstime(), label.getCoutEstime());
			}
		}
			
	}
	