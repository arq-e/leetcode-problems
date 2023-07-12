class MinumumFallingPathSumTwo {
    public int minFallingPathSum(int[][] grid) {
        int[] min = new int[]{100,100};
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[grid.length-1][j] < min[0]) {
                min[1] = min[0];
                min[0] = grid[grid.length-1][j];
            } else if (grid[grid.length-1][j] < min[1]) {
                min[1] = grid[grid.length-1][j];
            }
        }
        for (int i = grid.length-2; i >=0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i+1][j] != min[0]) grid[i][j] += min[0];
                else grid[i][j] += min[1];
            }
            min[0] = Integer.MAX_VALUE;
            min[1] = Integer.MAX_VALUE;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < min[0]) {
                    min[1] = min[0];
                    min[0] = grid[i][j];
                } else if (grid[i][j] < min[1]) {
                    min[1] = grid[i][j];
                }
            }
        }
        int res = grid[0][0];
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j] < res) res = grid[0][j];

        }
        return res;
    }
}