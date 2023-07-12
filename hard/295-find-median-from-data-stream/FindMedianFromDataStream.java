
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class MedianFinder {

    List<Integer> nums;
    double prevMedian;
    int change;

    public MedianFinder() {
        nums = new ArrayList<Integer>();
        change = 0;
        prevMedian = 0;
    }

    public void addNum(int num) {
        int i;
        if (nums.size() > 0) {
            i = Collections.binarySearch(nums,num);
            if (i < 0) {
                i = Math.abs(i+1);
            }
            nums.add(i, num);
        } else {
            nums.add(num);
        }
        change = 1;

    }

    public double findMedian() {
        if (change == 0) return prevMedian;
        else {
            change = 0;
        int med = nums.size() / 2;
        if (nums.size() % 2 == 0) {
            prevMedian = (double) (nums.get(med) + nums.get(med-1))/2;
            return prevMedian;
        } else {
            prevMedian = nums.get(med);
            return prevMedian;
        }
        }

    }
}