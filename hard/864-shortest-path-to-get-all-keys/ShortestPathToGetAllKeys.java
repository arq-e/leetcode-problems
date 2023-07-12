import java.util.*;
class ShortestPathToGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();

        char[][] charGrid = new char[n][];
        for (int i = 0; i < n; ++i) {
            charGrid[i] = grid[i].toCharArray();
        }

        int x = -1;
        int y = -1;

        Map<Integer, int[]> keys = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0 ; j < m; ++j) {
                if (charGrid[i][j] == '@') {
                    x = i;
                    y = j;
                    charGrid[x][y] = '.';
                } else if (charGrid[i][j] - 'a' < 6 && charGrid[i][j] - 'a' >= 0) {
                    keys.put(charGrid[i][j] - 'a', new int[]{i, j});
                }

            }
        }

        return bfs(charGrid, x, y, keys);
    }

    private int bfs(char[][] grid, int x, int y, Map<Integer, int[]> keys) {
        Deque<int[]> deque = new ArrayDeque<>();

        int[] start = new int[4 + keys.size()];
        start[0] = x;
        start[1] = y;
        deque.add(start);

        int minDist = Integer.MAX_VALUE;
        while(deque.size() > 0) {
            int[] pos = deque.pollFirst();
            for (int keyNum : keys.keySet()) {
                if (!(pos[4+keyNum] == 1)) {
                    int dist = findTheWay(pos[0], pos[1], keyNum, keys.get(keyNum), grid.clone(), Arrays.copyOfRange(pos, 4, pos.length));
                    if (dist > 0) {
                        int[] newPos = pos.clone();
                        newPos[0] = keys.get(keyNum)[0];
                        newPos[1] = keys.get(keyNum)[1];
                        newPos[4+keyNum] = 1;
                        newPos[2] += dist;
                        ++newPos[3];
                        if (newPos[3] == keys.size()) {
                            minDist = Math.min(minDist, newPos[2]);
                        } else {
                            deque.add(newPos);
                        }
                    }
                }
            }
        }
        return minDist < Integer.MAX_VALUE ? minDist : -1;
    }

    private int findTheWay(int x, int y, int keyNum, int[] keyPos, char[][] gridBasic, int[] ownedKeys) {
        Set<Character> openedDoors = new HashSet<>();
        for (int i = 0; i < ownedKeys.length; ++i) {
            if (ownedKeys[i] == 1) {
                openedDoors.add((char)('A' + i));
            }
        }

        char[][] grid = new char[gridBasic.length][gridBasic[0].length];
        for (int i = 0; i < grid.length; ++i) {
            grid[i] = Arrays.copyOf(gridBasic[i],grid[i].length);
        }

        char key = (char)('a' + keyNum);
        Deque<int[]> positions = new ArrayDeque<>();
        positions.add(new int[]{x, y, 0});
        grid[x][y] = ',';

        int[] dirs = new int[]{1,0,-1,0,1};
        while(positions.size() > 0) {
            int[] pos = positions.pollFirst();
            for (int i = 0; i < 4;) {
                int[] newPos = pos.clone();
                newPos[0] += dirs[i];
                newPos[1] += dirs[++i];
                ++newPos[2];
                if (newPos[0] >= 0 && newPos[0] < grid.length && newPos[1] >= 0 && newPos[1] < grid[0].length) {
                    if (grid[newPos[0]][newPos[1]] == key) {
                        return newPos[2];
                    } else if (grid[newPos[0]][newPos[1]] == '.'
                            || grid[newPos[0]][newPos[1]] - 'a' >= 0 && grid[newPos[0]][newPos[1]] - 'a' < 6) {
                        positions.add(newPos);
                        grid[newPos[0]][newPos[1]] = ',';
                    } else if (grid[newPos[0]][newPos[1]] - 'A' < 6 && grid[newPos[0]][newPos[1]] - 'A' >= 0) {
                        if (openedDoors.contains(grid[newPos[0]][newPos[1]])) {
                            positions.add(newPos);
                            grid[newPos[0]][newPos[1]] = ',';
                        }
                    }
                }
            }
        }

        return -1;
    }
}