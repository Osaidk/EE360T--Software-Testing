package pset1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class GraphTester {

    @Test
    public void tae0() {  // tests for method "addEdge" in class "Graph"
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        assertEquals("numNodes: 2\nedges: [[false, true], [false, false]]", g.toString());
    }

    @Test
    public void tae1() { // cyclic graph reachable
        Graph k = new Graph(3);
        k.addEdge(0, 2);
        k.addEdge(2, 0);
        assertEquals("numNodes: 3\nedges: [[false, false, true], [false, false, false], [true, false, false]]", k.toString());
    }

    @Test
    public void tae2() { // Adding edge to an illegal node
        Graph g = new Graph(1);
        g.addEdge(-1, 0);
        assertTrue(g.illegalNode(-1));
    }

    @Test
    public void tae3() { // Adding legal edges to legal nodes with cycles
        Graph g = new Graph(4);
        g.addEdge(0,0);
        g.addEdge(1,3);
        g.addEdge(0,3);
        g.addEdge(3,0);
        g.addEdge(2,0);
        assertEquals(g.toString(), "numNodes: 4\nedges: [[true, false, false, true], " +
                "[false, false, false, true], [true, false, false, false], [true, false, false, false]]");
    }

    @Test
    public void tae4() { // Adding edge to an illegal node
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        g.addEdge(1, 1);
        g.addEdge(0, 2);
        assertTrue(g.illegalNode(2));
    }

    // your tests for method "addEdge" in class "Graph" go here

    // you must provide at least 4 test methods;
    // each test method has at least 1 invocation of addEdge;
    // each test method creates exactly 1 graph
    // each test method creates a unique graph w.r.t. "equals" method
    // each test method has at least 1 test assertion;
    // your test methods provide full statement coverage of your
    //   implementation of addEdge and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework

    // ...


    // tests for method "reachable" in class "Graph"
    @Test
    public void tr0() { // Self reachable
        Graph g = new Graph(1);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        assertTrue(g.reachable(nodes, nodes));
    }
    // your tests for method "reachable" in class "Graph" go here

    // you must provide at least 6 test methods;
    // each test method must have at least 1 invocation of reachable;
    // each test method must have at least 1 test assertion;
    // at least 2 test methods must have at least 1 invocation of addEdge;
    // your test methods must provide full statement coverage of your
    //   implementation of reachable and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework

    // ...

    @Test
    public void tr1() {    //no source node reaches the target node 2. Should fail.
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        Set<Integer> Sources = new TreeSet<Integer>();
        Set<Integer> Targets = new TreeSet<Integer>();
        Sources.add(0);
        Sources.add(1);
        Targets.add(2);
        assertFalse(g.reachable(Sources, Targets));
    }

    @Test
    public void tr2() {    //Every source node should be able to reach itself.
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        Set<Integer> Sources = new TreeSet<Integer>();
        Set<Integer> Targets = new TreeSet<Integer>();
        Sources.add(0);
        Sources.add(2);
        Sources.add(3);
        Targets.add(0);
        Targets.add(1);
        Targets.add(2);
        Targets.add(3);
        assertTrue(g.reachable(Sources, Targets));
    }

    @Test
    public void tr3() {	// Illegal source node
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        Set<Integer> Sources = new TreeSet<Integer>();
        Set<Integer> Targets = new TreeSet<Integer>();
        Sources.add(2);
        Sources.add(4);
        Targets.add(0);
        Targets.add(1);
        assertFalse(g.reachable(Sources, Targets));
    }

    @Test
    public void tr4() {    // Illegal target node
        Graph g = new Graph(3);
        g.addEdge(1, 0);
        g.addEdge(2, 1);
        Set<Integer> Sources = new TreeSet<Integer>();
        Set<Integer> Targets = new TreeSet<Integer>();
        Sources.add(1);
        Sources.add(2);
        Targets.add(0);
        Targets.add(-1);
        assertFalse(g.reachable(Sources, Targets));
    }
    
    @Test
    public void tr5() {	// One source node reaches every target node
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        Set<Integer> Sources = new TreeSet<Integer>();
        Set<Integer> Targets = new TreeSet<Integer>();
        Sources.add(0);
        Targets.add(0);
        Targets.add(1);
        Targets.add(2);
        Targets.add(3);
        Targets.add(4);
        Targets.add(5);
        assertTrue(g.reachable(Sources, Targets));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tr6() {	//source node set is null. Exception is thrown.
        Graph g = new Graph(3);
        g.addEdge(2, 1);
        Set<Integer> Targets = new TreeSet<Integer>();
        assertFalse(g.reachable(null, Targets));
    }

    @Test public void tr7() {	// No source node reaches target node [3].
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        Set<Integer> Sources = new TreeSet<Integer>();
        Set<Integer> Targets = new TreeSet<Integer>();
        Sources.add(0);
        Sources.add(1);
        Sources.add(2);
        Targets.add(0);
        Targets.add(3);
        assertFalse(g.reachable(Sources, Targets));
    }

    @Test
    public void UniqueGraphChecker() {    // Optional uniqueness test for all graphs used in this module.
        boolean Unique = true;
        ArrayList<Graph> TestGraphs = new ArrayList<Graph>();

        Graph tae0 = new Graph(2);
        tae0.addEdge(0, 1);
        TestGraphs.add(tae0);

        Graph tae1 = new Graph(3);
        tae1.addEdge(0, 2);
        tae1.addEdge(2, 0);
        TestGraphs.add(tae1);

        Graph tae2 = new Graph(1);
        tae2.addEdge(-1, 0);
        TestGraphs.add(tae2);

        Graph tae3 = new Graph(4);
        tae3.addEdge(0,0);
        tae3.addEdge(1,3);
        tae3.addEdge(0,3);
        tae3.addEdge(3,0);
        tae3.addEdge(2,0);
        TestGraphs.add(tae3);

        Graph tae4 = new Graph(2);
        tae4.addEdge(0, 1);
        tae4.addEdge(1, 1);
        tae4.addEdge(0, 2);
        TestGraphs.add(tae4);

        Graph tr0 = new Graph(1);
        TestGraphs.add(tr0);

        Graph tr1 = new Graph(3);
        tr1.addEdge(0, 1);
        TestGraphs.add(tr1);

        Graph tr2 = new Graph(4);
        tr2.addEdge(0, 1);
        TestGraphs.add(tr2);

        Graph tr3 = new Graph(3);
        tr3.addEdge(0, 1);
        tr3.addEdge(1, 2);
        TestGraphs.add(tr3);

        Graph tr4 = new Graph(3);
        tr4.addEdge(1, 0);
        tr4.addEdge(2, 1);
        TestGraphs.add(tr4);

        Graph tr5 = new Graph(6);
        tr5.addEdge(0, 1);
        tr5.addEdge(0, 2);
        tr5.addEdge(1, 3);
        tr5.addEdge(1, 5);
        tr5.addEdge(2, 3);
        tr5.addEdge(2, 4);
        TestGraphs.add(tr5);

        Graph tr6 = new Graph(3);
        tr6.addEdge(2, 1);
        TestGraphs.add(tr6);

        Graph tr7 = new Graph(4);
        tr7.addEdge(0, 1);
        tr7.addEdge(1, 2);
        tr7.addEdge(2, 0);
        TestGraphs.add(tr7);

        for (Graph graph1 : TestGraphs) {
            for (Graph graph2 : TestGraphs) {
                if (TestGraphs.indexOf(graph1) != TestGraphs.indexOf(graph2)) {
                    if (graph1.equals(graph2)) {
                        Unique = false;
                    }
                }
            }
        }

        assertTrue(Unique);
    }
}

