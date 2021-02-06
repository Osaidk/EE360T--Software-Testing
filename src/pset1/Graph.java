package pset1;

import java.util.*;

public class Graph {
    private int numNodes; // number of nodes in the graph
    private boolean[][] edges;

    public Graph(int size) {
        numNodes = size;
        edges = new boolean[numNodes][numNodes];
    }

    public String toString() {
        return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
    }

    public boolean equals(Object o) {
        if (o.getClass() != Graph.class) return false;
        return toString().equals(o.toString());
    }

    public void addEdge(int from, int to) {
        if (!illegalNode(from) && !illegalNode(to))
            edges[from][to] = true;
    }

    public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
        Set<Integer> ReachedNodes = new TreeSet<Integer>();
        HashMap<Integer, Integer> VisitedNodes = new HashMap<Integer, Integer>();
        if (sources == null || targets == null) throw new IllegalArgumentException();

        //(1) "sources" does not contain an illegal node
        for (Integer node : sources) {
            if(illegalNode(node))
                return false;
        }

        //(2) "targets" does not contain an illegal node
        for (Integer node: targets) {
            if(illegalNode(node))
                return false;
        }

        //(3) for each node "m" in set "targets", there is some
        //node "n" in set "sources" such that there is a directed
        //path that starts at "n" and ends at "m" in "this"; and
        //false otherwise
        for (int source : sources) {
            reached(source, targets, ReachedNodes, VisitedNodes);
        }
        for (Integer target : targets) {
            if (!ReachedNodes.contains(target)) {
                return false;
            }
        }
        return true;
    }

    public void reached(Integer source, Set<Integer> targets, Set<Integer> reached, HashMap<Integer, Integer> Visited) {
        if (targets.contains(source)) {
            reached.add(source);
        }
        for (int i = 0; i < numNodes; i++) {
            if (Visited.containsKey(source) && Visited.get(source) == i) {
                continue;
            } else if (edges[source][i]) {
                Visited.put(source, i);
                reached(i, targets, reached, Visited);
            }
            Visited.put(source, i);
        }

    }

    public boolean illegalNode(Integer node) {
        return node < 0 || node >= numNodes;
    }
}


