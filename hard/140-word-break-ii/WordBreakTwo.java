import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class WordBreakTwo {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        set.addAll(wordDict);
        for (int j = s.length(); j > 0; j--) {
            if (set.contains(s.substring(0,j))) {
                res.add(s.substring(0,j));
            }
        }
        int step = 0;
        int finished = 0;
        while (true) {
            int count = res.size();
            for (int i = finished; i < count; i++) {
                int start = res.get(finished).length() - step;
                if (start >= s.length()) {
                    finished++;
                } else {
                    for (int j = s.length(); j > start; j--) {
                        if (set.contains(s.substring(start,j))) {
                            res.add(res.get(finished) + " " + s.substring(start,j));
                        }
                    }
                    res.remove(finished);
                }
            }
            if (count == finished) break;
            step++;
        }
        return res;
    }
}