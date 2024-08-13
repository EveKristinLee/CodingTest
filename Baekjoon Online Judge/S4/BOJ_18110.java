package solving;
//BOJ S4 18110 solved.ac

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_18110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> level = new ArrayList<>();
        for(int i=0; i<N; i++) {
            int now = Integer.parseInt(br.readLine());
            level.add(now);
        }
        Collections.sort(level);

        int delCnt = (int)Math.round((15 * N)/100.0);
        double sum = 0;
        int cnt = 0;
        for(int i=delCnt; i<N-delCnt; i++) {
            sum += level.get(i);
            cnt++;
        }
        int avg = (int)Math.round(sum / cnt);
        System.out.println(avg);
    }
}
