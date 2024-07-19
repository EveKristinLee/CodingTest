package solving;
//BOJ S4 17219 비밀번호 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_17219 {
    static int N;
    static int M;
    static Map<String, String> memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memo = new HashMap<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String pw = st.nextToken();
            memo.put(site, pw);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            String site = br.readLine();
            String pw = memo.get(site);
            sb.append(pw).append("\n");
        }
        System.out.println(sb);
    }
}
