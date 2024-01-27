package solving.solve_0925;
//BOJ G3 2879 코딩은 예쁘게

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2879 {
    static int N;
    static int[] arr;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        res = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - arr[i];
        }

        int prev = 0;
        if(N > 1) {
            prev = arr[0];
            for(int i=1; i<N; i++) {
                if(prev * arr[i] < 0) { //부호가 다르면
                    res += Math.abs(prev);
                }
                else if(Math.abs(prev) > Math.abs(arr[i])) {
                    res += Math.abs(prev) - Math.abs(arr[i]);
                }
                prev = arr[i];
            }
        }
        else {
            res = Math.abs(arr[0]);
        }

        res += Math.abs(prev);
        System.out.println(res);
    }
}
