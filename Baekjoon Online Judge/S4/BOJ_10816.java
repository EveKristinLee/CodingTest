package solving;
//BOJ S4 10816 숫자 카드 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_10816 {
    static int N;
    static int M;
    static Map<Integer, Integer> card;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        card = new HashMap<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            int cnt = 1;
            if(card.containsKey(num)) {
                cnt = card.get(num) + 1;
            }
            card.put(num, cnt);
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            int now = Integer.parseInt(st.nextToken());
            if(card.containsKey(now)) {
                sb.append(card.get(now)).append(" ");
            }
            else {
                sb.append("0").append(" ");
            }
        }

        System.out.println(sb);
    }
}
