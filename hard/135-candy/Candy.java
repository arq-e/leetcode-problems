class Solution {
    public int candy(int[] ratings) {
        if (ratings.length == 1) return 1;
        int[] candies = new int[ratings.length];
        int num = 1;
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i-1]) {
                ++num;  
            } else {
                num = 1;
            }
            candies[i] = num;
        }
        num = 1;
        int res = 0;
        for (int i = ratings.length-2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1]) {
                ++num;
            } else {
                num = 1;
            }
            candies[i] = Math.max(candies[i], num);
            res += candies[i];
        }
        return res + candies[ratings.length-1];
    }
}
