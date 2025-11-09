package cv7;

public class VigenereCipher {
    static final int mod = 26;
    String key;

    public VigenereCipher(String key){
        this.key = key;
    }

    public String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();

        for(int i = 0; i < plainText.length(); i++){
            int toNum = plainText.charAt(i) - 'a';
            toNum += key.charAt(i % key.length()) - 'a';
            toNum %= mod;
            char toChar = (char) (toNum + 'a');
            cipherText.append(toChar);
        }
        return cipherText.toString();
    }

    public String decrypt(String cypherText) {
        StringBuilder plainText = new StringBuilder();

        for(int i = 0; i < cypherText.length(); i++){
            int toNum = cypherText.charAt(i) - 'a';
            toNum -= key.charAt(i % key.length()) - 'a';
            toNum %= mod;
            char toChar = (char) (toNum + 'a');
            plainText.append(toChar);
        }
        return plainText.toString();
    }

}
