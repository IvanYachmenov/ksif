package cv3;


import static cv2.SteganographyFromCSR.rnd;

public class RandomPermutation {
    public static void rndPerm(Object input[]) {
        int size = input.length;
        for(int i = 0; i < size - 1; i++) {
            int j = rnd.nextInt(size - i) + i;
            Object tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
    }
}
