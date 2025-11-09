package cv4;

import static cv3.PermutationFromPhrase.permutationFromPhrase;
import static cv3.InversionPermutation.inverse;


public class Transposition {
    Integer[] key;
    Integer[] inverseKey;

    private Transposition() {}

    public Transposition(Integer[] key) {
        this.key = key;
        this.inverseKey = inverse(key);
    }

    public Transposition(String key_) {
        this.key = permutationFromPhrase(key_);
    }

    public String encrypt(String plainText) {
        if(plainText.length() % key.length != 0) {
            int toFill = key.length - (plainText.length() % key.length);
            StringBuilder plainTextBuilder = new StringBuilder(plainText);
            for(int i = 0; i < toFill; i++) {
                plainTextBuilder.append('x');
            }
            plainText = plainTextBuilder.toString();
        }
        return crypt(plainText, key);
    }

    public String decrypt(String cipherText) {
        return crypt(cipherText, inverseKey);
    }


    public String crypt(String plainText, Integer[] key) {
        int block_len = key.length;
        int blocks = plainText.length() / block_len;
        StringBuilder cryptedText = new StringBuilder();
        for(int i = 0; i < blocks; i++) {
            int idx =  i * block_len;
            String substring = plainText.substring(idx, idx + block_len);
            String reordered = applyPermutaion(substring, key);
            cryptedText.append(reordered);
        }
        return cryptedText.toString();
    }

    public static String applyPermutaion(String substring, Integer[] perm) {
        char[] output = new char[perm.length];
        for(int i = 0; i < perm.length; i++) {
            output[perm[i]] =  substring.charAt(i);
        }
        return new String(output);
    }
}
