class Solution {
    public int largestVariance(String s) {
        int n = s.length();
        boolean[] contained = new boolean[26];
        for (int i = 0; i < n; ++i) {
            contained[s.charAt(i) - 'a'] = true;
        }

        int[][] counts = new int[26][26];
        boolean[][] started = new boolean[26][26];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int curr = s.charAt(i) - 'a';
            for (int j = 0; j < 26; ++j) {
                if (j == s.charAt(i) - 'a') {
                    for (int k = 0; k < 26; ++k) {
                        if (j != k && contained[k]) {
                            ++counts[j][k];
                            if (started[j][k]) res = Math.max(res, counts[j][k]);
                            else if (i == n - 1) res = Math.max(res, counts[j][k]-1);
                        }
                    }
                } else {
                    --counts[j][curr];
                    if (counts[j][curr] < 0) {
                        counts[j][curr] = 0;
                        started[j][curr] = false;
                    } else {
                        started[j][curr] = true;
                        res = Math.max(res, counts[j][curr]);
                    }
                }
            }
        }

        return res;
    }

}