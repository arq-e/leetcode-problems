class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] summArr = new int[nums1.length + nums2.length];
        if (summArr.length == 0) return 0;
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < summArr.length; ++i) {
            if (p1 >= nums1.length) {
                summArr[i] = nums2[p2];
                ++p2;
            } else if (p2 >= nums2.length) {
                summArr[i] = nums1[p1];
                ++p1;
            } else {
                if (nums1[p1] < nums2[p2]) {
                    summArr[i] = nums1[p1];
                    ++p1;
                } else {
                    summArr[i] = nums2[p2];
                    ++p2;
                }
            }
        }

        if (summArr.length % 2 == 1) {
            return summArr[summArr.length / 2];
        } else return (summArr[summArr.length / 2 - 1] + summArr[summArr.length / 2]) / 2.0;
    }
}