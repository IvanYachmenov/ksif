package cv3;

import java.util.Set;

public class RecursionPermutation {
    public static void allPasswords(int depth, int maxDepth,
                                Character[] pwd, Set<Character[]> res) {
        if(depth == maxDepth) {
            res.add(pwd.clone());
        } else {
            for(int i = 0; i < 26; i++) {
                pwd[depth] = (char) (i + 'a');
                allPasswords(depth + 1, maxDepth, pwd, res);
            }
        }
    }
}
