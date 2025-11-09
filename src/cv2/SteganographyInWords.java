package cv2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SteganographyInWords {

    static final Random rnd = new Random(System.currentTimeMillis());

    Map<Character, List<String>> dict;

    public SteganographyInWords(String dictPath) throws IOException {
        List<String> words = Files.readAllLines(Paths.get(dictPath));
        dict = new HashMap<>();
        for(String wrd : words) {
            Character fst = wrd.charAt(0);
            if(!dict.containsKey(fst)) {
                dict.put(fst, new ArrayList<>());
            }
            dict.get(fst).add(wrd);
        }
    }

    public String write(String plainText) {
        StringBuilder cipherText = new StringBuilder();
        for(char x : plainText.toCharArray()) {
            if(dict.containsKey(x)) {
                List<String> words = dict.get(x);
                String rndWord = words.get(rnd.nextInt(words.size()));
                cipherText.append(rndWord).append(" ");
            }
        }
        return cipherText.toString().trim();
    }

    public String read(String cipherText) {
        StringBuilder plainText = new StringBuilder();
        String[] words = cipherText.split(" ");
        for(String wrd : words) {
            plainText.append(wrd.charAt(0));
        }
        return plainText.toString();
    }
}
