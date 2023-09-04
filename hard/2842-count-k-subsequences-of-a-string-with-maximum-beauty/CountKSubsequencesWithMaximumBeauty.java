class Solution {
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int div = 1000000007;

        if (k > 26) return 0;
        int[][] occ = new int[26][2];
        for (int i = 0; i < 26; ++i) occ[i][1] = i;
        for (int i = 0; i < s.length(); ++i) {
            ++occ[s.charAt(i) -'a'][0];
        }

        Arrays.sort(occ, (a,b) -> b[0] - a[0]);

        long res = 1;
        int start = k - 1;

        if (k == 26 || occ[k][0] < occ[k-1][0]) {
            for (int i = 0; i < k; ++i) {
                res *= occ[i][0];
                res %= div;
            }
            return (int)res;
        } else {
            for (int i = 0; i < k-1; ++i) {
                if (occ[i][0] == occ[k-1][0]) {
                    start = i;
                    break;
                }
                res *= occ[i][0];
                res %= div;

            }

            int count = 0;
            for (int i = start; i < 26; ++i) {
                if (occ[i][0] == occ[k-1][0]) ++count;
                else break;
            }

            res *= countCombinations(count, k - start, div);
            res %= div;
            for (int i = 0; i<Math.min(count-1, k-start); ++i) {
                res *= occ[k-1][0];
                res %= div;
            }

            return (int)res;
        }
    }

    public int countCombinations(int n, int k, int div) {
        long num = 1;
        for (int i = 1; i <= n; ++i) {
            num *= i;
            if (num <= Math.max(k, n-k)) num /= i;
        }
        if (k < n - k) {
            for (int i = 1; i <= k; ++i) {
                num /= i;
            }
        } else {
            for (int i = 1; i <= n-k; ++i) {
                num /= i;
            }
        }

        return (int)num;
    }
}