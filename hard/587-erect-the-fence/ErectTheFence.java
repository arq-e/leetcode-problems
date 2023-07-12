import java.util.ArrayList;
import java.util.List;

class ErectTheFence {

    public int[][] outerTrees(int[][] trees) {
        if (trees.length < 4) return trees;
        List<int[]> list = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < trees.length; i++) {
            if (trees[i][0] < trees[prev][0]) {
                prev = i;
            }
        }
        int first = prev;
        list.add(trees[prev]);
        while(list.size() < trees.length) {
            int right = (prev + 1) % trees.length;
            for(int i = 0; i < trees.length; i++) {
                if (rotate(trees[prev], trees[right], trees[i])  < 0) {
                    right = i;
                }
            }
            for (int i = 0; i < trees.length; i++) {
                if (i != prev && i != right
                        && rotate(trees[prev], trees[right], trees[i]) == 0
                        && inBetween(trees[prev], trees[right], trees[i])) {
                    list.add(trees[i]);
                }
            }
            if (right == first) break;
            prev = right;
            list.add(trees[right]);
        }
        return list.toArray(new int[list.size()][]);
    }

    public int rotate(int[] a, int[] b, int[] c){
        return (b[0] - a[0])*(c[1]-b[1]) - (b[1] - a[1])*(c[0]-b[0]);
    }

    public boolean inBetween(int[]a, int[] b, int[] c) {
        boolean x = (c[0] >= a[0] && c[0] <= b[0] || c[0] >= b[0] && c[0] <= a[0]);
        boolean y = (c[1] >= a[1] && c[1] <= b[1] || c[1] >= b[1] && c[1] <= a[1]);
        return x && y;
    }
}