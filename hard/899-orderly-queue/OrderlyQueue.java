import java.util.Arrays;

class OrderlyQueue {
    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            char[] sMin = new char[s.length()];
            for (int i = 0; i < s.length(); i++) {
                sMin[i] = s.charAt(i);
            }
            Arrays.sort(sMin);
            StringBuilder sbl = new StringBuilder();
            sbl.append(sMin);
            return sbl.toString();
        } else {
            String letters = s + s;
            int minPointer = 0;
            for (int i = 0; i < s.length(); i++) {
                if (letters.charAt(i) == letters.charAt(minPointer)) {
                    for (int j = 1; j < s.length(); j++) {
                        if (letters.charAt(minPointer+j) < letters.charAt(i+j)) {
                            break;
                        } else if (letters.charAt(minPointer+j) > letters.charAt(i+j)) {
                            minPointer = i;
                            break;
                        }
                    }
                } else if (letters.charAt(i) < letters.charAt(minPointer)) {
                    minPointer = i;
                }
            }
            return letters.substring(minPointer, minPointer+s.length());
        }
    }
}