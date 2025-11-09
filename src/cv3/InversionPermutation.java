package cv3;

public class InversionPermutation {
    // for integer permutation
    public static Integer[] inverse(Integer[] perm) {
        Integer[] inverse = new Integer[perm.length];
        for(int i = 0; i < perm.length; i++){
            inverse[perm[i]] = i;
        }
        return inverse;
    }

    // for character permutation
    public static Character[] inverse(Character[] perm) {
        Character[] inverse = new Character[perm.length];
        for(int i = 0; i < perm.length; i++){
            inverse[perm[i] - 'a'] = (char)('a' + i);
        }
        return inverse;
    }
}
