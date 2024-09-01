//BOJ B3 10833 사과

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10833 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] student = new int[N];
        int[] apple = new int[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            student[i] = Integer.parseInt(st.nextToken());
            apple[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        for(int i=0; i<N; i++) {
            int idx = (apple[i] / student[i]);
            res += (apple[i] - (student[i] * idx));
        }

        System.out.println(res);
    }
}
