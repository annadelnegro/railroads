
import java.io.*;
import java.util.*;

public class Railroad {
    /* creating class variables */
    private int tracks;
    private String fileName;
    int cost = 0;

    /* tracks: total number of lines in each text file */
    public Railroad(int tracks, String fileName) {
        this.tracks = tracks;
        this.fileName = fileName;
    }

    /* buildRailroad method */
    public String buildRailroad() {
        Edge[] edgeArray = new Edge[tracks]; /* array of edges size tracks */
        HashSet<String> vertices = new HashSet<String>(); /* hash set to avoid repeating vertices */

        /* read and scan file & place into edgeArray */
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            int i = 0;
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                edgeArray[i] = new Edge(line[0], line[1], Integer.parseInt(line[2]));
                i++;
                /* add to vertices hashset */
                vertices.add(line[0]);
                vertices.add(line[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
         * size of vertices array to use with disjointset class given by professor
         * Steinberg
         */
        int len = vertices.size();

        /* beginning Kruskal's algorithm from this line (47) */
        DisjointSetImproved result = new DisjointSetImproved(len);

        /* add vertices to result array */
        for (String vertex : vertices) {
            result.addVertex(vertex);
        }

        /* sort array as per Kruskal algorithm (increasing order) */
        Arrays.sort(edgeArray, new Comparator<Edge>() {
            @Override
            public int compare(Edge first, Edge second) {
                return first.weight - second.weight;
            }
        });

        /* for each edge (u, v) from sorted order */
        for (Edge edge : edgeArray) {
            /* if findset(u) != findset(v) */
            if (result.find(edge.src) != result.find(edge.dest)) {
                /* union(u,v) */
                result.union(edge.src, edge.dest);
                cost += edge.weight;
                /* fixing printing to make sure alphabetical order is kept */
                if (edge.src.compareTo(edge.dest) > 0) {
                    String temp = edge.src;
                    edge.src = edge.dest;
                    edge.dest = temp;
                }
                System.out.println(edge.src + "---" + edge.dest + "\t$" + edge.weight);
            }
        }
        System.out.print("The cost of the railroad is $" + cost + ".");

        return "";

    }

    /* edge class & constructor */
    class Edge {
        String src, dest;
        int weight;

        public Edge(String src, String dest, int weight) {
            this.weight = weight;
            this.src = src;
            this.dest = dest;
        }
    }

    /* given by professor / changed parameters to string from int */
    class DisjointSetImproved {
        private HashMap<String, Integer> vertexIndexMap;
        private int[] rank;
        private int[] parent;
        private int n;

        public DisjointSetImproved(int n) {
            this.n = n;
            this.rank = new int[n];
            this.parent = new int[n];
            this.vertexIndexMap = new HashMap<>();
            makeSet();
        }

        /* changed from int parameter to string */
        public void makeSet() {
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /* changed from int parameter to string */
        public void addVertex(String vertex) {
            if (!vertexIndexMap.containsKey(vertex)) {
                int index = vertexIndexMap.size();
                vertexIndexMap.put(vertex, index);
            }
        }

        /* changed from int parameter to string */
        public int find(String vertex) {
            int index = vertexIndexMap.get(vertex);
            if (parent[index] != index) {
                parent[index] = find(vertexIndexMap.keySet().toArray(new String[0])[parent[index]]);
            }
            return parent[index];
        }

        /* changed from int parameter to string */
        public void union(String vertex1, String vertex2) {
            int root1 = find(vertex1);
            int root2 = find(vertex2);

            if (root1 != root2) {
                if (rank[root1] < rank[root2]) {
                    parent[root1] = root2;
                } else if (rank[root2] < rank[root1]) {
                    parent[root2] = root1;
                } else {
                    parent[root2] = root1;
                    rank[root1]++;
                }
            }
        }
    }
}
