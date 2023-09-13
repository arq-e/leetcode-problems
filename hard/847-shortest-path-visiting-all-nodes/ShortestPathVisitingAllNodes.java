class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Set<Integer>[] visited = new HashSet[n];
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            visited[i] = new HashSet<>();
            queue.add(new Node(i, 0, 1 << i));
            visited[i].add(1 << i);
        }

        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.visited == (1 << n) - 1) 
                return curr.dist;

            for (int i : graph[curr.node]) {
                int mask = curr.visited | (1 << i);
                if(visited[i].add(mask)) 
                    queue.add(new Node(i, curr.dist + 1, mask));
            }
        }
        return 0;
    }

    class Node {
        int visited;
        int dist;
        int node;
        Node(int node, int dist, int visited) {
            this.node = node;
            this.dist = dist;
            this.visited = visited;
        }
    }
}
