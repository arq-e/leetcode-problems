class Solution {
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n+1][2];
        for (int i = 0; i <= n; ++i) {
            intervals[i][0] = i - ranges[i];
            intervals[i][1] = i + ranges[i];
        }
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        int leftEdge = 0;
        int newEdge = 0;
        int pos = 0;
        int count = 0;
        while (leftEdge <= n) {
            ++count;
            for (int i = pos; i <= n; ++i) {
                if (intervals[i][0] > leftEdge) break;
                if (intervals[i][1] > newEdge) {
                    newEdge = intervals[i][1];
                    pos = i;
                }
            }
            if (newEdge >= n) return count;
            else if (newEdge == leftEdge) break;
            else leftEdge = newEdge;
        }

        return -1;
    }
}