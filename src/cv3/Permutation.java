package cv3;

import java.util.*;

import static cv2.SteganographyFromCSR.rnd;

public class Permutation {
    static List<Integer> genPermutation() {
        Set<Integer> s = new HashSet<>();
        do{
            int i = rnd.nextInt(26);
            s.add(i);
            if(s.size() == 10) {
                break;
            }
        } while (true);
        List<Integer> l = new ArrayList<>(s);
        System.out.println(l);
        Collections.shuffle(l);
        System.out.println(l);
        return l;
    }
}
