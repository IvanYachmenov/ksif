package cv3;

public class InversionPermutation {
    public static Integer[] inverse(Integer[] perm) {
        Integer[] inverse = new Integer[perm.length];
        for(int i = 0; i < perm.length; i++){
            inverse[perm[i]] = i;
        }
        return inverse;
    }
}
