class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[][] numEdges = new int[edges.length][4];
        for (int i = 0; i < edges.length; ++i) {
            numEdges[i] = new int[]{edges[i][0], edges[i][1], edges[i][2], i};
        }
        Arrays.sort(numEdges, (a, b) -> a[2] - b[2]);
        int fullMSTWeight = findMSTWeight(numEdges, new HashMap<>(), new HashSet<>(), 0, n);

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        for (int i = 0; i < numEdges.length; ++i) {
            int num = numEdges[i][3];
            numEdges[i][3] = -1;
            int MSTWeight = findMSTWeight(numEdges, new HashMap<>(), new HashSet<>(), 0, n);
            //System.out.println(num + " " + MSTWeight);
            if (MSTWeight > fullMSTWeight || MSTWeight < 0) res.get(0).add(num);
            else {
                Set<Integer> visited = new HashSet<>();
                Map<Integer, List<Integer>> connections = new HashMap<>();
                visited.add(numEdges[i][0]);
                visited.add(numEdges[i][1]);
                connections.put(numEdges[i][0], new ArrayList<>());
                connections.put(numEdges[i][1], new ArrayList<>());
                connections.get(numEdges[i][0]).add(numEdges[i][1]);
                connections.get(numEdges[i][1]).add(numEdges[i][0]);
                int sum = findMSTWeight(numEdges, connections, 
                        visited, numEdges[i][2], n);
                if (sum == fullMSTWeight)         
                        res.get(1).add(num);
            }
            numEdges[i][3] = num;
        }

        return res;
    }

    public int findMSTWeight(int[][] edges, Map<Integer, List<Integer>> connections, Set<Integer> visited, int sum, int n) {
        int countEdges = 0;
        if (sum > 0) ++countEdges;
        for (int i = 0; i < edges.length; ++i) {
            if (edges[i][3] != -1) {
                if (!checkCycle(connections, edges[i][0], edges[i][1])) {
                    visited.add(edges[i][0]);
                    visited.add(edges[i][1]);
                    if (!connections.containsKey(edges[i][0]))
                        connections.put(edges[i][0], new ArrayList<>());
                    if (!connections.containsKey(edges[i][1]))
                        connections.put(edges[i][1], new ArrayList<>());
                    connections.get(edges[i][0]).add(edges[i][1]);
                    connections.get(edges[i][1]).add(edges[i][0]);
                    sum += edges[i][2];
                    ++countEdges;
                    if (countEdges == n - 1) break;
                }
            }
        }
        if (countEdges < n - 1) return - 1;
        return sum;
    }

    public boolean checkCycle(Map<Integer, List<Integer>> connections, int x, int y) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(x);
        visited.add(x);
        while(deque.size() > 0) {
            int curr = deque.pollLast();
            if (connections.get(curr) != null) {
                for (int i : connections.get(curr)) {
                    if (!visited.contains(i)) {
                        if (i == y) return true;
                        visited.add(i);
                        deque.add(i);
                    }
                }
            }

        }
        return false;
    }
}