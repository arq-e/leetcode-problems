class BestTimeToBuyAndSellStockThree {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] profit = new int[n];
        int maxPrice = prices[n-1];
        for (int i = n-2; i >= 0; --i) {
            if (prices[i] > maxPrice) maxPrice = prices[i];
            profit[i] = Math.max(profit[i+1],maxPrice - prices[i]);
        }

        int minPrice = prices[0];
        int[] profit2 = new int[n];

        for (int i = 1; i < n; ++i) {
            if (prices[i] < minPrice) minPrice = prices[i];
            profit2[i] = Math.max(profit2[i-1],prices[i] - minPrice);
        }
        int max = profit[0];
        int sellDay = n;
        int[] totalProfit = new int[n];
        for (int i = 0; i < n-1; ++i) {
            max = Math.max(profit[i+1] + profit2[i], max);

        }
        return max;
    }
}