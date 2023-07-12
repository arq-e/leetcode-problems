class SplitMessageBasedOnLimit {
    public String[] splitMessage(String message, int limit) {
        if (limit < 6) return new String[0];
        int length = message.length();
        int minSuffixLength = 5;
        int maxSuffixLength = 5;
        int maxParts = 9;
        int parts = 0;
        int part = 0;
        if (9*(limit-5) >= length) {
            parts = length / (limit - minSuffixLength);
            part = (limit - minSuffixLength);
            if (length % (limit - minSuffixLength) > 0) parts++;
            return parts9(message, parts,parts, part, 0);
        } else if (9*(limit-6) + 90*(limit-7) >= length) {
            minSuffixLength = 6;
            maxSuffixLength = 7;
            part = (limit - minSuffixLength);
            int maxPart = (limit - maxSuffixLength);
            parts = 9 + (length - part*9) / maxPart;
            if ((length - part*9) % maxPart > 0) parts++;
            String[] res = new String[parts];
            String[] res9 = parts9(message, 9,parts, part, 0);
            for (int i = 0; i < 9; i++) {
                res[i] = res9[i];
            }
            message = message.substring(part*9);
            String[] res90 = parts9(message, parts-9,parts,maxPart, 1);
            for (int i = 0; i < res90.length; i++) {
                res[i+9] = res90[i];
            }
            return res;
        } else if (9*(limit-7) + 90*(limit-8) + 900*(limit-9) >= length) {
            minSuffixLength = 7;
            maxSuffixLength = 9;
            part = (limit - minSuffixLength);
            int maxPart = (limit - maxSuffixLength);
            parts = 9 + 90 + (length - part*9 - (part-1)*90) / maxPart;
            if ((length - part*9 - (part-1)*90) % maxPart > 0) parts++;
            String[] res = new String[parts];
            String[] res9 = parts9(message, 9,parts, part, 0);
            for (int i = 0; i < 9; i++) {
                res[i] = res9[i];
            }
            String[] res90 = parts9(message.substring(part*9), 90,parts,part-1, 1);
            for (int i = 0; i < 90; i++) {
                res[i+9] = res90[i];
            }
            String[] res900 = parts9(message.substring(part*9+(part-1)*90), parts-99,parts,part-2, 2);
            for (int i = 0; i < res900.length; i++) {
                res[i+99] = res900[i];
            }
            return res;
        } else if (9*(limit-8) + 90*(limit-9) + 900*(limit-10) + 9000*(limit-11) >= length) {
            minSuffixLength = 8;
            maxSuffixLength = 11;
            part = (limit - minSuffixLength);
            int maxPart = (limit - maxSuffixLength);
            parts = 9 + 90+900 + (length - part*9 - (part-1)*90- (part-2)*900) / maxPart;
            if ((length - part*9 - (part-1)*90- (part-2)*900) % maxPart > 0) parts++;
            String[] res = new String[parts];
            String[] res9 = parts9(message, 9,parts, part, 0);
            for (int i = 0; i < 9; i++) {
                res[i] = res9[i];
            }
            String[] res90 = parts9(message.substring(part*9), 90,parts,part-1, 1);
            for (int i = 0; i < 90; i++) {
                res[i+9] = res90[i];
            }
            String[] res900 = parts9(message.substring(part*9+(part-1)*90), 900,parts,part-2, 2);
            for (int i = 0; i < 900; i++) {
                res[i+99] = res900[i];
            }
            String[] res9000 = parts9(message.substring(part*9+(part-1)*90 + (part-2)*900), parts-999,parts,part-3, 3);
            for (int i = 0; i < res9000.length; i++) {
                res[i+999] = res9000[i];
            }
            return res;
        } else return new String[0];

    }

    public String[] parts9(String message, int parts,int totalParts,  int size, int x) {
        String[] res = new String[parts];
        for(int i = 1; i <= res.length; i++) {
            int l = i -1 + (int)Math.pow(10, x);
            res[i-1] = message.substring((i-1)*(size), Math.min(message.length(), i*size)) + "<" + l + "/" + totalParts + ">";
        }
        return res;
    }

}