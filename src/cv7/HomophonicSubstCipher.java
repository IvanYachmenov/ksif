package cv7;

import java.util.*;

public class HomophonicSubstCipher {

    int homoCount = 5;
    Map<Character, List<Integer>> encKey;
    Map<Integer, Character> decKey;

    private static final Random rnd = new Random(System.currentTimeMillis());

    public HomophonicSubstCipher() {
        randomKey();
    }

    public void setEncKey(Map<Character, List<Integer>> encKey) {
        this.encKey = encKey;
    }

    public void setDecKey(Map<Integer, Character> decKey) {
        this.decKey = decKey;
    }

    public void randomKey() {
        this.encKey = new HashMap<>();
        this.decKey = new HashMap<>();
        List<Integer> numbers = new ArrayList<>();

        for(int i = 0; i < (26 * this.homoCount); i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        int idx = 0;

        for(char c = 'a'; c <= 'z'; c++) {
            List<Integer> homophones = new ArrayList<>();
            for(int j = 0; j < this.homoCount; j++) {
                int count = numbers.get(idx);
                homophones.add(count);
                decKey.put(count,c);
                idx++;
            }
            encKey.put(c,homophones);
        }
    }

    public Integer[] encrypt(String plainText) {
        Integer[] cipherText = new Integer[plainText.length()];
        int pos = 0;
        for(char letter : plainText.toCharArray()) {
            List<Integer> homophones = this.encKey.get(letter);
            if(homophones != null && !homophones.isEmpty()) {
                int idx = rnd.nextInt(homophones.size());
                cipherText[pos++] = homophones.get(idx);
            } else {
                cipherText[pos++] = -1;
            }
        }
        return cipherText;
    }

    public String decrypt(Integer[] cipherText) {
        StringBuilder plainText = new StringBuilder();

        for(int num : cipherText) {
            if(this.decKey.containsKey(num)) {
                plainText.append(this.decKey.get(num));
            } else {
                plainText.append('?');
            }
        }
        return plainText.toString();
    }


    public HomophonicSubstCipher(String text) {
        this.encKey = new HashMap<>();
        this.decKey = new HashMap<>();
        for(int i = 0; i < text.length(); i++) {
            int num = (i + 1);
            char ch = text.charAt(i);
            if(!encKey.containsKey(ch)) {
                encKey.put(ch, new ArrayList<>());
            }
            List<Integer> homophones = encKey.get(ch);
            homophones.add(num);
        }
    }

}
