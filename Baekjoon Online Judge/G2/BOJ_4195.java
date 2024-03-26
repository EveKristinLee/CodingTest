package solving_2.solve_12.solve_19;
//BOJ G2 4195 친구 네트워크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_4195 {
    static Map<String, Integer> index;
    static int F;
    static int[] p;
    static int[] cnt;
    static int INF = 200001;

    private static void init() {
        p = new int[INF];
        cnt = new int[INF];
        for(int i=1; i<INF; i++) {
            p[i] = i;
            cnt[i] = 1;
        }
    }

    private static int getParent(int a) {
        if(p[a] == a) {
            return a;
        }
        return p[a] = getParent(p[a]);
    }

    private static boolean union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if(a == b) {
            return false;
        }

        cnt[a] += cnt[b];
        p[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            F = Integer.parseInt(br.readLine());
            int idx = 1;
            init();
            index = new HashMap<>();
            for(int i=0; i<F; i++) {
                String s = br.readLine();
                String[] rel = s.split(" ");
                for(int j=0; j<=1; j++) {
                    if(!index.containsKey(rel[j])) {
                        index.put(rel[j], idx++);
                    }
                }
                int a = index.get(rel[0]);
                int b = index.get(rel[1]);
                union(a, b);
                int now = Math.max(cnt[getParent(a)], cnt[getParent(b)]);
                sb.append(now).append("\n");
            }
        }
        System.out.println(sb);
    }
}
