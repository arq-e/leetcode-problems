class Solution {
    public int countOrders(int n) {
        int div = 1000000007;
        long res = 1;
        for (int i = 1; i <= 2*n; ++i) {
            res *= i;
            if (i % 2 == 0) res /= 2;
            res %= div;
        }
        return (int)res;
    }
}