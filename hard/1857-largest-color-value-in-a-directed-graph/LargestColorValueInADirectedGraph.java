import java.util.*;

class LargestColorValueInADirectedGraph {

    Map<Integer, int[]> postColors = new HashMap<>();
    public int largestPathValue(String colors, int[][] edges) {
        if (edges.length == 0) return 1;
        int n = colors.length();

        Map<Integer, List<Integer>> connections = new HashMap<>();
        int[] indegrees = new int[n];
        for (int i = 0; i < n; ++i) {
            connections.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            connections.get(edge[0]).add(edge[1]);
            ++indegrees[edge[1]];
        }
        LinkedList<Integer> zeroIndegrees = new LinkedList<>();
        int[][] colorsCounts = new int[n][26];
        for (int i = 0; i < n; ++i) {
            if (indegrees[i] == 0) zeroIndegrees.offer(i);
            ++colorsCounts[i][colors.charAt(i)-'a'];
        }
        int max = 0;
        int visited = 0;
        while (zeroIndegrees.size() > 0) {
            int curr = zeroIndegrees.poll();
            for (int next : connections.get(curr)) {
                for (int i = 0; i < 26; ++i) {
                    colorsCounts[next][i] = Math.max(colorsCounts[next][i], colorsCounts[curr][i]
                            + (colors.charAt(next) - 'a' == i ? 1 : 0));

                }
                --indegrees[next];
                if (indegrees[next] == 0) zeroIndegrees.offer(next);
            }
            ++visited;
            max = Math.max(max, Arrays.stream(colorsCounts[curr]).max().getAsInt());
        }
        return visited == n ? max : -1;
    }
}