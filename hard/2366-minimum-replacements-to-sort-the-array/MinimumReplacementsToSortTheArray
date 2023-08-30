class Solution {
    public long minimumReplacement(int[] nums) {
        long res = 0;
        for (int i = nums.length-2; i >= 0; --i) {
            if (nums[i] > nums[i+1]) {
                int div = nums[i]/nums[i+1];
                if (nums[i] % nums[i+1] == 0) {
                    res += div - 1;
                    nums[i] = nums[i+1];
                } else {
                    res += div;
                    nums[i] /= (div + 1);
                }
            }
        }
        return res;
    }
}