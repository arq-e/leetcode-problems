class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> dist = new ArrayList<>();
        dist.add(new ArrayList<>());
        int cWidth = 0;
        int row = 0;
        for (String word : words) {
            if (cWidth + word.length() <= maxWidth) {
                dist.get(row).add(word);
                cWidth += (word.length() + 1);
            } else {
                ++row;
                dist.add(new ArrayList<>());
                dist.get(row).add(word);
                cWidth = word.length() + 1;
            }
        }

        List<String> res = new ArrayList<>();
        for (int j = 0; j < dist.size(); ++j) {
            List<String> list = dist.get(j);
            int spaces = maxWidth;
            for (String word : list) {
                spaces -= word.length();
            }
            StringBuilder sb = new StringBuilder(list.get(0));

            if (j != dist.size() - 1) {
                int spacesCount = list.size() - 1;
                for (int i = 1; i < list.size(); ++i) {
                    int cSpaces = spaces % spacesCount == 0 ? spaces / spacesCount : spaces / spacesCount + 1;
                    --spacesCount;
                    spaces -= cSpaces;
                    sb.append(" ".repeat(cSpaces));
                    sb.append(list.get(i));
                }
                if (spaces > 0) sb.append(" ".repeat(spaces));
            } else {
                for (int i = 1 ; i < list.size(); ++i) {
                    sb.append(" ");
                    sb.append(list.get(i));
                    --spaces;
                }
                sb.append(" ".repeat(spaces));
            }
            res.add(sb.toString());
        }
        return res;
    }
}