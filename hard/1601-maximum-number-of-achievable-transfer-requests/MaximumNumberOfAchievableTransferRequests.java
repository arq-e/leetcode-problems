class MaximumNumberOfAchievableTransferRequests {
    public int maximumRequests(int n, int[][] requests) {
        int len = requests.length;

        int[] indegree = new int[n];
        return recursion(0, n, requests, indegree,0);
    }

    private int recursion(int pos, int n, int[][] requests, int[] indegree, int res) {
        if (pos == requests.length) {
            for (int i = 0; i < n; ++i) {
                if (indegree[i] != 0) return 0;
            }
            return res;
        }

        ++indegree[requests[pos][0]];
        --indegree[requests[pos][1]];
        int first = recursion(pos+1, n, requests, indegree, res + 1);

        --indegree[requests[pos][0]];
        ++indegree[requests[pos][1]];
        int second = recursion(pos+1, n, requests, indegree, res);
        return Math.max(first, second);
    }
}