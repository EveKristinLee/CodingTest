package solving.solve_1028;
//BOJ G4 2295 세 수의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * x + y + z = k
 * x + y = k - z
 * z = k - (x + y)
 */
public class BOJ_2295 {
    static int N;
    static List<Integer> U;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        U = new ArrayList<>();
        for(int i=0; i<N; i++) {
            U.add(Integer.parseInt(br.readLine()));
        }
        set = new TreeSet<>();
        for(int i=0; i<N; i++) {
            for(int j=i; j<N; j++) {
                set.add(U.get(i) + U.get(j));
            }
        }
        Collections.sort(U);

        for(int idx = N-1; idx>=0; idx--) {
            for(Integer now : set) {
                int tmpZ = U.get(idx) - now;
                if(Collections.binarySearch(U, tmpZ) >= 0) {
                    System.out.println(U.get(idx));
                    return;
                }
            }
        }

    }
}
