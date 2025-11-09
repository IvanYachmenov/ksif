package cv3;

import java.util.Arrays;

public class PermutationFromPhrase {
    public static Integer[] permutationFromPhrase(String phrase) {
        phrase = phrase.toLowerCase();
        Integer[] retVal = new Integer[phrase.length()];
        char[] sorted = phrase.toCharArray();
        Arrays.sort(sorted);
        int len = phrase.length();

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(phrase.charAt(i) == sorted[j]){
                    retVal[j] = i;
                    sorted[j] = '_';
                    break;
                }
            }
        }
        return retVal;
    }
}
