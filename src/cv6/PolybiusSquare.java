package cv6;

import java.security.InvalidParameterException;

public class PolybiusSquare {
    static char[][] matrix = {
        {'a', 'b', 'c', 'd', 'e'},
        {'f', 'g', 'h', 'i', 'k'},
        {'l', 'm', 'n', 'o', 'p'},
        {'q', 'r', 's', 't', 'u'},
        {'v', 'w', 'x', 'y', 'z'}
    };

    private int findPositionInMatrix(char letter) {
        int row = 0;
        int col = 0;
        boolean flag = false;
        for (row = 0; row < 5; row ++) {
            for(col = 0; col < 5; col ++) {
                if (matrix[row][col] == letter) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        if(flag) {
            row++;
            col++;
            return row * 10 + col;
        } else {
            throw new InvalidParameterException("invalid input!");
        }
    }

    private char getCharAtPosition(int pos) {
        int row = pos / 10;
        int col = pos % 10;
        row--;
        col--;
        return matrix[row][col];
    }

    public int[] encrypt(String plainText) {
        int[] cipherText = new int[plainText.length()];
        plainText = plainText.replace('j', 'i');
        int i = 0;

        for(char letter : plainText.toCharArray()) {
            cipherText[i++] = findPositionInMatrix(letter);
        }
        return cipherText;
    }

    public String decrypt(int[] cipherText) {
        StringBuilder plainText = new StringBuilder();

        for(int i : cipherText) {
            plainText.append(getCharAtPosition(i));
        }
        return plainText.toString();
    }
}
