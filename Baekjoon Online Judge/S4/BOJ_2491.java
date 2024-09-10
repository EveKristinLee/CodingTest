package solving;
//BOJ S4 2491 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2491 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N+1];
        int[] increase = new int[N+1];
        int[] decrease = new int[N+1];
        for(int i=1; i<=N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        increase[1] = 1;
        decrease[1] = 1;
        int res = 1;
        for(int i=2; i<=N; i++) {
            if(num[i-1] <= num[i]) {
                increase[i] = increase[i-1] + 1;
            }
            else {
                increase[i] = 1;
            }

            if(num[i-1] >= num[i]) {
                decrease[i] = decrease[i-1] + 1;
            }
            else {
                decrease[i] = 1;
            }

            res = Math.max(res, Math.max(increase[i], decrease[i]));
        }
        System.out.println(res);
    }
}
