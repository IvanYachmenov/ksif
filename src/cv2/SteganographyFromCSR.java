package cv2;

import java.util.Random;

public class SteganographyFromCSR {

    public static final String TSA = " abcdefghijklmnopqrstuvwxyz";
    public static final Random rnd = new Random(System.currentTimeMillis());

    public static String writeEven(String plainText) {
        StringBuilder cipherText = new  StringBuilder();
        plainText = plainText.replaceAll(" ", "");
        int counter = 0;
        for(char x : plainText.toCharArray()) {
            counter++;
            char[] tmp = new char[3];
            tmp[0] = TSA.charAt(rnd.nextInt(TSA.length()));
            tmp[1] = x;
            tmp[2] = TSA.charAt(rnd.nextInt(TSA.length()));
            cipherText.append(tmp);
            if(counter % 2 == 0) {
                cipherText.append(" ");
            }
        }
        return cipherText.toString().trim();
    }

    public static String readEven(String cipherText) {
        StringBuilder plainText = new  StringBuilder();
        cipherText = cipherText.replaceAll(" ", "");
        for(int i = 0; i < cipherText.length(); i+=3) {
            plainText.append(cipherText.charAt(i+1));
        }
        return plainText.toString();
    }

    public static String writeOdd(String plainText) {
        StringBuilder cipherText = new  StringBuilder();
        String[] words = plainText.split(" ");
        int counter = 0;
        for(String word : words) {
            StringBuilder wrd = new StringBuilder(word);
            wrd.reverse();
            for(char x : wrd.toString().toCharArray()) {
                counter++;
                char[] tmp = new char[3];
                tmp[0] = TSA.charAt(rnd.nextInt(TSA.length()));
                tmp[1] = x;
                tmp[2] = TSA.charAt(rnd.nextInt(TSA.length()));
                cipherText.append(tmp);
                if(counter % 2 == 0) {
                    cipherText.append(" ");
                }
            }
            char div = TSA.charAt(rnd.nextInt(TSA.length()));
            cipherText.append(" ").append(div).append(" ");
        }
        return cipherText.toString().trim();
    }

    public static String readOdd(String cipherText) {
        cipherText = cipherText.replaceAll(" +"," ");
        StringBuilder plainText = new  StringBuilder();
        String[] divs = cipherText.split(" ");
        StringBuilder tmp = new StringBuilder();
        for(String d : divs) {
            if(d.length() % 3 == 0) {
                char x1 = d.charAt(1);
                tmp.append(x1);

                if(d.length() % 6 == 0) {
                    char x2 = d.charAt(4);
                    tmp.append(x2);
                }
            } else {
                plainText.append(tmp.reverse()).append(" ");
            }
        }
        return plainText.toString();
    }
}
