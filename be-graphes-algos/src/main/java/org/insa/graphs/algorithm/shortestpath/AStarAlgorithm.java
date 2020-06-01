package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Graph;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    public ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        
        int nbNodes = graph.size();
        // Initialize array of labels.
        Label[] labels = new LabelStar[nbNodes];
        for (int i = 0; i<nbNodes; i++) {
        	if (this.data.getMode() == AbstractInputData.Mode.TIME) {
        		int vitesse_graph_max = graph.getGraphInformation().getMaximumSpeed();
        		int vitesseDataMax = this.data.getMaximumSpeed();
        		int vitesse;
        		if (vitesseDataMax == -1) {
        			vitesse = vitesse_graph_max;
        		}else if(vitesse_graph_max == -1) {
        			vitesse = vitesseDataMax;
        		}else if(vitesseDataMax == -1 && vitesse_graph_max == -1) {
        			vitesse = 130;
        		}else if (vitesseDataMax<vitesse_graph_max) {
        			vitesse = vitesseDataMax;        			
        		}else {
        			vitesse = vitesse_graph_max;
        		}	
        		double h = ((graph.getNodes().get(i).getPoint().distanceTo(data.getDestination().getPoint())) / (double)vitesse)*3.6;
        		labels[i] = new LabelStar(i, h);
        	}else {
        		double h = graph.get(i).getPoint().distanceTo(data.getDestination().getPoint());
        		labels[i] = new LabelStar(i, h);
        	}
        	
        }
        return Dijkstra(labels, graph, data);
    }
    
}
