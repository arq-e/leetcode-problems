class Solution {
    public long maxRunTime(int n, int[] batteries) {

        long sum = 0;
        for (int i = 0; i < batteries.length; ++i) {
            sum += batteries[i];
        }

        long right = sum / n;

        long left = 1;
        while (left < right) {
            long med = right - (right - left) / 2;
            if (allComputersCanRun(med, batteries, n)) {
                left = med;
            } else right = med - 1;
        }
        return left;
    }

    private boolean allComputersCanRun(long time, int[] batteries, int n) {
        long sum = 0;
        for (int i = 0; i < batteries.length; ++i) {
            sum += Math.min(batteries[i], time);
        }
        return sum >= n * time;
    }
}