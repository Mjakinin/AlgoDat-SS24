import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        this.s = s;
        int V = G.V();
        parent = new int[V];
        dist = new double[V];

        for(int v = 0; v < V; v++)
        {
            dist[v] = Double.POSITIVE_INFINITY;
            parent[v] = -1;
        }
        dist[s] = 0.0;

        TopologicalWD topological = new TopologicalWD(G);
        topological.dfs(s);
        Stack<Integer> order = topological.order();

        while(!order.isEmpty())
        {
            int v = order.pop();

            for(DirectedEdge e : G.incident(v))
            {
                relax(e);
            }

        }

    }

    public void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();

        if(dist[w] > dist[v] + e.weight())
        {
            dist[w] = dist[v] + e.weight();
            parent[w] = v;
        }

    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

