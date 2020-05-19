package org.insa.graphs.algorithm.shortestpath;

import java.util.Arrays;
import java.util.Collections;


import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

import java.util.ArrayList;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
	public ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        
        
        int nbNodes = graph.size();
        // Initialize array of labels.
        Label[] labels = new Label[nbNodes];
        for (int i = 0; i<nbNodes; i++) {
        	labels[i] = new Label(i);
        }
        return Dijkstra(labels, graph, data);
    }
        		
        			
    protected ShortestPathSolution Dijkstra(Label[] labels, Graph graph, ShortestPathData data) {
        int sommet = data.getOrigin().getId();
        int destination = data.getDestination().getId();
        labels[sommet].setCost(0);    
        
     // Initialize heap
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        tas.insert(labels[sommet]);
        ;
       
     // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        int cpt = 0;
        while (!tas.isEmpty() && labels[destination].inconnu()) {
        	cpt++;
        	Label x = tas.deleteMin();
        	x.setConnu(true);
        	Node node = graph.getNodes().get(x.sommetCourant());
        	notifyNodeMarked(node);
        	int nbSuc = 0;
        	for (Arc arc : node.getSuccessors()) {
        		
        		int numLabel = arc.getDestination().getId();
        		if(labels[numLabel].inconnu() && data.isAllowed(arc)) {
        			update(tas, labels[numLabel], x, arc, data);  
        			
        		}
        		System.out.println("Cout label : " + labels[numLabel].getCost()+"\n");	
        		nbSuc++;
        		System.out.println("Nombre successeurs explorés depuis la node " + node.getId()+ " : " +  nbSuc + "\n");
        		System.out.println("Le tas : est valide : " + tas.isValid());
        	}
        	System.out.println("Nombre successeurs théoriques de la node " + node.getId() + " : " + node.getNumberOfSuccessors());
        }
        ShortestPathSolution solution;
        
        if (labels[destination].getPere() == null) {
        	Node nodeNull = null;
            solution = new ShortestPathSolution(data, Status.INFEASIBLE, new Path(data.getGraph(), nodeNull));
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = labels[destination].getPere();
            while (arc != null) {
                arcs.add(arc);
                arc = labels[arc.getOrigin().getId()].getPere();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }
        
        // Verify the path is valid
        if (solution.getPath().isValid()){
        	System.out.println("Le Path est valide\n");
        }

        
        return solution;
        
    }


public void update( BinaryHeap<Label> heap, Label y, Label x, Arc pere, ShortestPathData data) {
	double old_cost = y.getCost();
	double new_cost = x.getCost() + data.getCost(pere);
	if(old_cost>new_cost) {
		try {
			heap.remove(y);
		}catch(ElementNotFoundException e) {
		}
		y.setCost(new_cost);
		y.setPere(pere);
		heap.insert(y);
	}
}

}