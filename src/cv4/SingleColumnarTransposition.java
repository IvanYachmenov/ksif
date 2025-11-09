package cv4;

import java.util.ArrayList;
import java.util.List;

import static cv3.PermutationFromPhrase.permutationFromPhrase;

public class SingleColumnarTransposition {
    Integer[] key;

    private SingleColumnarTransposition(){}

    public SingleColumnarTransposition(Integer[] key){
        this.key = key;
    }

    public SingleColumnarTransposition(String key){
        this.key = permutationFromPhrase(key);
    }

    public String encrypt(String plainText) {
        int columns = key.length;
        int rows = plainText.length() / columns;
        if(plainText.length() % columns > 0) {
            rows++;
        }

        char[][] matrix = new char[rows][columns];
        int i = 0;
        int j = 0;
        for(char c : plainText.toCharArray()){
            matrix[i][j++] = c;
            if(j == columns){
                i++;
            }
            j %= columns;
        }

        List<Integer> permList = new ArrayList<>();
        for(int c = 0; c < columns; c++){
            permList.add(key[c]);
        }

        StringBuilder toReturn = new StringBuilder();
        for(int col = 0; col < columns; col++){
            int colIdx = permList.indexOf(col);
            for(int row = 0; row < rows; row++){
                if(matrix[row][colIdx] != '\u0000'){
                    toReturn.append(matrix[row][colIdx]);
                }
            }
        }

        return toReturn.toString();
    }

    public String decrypt(String cipherText) {
        int columns = key.length;
        int rows = cipherText.length() / columns;
        int nonCompleteCols = cipherText.length() % columns;
        if(nonCompleteCols > 0){
            rows++;
        }
        List<Integer> permList = new ArrayList<>();
        for(int pos = 0; pos < key.length; pos++){
            permList.add(key[pos]);
        }

        char[][] matrix = new char[rows][columns];
        int idx = 0;
        for(int col = 0; col < columns; col++){
            int colIdx = permList.indexOf(col);
            int maxRowIdx = rows;

            if(nonCompleteCols > 0 && (colIdx >= nonCompleteCols)){
                maxRowIdx--;
            }

            for(int row = 0; row < maxRowIdx; row++){
                matrix[row][colIdx] = cipherText.charAt(idx++);
            }
        }

        StringBuilder toReturn = new StringBuilder();
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){
                if(matrix[row][col] != '\u0000'){
                    toReturn.append(matrix[row][col]);
                }
            }
        }
        return toReturn.toString();
    }
}
