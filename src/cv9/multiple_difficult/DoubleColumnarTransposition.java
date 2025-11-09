package cv9.multiple_difficult;

import cv4.SingleColumnarTransposition;

public class DoubleColumnarTransposition {

    SingleColumnarTransposition sct1;
    SingleColumnarTransposition sct2;

    // from array of integers
    public DoubleColumnarTransposition(Integer[] key1, Integer[] key2) {
        sct1 = new SingleColumnarTransposition(key1);
        sct2 = new SingleColumnarTransposition(key2);
    }

    // from phrase
    public DoubleColumnarTransposition(String key1, String key2) {
        sct1 = new SingleColumnarTransposition(key1);
        sct2 = new SingleColumnarTransposition(key2);
    }

    public String encrypt(String plainText) {
        String encryptedText;
        encryptedText = sct1.encrypt(plainText);
        encryptedText = sct2.encrypt(encryptedText);
        return encryptedText;
    }

    public String decrypt(String cipherText) {
        String decryptedText;
        decryptedText = sct2.decrypt(cipherText);
        decryptedText = sct1.decrypt(decryptedText);
        return decryptedText;
    }

}
