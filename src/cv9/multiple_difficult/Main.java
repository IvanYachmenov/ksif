package cv9.multiple_difficult;

public class Main {
    public static void main(String[] args) {
        DoubleColumnarTransposition dct = new DoubleColumnarTransposition("KEY1", "KEY2");
        String plaintext = "HELLO WORLD THIS IS A TEST";

        String encrypted = dct.encrypt(plaintext);
        String decrypted = dct.decrypt(encrypted);

        System.out.println("Original: " + plaintext);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Success: " + plaintext.equals(decrypted));
    }
}
