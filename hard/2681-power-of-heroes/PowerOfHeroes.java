import java.util.Arrays;

class PowerOfHeroes {
    public int sumOfPower(int[] nums) {
        int mod = 1000000007;
        Arrays.sort(nums);
        long lowerSum = 0;
        long sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            long pow = (long)nums[i]*nums[i] % mod;
            sum = (sum + pow*nums[i]%mod + pow*lowerSum%mod)%mod;
            lowerSum = (lowerSum*2+nums[i])%mod;
        }
        return (int)sum;
    }
}