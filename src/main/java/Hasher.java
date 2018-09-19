public class Hasher {

    public static int getHashNumberFromString(String name, int maxInt) {
        try {
            char[] chars = name.toCharArray();
            long hash = name.length();

            for (int i = 0; i < chars.length; i++) {
                int weight = getWeight(chars[i]);
                hash = addWeight(hash, weight, i);
            }

            hash /= (getWeight(chars[0]) + getWeight(chars[chars.length - 1]));
            int index = getIndex(hash, maxInt);

            return index;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
//        Random r = new Random(iconArray.length-1);
//        return iconArray[r.nextInt()];
    }

    private static long addWeight(long hash, int weight, int i) {
        if (hash > Integer.MAX_VALUE) {
            return hash / (weight + i);
        } else {
            return hash * (weight + i);
        }
    }

    private static int getWeight(char c) {
        int weight;
        if (Character.isUpperCase(c)) {
            weight = 3;
        } else if (Character.isLowerCase(c)) {
            weight = 5;
        } else if (Character.isDigit(c) && isEvenNumber(c)) {
            weight = 2;
        } else if (Character.isDigit(c) && !isEvenNumber(c)) {
            weight = 7;
        } else {
            weight = 11;
        }
        return weight;
    }

    private static boolean isEvenNumber(char c) {
        return c == 0 || c % 2 == 0;
    }

    private static int getIndex(long weight, int maxInt) {
        long index = weight;
        while (index >= maxInt) {
            index = index / 10;
        }
        return (int) index;
    }
}
