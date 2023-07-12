
import java.util.ArrayDeque;
import java.util.Deque;
class NumberOfIncreasingPathsInAGrid {

    private static int mod = 1000000007;
    public int countPaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                res += bfs(i, j, dp, grid, n, m) % mod;
                res %= mod;
            }
        }
        return res;
    }

    private int bfs(int x, int y, int[][] dp, int[][] grid, int n, int m) {
        if (dp[x][y] > 0) return dp[x][y];
        int[] dirs = new int[]{1,0,-1,0,1};
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{x, y});
        while (deque.size() > 0) {
            int[] curr = deque.peekLast();
            int deadEnd = 0;
            int localCount = 0;
            for (int i = 0; i < 4; ++i) {
                int[] next = new int[]{curr[0]+dirs[i], curr[1]+dirs[i+1]};
                if (next[0] >= 0 && next[1] >= 0 && next[0] < n && next[1] < m) {
                    if (grid[next[0]][next[1]] > grid[curr[0]][curr[1]]) {
                        if (dp[next[0]][next[1]] > 0)  {
                            ++deadEnd;
                            localCount += dp[next[0]][next[1]] % mod;
                        } else {
                            deque.add(next);
                        }
                    } else ++deadEnd;
                } else {
                    ++deadEnd;
                }
            }
            if (deadEnd == 4) {
                deque.pollLast();
                dp[curr[0]][curr[1]] = 1 + localCount;
            }
        }
        return dp[x][y] % mod;
    }
}