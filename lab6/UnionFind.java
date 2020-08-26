
public class UnionFind {

    // TODO - Add instance variables?
    private int parent[];

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception {
        // TODO
        if (vertex < 0 || vertex > parent.length){
            throw new Exception("not valid");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
//        int p = v1;
//        while (parent[p] >= 0){
//            p = parent[p];
//        }
//        return p*-1;
        int size = -1;
        for (int i = 0; i < parent.length; i++){
            if (connected(i, v1)){
                size += 1;
            }
        }
        return size*-1;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);
        int i = find(v1);
        int j = find(v2);
        parent[i] = j;
        parent[j] = -s1-s2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int r = vertex;
        while (parent[r] >= 0){
            r = parent[r];
        }
        return r;
    }
}
