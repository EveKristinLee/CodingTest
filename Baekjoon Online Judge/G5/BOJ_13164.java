package solving.solve_0914;
//BOJ G5 13164 행복 유치원

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13164 {
    static int N;
    static int K;
    static List<Integer> height;
    static List<Integer> diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        height = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            height.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(height);

        diff = new ArrayList<>();
        for(int i=0; i<N-1; i++) {
            int nowDiff = Math.abs(height.get(i) - height.get(i+1));
            diff.add(nowDiff);
        }
        Collections.sort(diff);

        int sum = 0;
        for(int i=0; i<N-K; i++) {
            sum += diff.get(i);
        }
        System.out.println(sum);
    }
}
