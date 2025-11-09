package cv9.multiple_difficult;

import cv4.SingleColumnarTransposition;
import cv6.MonoalphabeticCipher;
import cv7.VigenereCipher;

public class STPCipher {
    MonoalphabeticCipher mas;
    SingleColumnarTransposition sct;
    VigenereCipher vc;

    private STPCipher() {}

    public STPCipher(MonoalphabeticCipher mas,
                     SingleColumnarTransposition sct,
                     VigenereCipher vc){
        this.mas = mas;
        this.sct = sct;
        this.vc = vc;
    }

    public STPCipher(Character[] masKey,
                     String sctKey,
                     String vcKey){
        this.mas = new MonoalphabeticCipher(masKey);
        this.sct = new SingleColumnarTransposition(sctKey);
        this.vc = new VigenereCipher(vcKey);
    }

    public STPCipher(Character[] masKey,
                     Integer[] sctKey,
                     String vcKey){
        this.mas = new MonoalphabeticCipher(masKey);
        this.sct = new SingleColumnarTransposition(sctKey);
        this.vc = new VigenereCipher(vcKey);
    }

    public String encrypt(String plainText){
        String encryptedText;
        encryptedText = mas.encrypt(plainText);
        encryptedText = sct.encrypt(encryptedText);
        encryptedText = vc.encrypt(encryptedText);
        return encryptedText;
    }

    public String decrypt(String cipherText){
        String decryptedText;
        decryptedText = vc.decrypt(cipherText);
        decryptedText = sct.decrypt(decryptedText);
        decryptedText = mas.decrypt(decryptedText);
        return decryptedText;
    }
}
