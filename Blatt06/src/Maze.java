import java.util.Collections;
import java.util.List;

/**
 * Class that represents a maze with N*N junctions.
 *
 * @author Vera Röhr
 */
public class Maze{
    private final int N;
    private Graph M;    //Maze
    public int startnode;

    public Maze(int N, int startnode) {

        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N);
        this.startnode= startnode;
        buildMaze();
    }

    public Maze (In in) {
        this.M = new Graph(in);
        this.N= (int) Math.sqrt(M.V());
        this.startnode=0;
    }


    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w)
    {
        M.addEdge(v, w);
    }

    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge( int v, int w)
    {
        if(v == w)
        {
            return true;
        }

        if(M.V() == 1 || M.V() == 0)
        {
            return false;
        }

        return M.adj(v).contains(w);
    }

    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
        Graph G = new Graph(N * N);

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                int v = i * N + j;

                if(i < N - 1)
                {
                    G.addEdge(v, v + N);
                }

                if(j < N - 1)
                {
                    G.addEdge(v, v + 1);
                }
            }
        }
        return G;
    }

    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
        Graph G = mazegrid();
        RandomDepthFirstPaths rnd = new RandomDepthFirstPaths(G, startnode);

        int[] edgeTo = rnd.edge();
        for(int v = 0; v < edgeTo.length; v++)
        {
            if(v != startnode)
            {
                int w = edgeTo[v];

                if(!hasEdge(v, w))
                {
                    addEdge(v, w);
                }

            }

        }

    }

    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w){
        DepthFirstPaths dfs = new DepthFirstPaths(M, v);
        dfs.dfs(M);

        if(!dfs.hasPathTo(w))
        {
            return null;
        }

        List<Integer> path = dfs.pathTo(w);
        Collections.reverse(path);

        return path;
    }

    /**
     * @return Graph M
     */
    public Graph M() {
        return M;
    }

    public static void main(String[] args) {    }


}

