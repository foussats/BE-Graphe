package org.insa.graphs.algorithm.utils;

import org.insa.graphs.model.io.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.insa.graphs.algorithm.*;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class optimisationTest {

	private static String nomCarteCarre;
	private static String nomCarteHG;
	
	private static BellmanFordAlgorithm BFnullLongPath_d, BFnullLongPath_t, BFimpossiblePathHG_d, BFimpossiblePathHG_t, BFpossiblePathCarre_d, BFpossiblePathCarre_t, BFpossiblePathHG_d, BFpossiblePathHG_t;
	private static DijkstraAlgorithm nullLongPath_d, nullLongPath_t, impossiblePathHG_d, impossiblePathHG_t, possiblePathCarre_d, possiblePathCarre_t, possiblePathHG_d, possiblePathHG_t;
	private static AStarAlgorithm AnullLongPath_d, AnullLongPath_t, AimpossiblePathHG_d, AimpossiblePathHG_t, ApossiblePathCarre_d, ApossiblePathCarre_t, ApossiblePathHG_d, ApossiblePathHG_t;
	
	
	@BeforeClass
	public static void init() throws IOException{
	nomCarteCarre = "C:\\Users\\momof\\OneDrive\\Bureau\\Cartes_BE_Graphe\\carre.mapgr";
	nomCarteHG = "C:\\Users\\momof\\OneDrive\\Bureau\\Cartes_BE_Graphe\\haute-garonne.mapgr";
	
	final GraphReader readerCarre = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nomCarteCarre))));
	
	final GraphReader readerHG = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nomCarteHG))));

	Graph carre = readerCarre.read();
	Graph hg = readerHG.read();
	
	readerCarre.close();
	readerHG.close();
	
	ArcInspector shortestPath = ArcInspectorFactory.getAllFilters().get(0);
	ArcInspector fastestPath = ArcInspectorFactory.getAllFilters().get(2);
	
	//Null path time and distance between two maps
	ShortestPathData longueurNulle_d = new ShortestPathData(carre, carre.getNodes().get(1), hg.getNodes().get(22), shortestPath);
	ShortestPathData longueurNulle_t = new ShortestPathData(carre, carre.getNodes().get(1), hg.getNodes().get(22), fastestPath);
	
	//Impossible path time and distance in a map
	
	final ShortestPathData cheminImpossibleHG_d = new ShortestPathData(hg, hg.get(66593), hg.get(121378), shortestPath);
	final ShortestPathData cheminImpossibleHG_t = new ShortestPathData(hg, hg.get(66593), hg.get(121378), fastestPath);
	
	//Possible path time and distance in a map
	final ShortestPathData cheminPossibleCarre_d = new ShortestPathData(carre, carre.getNodes().get(12), carre.getNodes().get(1), shortestPath);
	final ShortestPathData cheminPossibleCarre_t = new ShortestPathData(carre, carre.getNodes().get(12), carre.getNodes().get(1), fastestPath);
	
	final ShortestPathData cheminPossibleHG_d = new ShortestPathData(hg, hg.getNodes().get(12), hg.getNodes().get(1478), shortestPath);
	final ShortestPathData cheminPossibleHG_t = new ShortestPathData(hg, hg.getNodes().get(12), hg.getNodes().get(1478), fastestPath);


	
	impossiblePathHG_d = new DijkstraAlgorithm(cheminImpossibleHG_d);
	impossiblePathHG_t = new DijkstraAlgorithm(cheminImpossibleHG_t);
	
	nullLongPath_d = new DijkstraAlgorithm(longueurNulle_d);
	nullLongPath_t = new DijkstraAlgorithm(longueurNulle_t);
	
	possiblePathCarre_d = new DijkstraAlgorithm(cheminPossibleCarre_d);
	possiblePathCarre_t = new DijkstraAlgorithm(cheminPossibleCarre_t);
	
	possiblePathHG_d = new DijkstraAlgorithm(cheminPossibleHG_d);
	possiblePathHG_t = new DijkstraAlgorithm(cheminPossibleHG_t);
	
	
	
	AimpossiblePathHG_d = new AStarAlgorithm(cheminImpossibleHG_d);
	AimpossiblePathHG_t = new AStarAlgorithm(cheminImpossibleHG_t);
	
	AnullLongPath_d = new AStarAlgorithm(longueurNulle_d);
	AnullLongPath_t = new AStarAlgorithm(longueurNulle_t);
	
	ApossiblePathCarre_d = new AStarAlgorithm(cheminPossibleCarre_d);
	ApossiblePathCarre_t = new AStarAlgorithm(cheminPossibleCarre_t);
	
	ApossiblePathHG_d = new AStarAlgorithm(cheminPossibleHG_d);
	ApossiblePathHG_t = new AStarAlgorithm(cheminPossibleHG_t);
	
	
	
	BFimpossiblePathHG_d = new BellmanFordAlgorithm(cheminImpossibleHG_d);
	BFimpossiblePathHG_t = new BellmanFordAlgorithm(cheminImpossibleHG_t);
	
	BFnullLongPath_d = new BellmanFordAlgorithm(longueurNulle_d);
	BFnullLongPath_t = new BellmanFordAlgorithm(longueurNulle_t);
	
	BFpossiblePathCarre_d = new BellmanFordAlgorithm(cheminPossibleCarre_d);
	BFpossiblePathCarre_t = new BellmanFordAlgorithm(cheminPossibleCarre_t);
	
	BFpossiblePathHG_d = new BellmanFordAlgorithm(cheminPossibleHG_d);
	BFpossiblePathHG_t = new BellmanFordAlgorithm(cheminPossibleHG_t);
	
	}
	
	//@Test
	/*public void testIsValid() {
		//test Dijkstra
		assertTrue(nullLongPath_d.doRun().getPath().isValid());
		assertTrue(nullLongPath_t.doRun().getPath().isValid());
		assertTrue(possiblePathCarre_d.doRun().getPath().isValid());
		assertTrue(possiblePathCarre_t.run().getPath().isValid());
		assertTrue(possiblePathHG_d.doRun().getPath().isValid());
		assertTrue(possiblePathHG_t.doRun().getPath().isValid());
		assertTrue(impossiblePathHG_d.doRun().getPath().isValid());
		assertTrue(impossiblePathHG_t.doRun().getPath().isValid());
		
		//test A*
		assertTrue(AnullLongPath_d.doRun().getPath().isValid());
		assertTrue(AnullLongPath_t.doRun().getPath().isValid());
		assertTrue(ApossiblePathCarre_d.doRun().getPath().isValid());
		assertTrue(ApossiblePathCarre_t.run().getPath().isValid());
		assertTrue(ApossiblePathHG_d.doRun().getPath().isValid());
		assertTrue(ApossiblePathHG_t.doRun().getPath().isValid());
		assertTrue(AimpossiblePathHG_d.doRun().getPath().isValid());
		assertTrue(AimpossiblePathHG_t.doRun().getPath().isValid());
	}*/
	
	/*@Test
	public void testLongueur() {
		//Bellman-Ford et Dijkstra		
		assertEquals(nullLongPath_d.doRun().getPath().getLength(), BFnullLongPath_d.doRun().getPath().getLength(), 0);
		assertEquals(nullLongPath_t.doRun().getPath().getLength(), BFnullLongPath_t.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathCarre_d.doRun().getPath().getLength(), BFpossiblePathCarre_d.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathCarre_t.doRun().getPath().getLength(), BFpossiblePathCarre_t.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathHG_d.doRun().getPath().getLength(), BFpossiblePathHG_d.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathHG_t.doRun().getPath().getLength(), BFpossiblePathHG_t.doRun().getPath().getLength(), 0);
		//Dijkstra et A*
		assertEquals(nullLongPath_d.doRun().getPath().getLength(), AnullLongPath_d.doRun().getPath().getLength(), 0);
		assertEquals(nullLongPath_t.doRun().getPath().getLength(), AnullLongPath_t.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathCarre_d.doRun().getPath().getLength(), ApossiblePathCarre_d.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathCarre_t.doRun().getPath().getLength(), ApossiblePathCarre_t.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathHG_d.doRun().getPath().getLength(), ApossiblePathHG_d.doRun().getPath().getLength(), 0);
		assertEquals(possiblePathHG_t.doRun().getPath().getLength(), ApossiblePathHG_t.doRun().getPath().getLength(), 0);
	}*/
	
	@Test
	public void testLongueurImpossible() {
		assertTrue(impossiblePathHG_d.doRun().getPath().isEmpty());
		assertTrue(impossiblePathHG_t.doRun().getPath().isEmpty());
	}
	
	
	
}
