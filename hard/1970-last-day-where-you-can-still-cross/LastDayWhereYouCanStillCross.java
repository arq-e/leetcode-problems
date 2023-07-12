import java.util.*;

class LastDayWhereYouCanStillCross {
    public int latestDayToCross(int row, int col, int[][] cells) {
        if (row == 1) return col - 1;
        int[][] grid = new int[row][col];
        for (int i = 0; i < row*col; ++i) {
            grid[cells[i][0]-1][cells[i][1]-1] = i;
        }

        int left = 0;
        int right = row*col;
        int med = (left + right) / 2;
        while (left < right) {
            if (checkRoutePossibility(med, grid, row, col)) {
                left = med + 1;
            } else right = med;
            med = (left + right) / 2;
        }
        return left;
    }

    private boolean checkRoutePossibility(int day,  int[][] grid, int row, int col) {
        Deque<int[]> deque = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < col; ++i) {
            if (grid[0][i] > day) {
                deque.addLast(new int[]{0,i});
                visited.add(grid[0][i]);
            }
        }

        int[] dirs = new int[]{0,-1,0,1,0};
        while(deque.size() > 0) {
            int[] pos = deque.pollLast();
            for (int i = 0; i < 4;) {
                int[] newPos = Arrays.copyOf(pos, 2);
                newPos[0] += dirs[i];
                newPos[1] += dirs[++i];
                if (newPos[0] >= 0 && newPos[0] < row && newPos[1] >= 0 && newPos[1] < col) {
                    if (grid[newPos[0]][newPos[1]] > day && !visited.contains(grid[newPos[0]][newPos[1]])) {
                        if (newPos[0] == row - 1) return true;
                        deque.add(newPos);
                        visited.add(grid[newPos[0]][newPos[1]]);
                    }
                }
            }
        }
        return false;
    }
}