package cv8;

public class VigenerePT {

    String key;

    private VigenerePT(){}

    public VigenerePT(String key){
        this.key = key;
    }

    public String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();
        for(int i = 0; i < plainText.length(); i++){
            char k;
            if(i < key.length()) {
                k = key.charAt(i);
            } else {
                k = plainText.charAt(i - key.length());
            }
            int toNum = plainText.charAt(i) - 'a';
            toNum += k - 'a';
            toNum %= 26;
            char toChar = (char) (toNum + 'a');
            cipherText.append(toChar);
        }
        return cipherText.toString();
    }

    public String decrypt(String cypherText) {
        StringBuilder plainText = new StringBuilder();
        for(int i = 0; i < cypherText.length(); i++){
            char k;
            if(i < key.length()) {
                k = key.charAt(i);
            } else {
                k = plainText.charAt(i - key.length());
            }
            int toNum = cypherText.charAt(i) - 'a';
            toNum -= k - 'a';
            toNum = (toNum + 26) % 26;
            char toChar = (char) (toNum + 'a');
            plainText.append(toChar);
        }
        return plainText.toString();
    }

}
