package solving.solve_0930;
//BOJ S3 9375 패션왕 신해빈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_9375 {
    static int N;
    static Map<String, Integer> cloth;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            cloth = new HashMap<>();
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                if(cloth.containsKey(type)) {
                    cloth.put(type, cloth.get(type)+1);
                }
                else {
                    cloth.put(type, 1);
                }
            }

            int res = 1;
            for(Integer cnt : cloth.values()) {
                res *= (cnt + 1);
            }
            sb.append(res-1).append("\n");
        }
        System.out.print(sb);
    }
}
