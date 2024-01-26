package homework;
//BOJ B2 8846 Wymiana żarówki

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8846 {
    static final int MOD = 500000009;
    static int A;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        res = 1;
        int now = 1;
        int before = 1;
        for(int i=1; i<A; i++) {
            now = before * 4 % MOD;
            res += now % MOD;
            before = now % MOD;
        }
        System.out.println(res % MOD);
    }
}
