class MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        int total = t.length();
        int[] counts = new int[60];
        for (int i = 0; i < t.length(); ++i) {
            ++counts[t.charAt(i)-'A'];
        }
        for (int i = 0; i < 60; ++i) {
            if (counts[i] == 0) counts[i] = Integer.MIN_VALUE;
        }
        String res = "";
        int start = 0;
        int end = -1;
        int counted = 0;
        while (end < s.length()-1 || counted == total) {
            if (counted < total) {
                ++end;
                if (counts[s.charAt(end)-'A'] != Integer.MIN_VALUE) {
                    if (counts[s.charAt(end)-'A']-- > 0) {
                        ++counted;
                    }
                }
            } else if (counted == total) {
                if (counts[s.charAt(start)-'A'] != Integer.MIN_VALUE) {
                    if (counts[s.charAt(start)-'A']++ >= 0) {
                        if (end+1-start < res.length() || res.equals("")) res = s.substring(start,end+1);
                        --counted;
                    }
                }
                ++start;
            }
        }
        return res;
    }
}