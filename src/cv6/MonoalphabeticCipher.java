package cv6;

import static cv3.InversionPermutation.inverse;
import static cv3.RandomPermutation.rndPerm;

public class MonoalphabeticCipher {

    Character[] key, inverseKey;

    public MonoalphabeticCipher(){
        rndKey();
    }

    public MonoalphabeticCipher(Character[] key){
        this.key = key;
        this.inverseKey = inverse(key);
    }

    public void rndKey() {
        Character[] alphabet = new Character[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char) ('a' + i);
        }
        rndPerm(alphabet);
        this.key = alphabet;
        this.inverseKey = inverse(alphabet);
    }

    public String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();

        for(char letter : plainText.toCharArray()) {
            cipherText.append(this.key[letter - 'a']);
        }
        return cipherText.toString();
    }

    public String decrypt(String cipherText) {
        StringBuilder plainText = new StringBuilder();

        for(char letter : cipherText.toCharArray()) {
            plainText.append(this.inverseKey[letter - 'a']);
        }
        return plainText.toString();
    }

}
