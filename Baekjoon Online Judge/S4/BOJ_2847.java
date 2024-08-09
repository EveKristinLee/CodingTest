package solving;
//BOJ S4 2847 게임을 만든 동준이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] level = new int[N];
        int res = 0;
        for(int i=0; i<N; i++) {
            level[i] = Integer.parseInt(br.readLine());
        }

        for(int i=N-1; i>0; i--) {
            if(level[i] <= level[i-1]) {
                int nowTmp = Math.abs(level[i] - level[i-1]) + 1;
                level[i-1] -= nowTmp;
                res += nowTmp;
            }
        }
        System.out.println(res);
    }
}
