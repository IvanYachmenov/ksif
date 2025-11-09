package cv6;

public class CaesarCipher {

    static final int mod = 26;
    int shift;

    public CaesarCipher(int shift){
        this.shift = shift;
    }

    public String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();

        for(char letter : plainText.toCharArray()) {
            int toNum = letter - 'a';
            toNum += shift;
            toNum %= mod;
            char toChar = (char) (toNum + 'a');
            cipherText.append(toChar);
        }
        return cipherText.toString();
    }

    public String decrypt(String cypherText) {
        StringBuilder plainText = new StringBuilder();

        for(char letter : cypherText.toCharArray()) {
            int toNum = letter - 'a';
            toNum -= shift;
            toNum = (toNum + mod) % mod;
            char toChar = (char) (toNum + 'a');
            plainText.append(toChar);
        }
        return plainText.toString();
    }

}
