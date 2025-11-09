package cv5;

import cv4.SingleColumnarTransposition;

import java.util.ArrayList;
import java.util.List;

public class Utility extends SingleColumnarTransposition {

    public Utility(Integer[] key) {
        super(key);
    }

    public Utility(String key) {
        super(key);
    }

    @Override
    public String encrypt(String plainText) {
        int cols = key.length;
        int rows = plainText.length() / cols;
        if(plainText.length() % cols > 0) {
            rows++;
        }

        char[][] matrix = new char[rows][cols];
        int i = 0, j = 0;
        for(char s : plainText.toCharArray()) {
            matrix[i][j++] = s;
            if(j == cols) {
                i++;
            }
            j %= cols;
        }

        StringBuilder toReturn = new StringBuilder();
        List<Integer> perms = new ArrayList<>();
        for(int col = 0; col < key.length; col++) {
            perms.add(key[col]);
        }

        for(int col = 0; col < key.length; col++) {
            int colIdx = perms.indexOf(col);
            for(int row = 0; row < rows; row ++) {
                if(matrix[row][colIdx] != '\u0000' && (row <= col)) {
                    toReturn.append(matrix[row][colIdx]);
                }
            }
        }

        for(int col = 0; col < cols; col++) {
            int colIdx = perms.indexOf(col);
            for(int row = 0; row < rows; row ++) {
                if(matrix[row][colIdx] != '\u0000' && (row > col)) {
                    toReturn.append(matrix[row][colIdx]);
                }
            }
        }
        return toReturn.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        int cols = key.length;
        int rows = cipherText.length() / cols;
        int nonComplete = cipherText.length() % cols;
        if(nonComplete > 0) {
            rows++;
        }

        List<Integer> perms = new ArrayList<>();
        for(int col = 0; col < key.length; col++) {
            perms.add(key[col]);
        }

        char[][] matrix = new char[rows][cols];
        if(nonComplete > 0) {
            for(int col = nonComplete; col < cols; col++) {
                matrix[rows-1][col] = '_';
            }
        }
        int idx = 0;

        for(int col = 0; col < cols; col++) {
            int colIdx = perms.indexOf(col);
            int maxRowIdx = Math.min(col + 1, rows);

            for(int row = 0; row < maxRowIdx && matrix[row][colIdx] != '_'; row++) {
                matrix[row][colIdx] = cipherText.charAt(idx++);
            }
        }

        for(int col = 0; col < cols; col++) {
            int colIdx = perms.indexOf(col);
            for(int row = col+1; row < rows && matrix[row][colIdx] != '_'; row++) {
                matrix[row][colIdx] = cipherText.charAt(idx++);
            }
        }

        StringBuilder toReturn = new StringBuilder();
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(matrix[row][col] != '\u0000') {
                    toReturn.append(matrix[row][col]);
                }
            }
        }
        return toReturn.toString();
    }
}
