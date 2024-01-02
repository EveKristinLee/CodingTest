package solving.solve_0925;
//BOJ G3 9466 텀 프로젝트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466 {
    static int n; //학생 수
    static int[] link;
    static int[] p;
    static int res;

    private static void init() {
        p = new int[n+1];
        for(int i=1; i<=n; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if(p[a] == a) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) {
            return false;
        }
        p[a] = b;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());
            res = n;
            link = new int[n+1];
            init();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) {
                link[i] = Integer.parseInt(st.nextToken());
                if(!union(i, link[i])) { //사이클 발생
                    int start = i;
                    int now = i;
                    int parent = link[i];
                    int cnt = 1;
                    while(parent != start) {
                        now = parent;
                        parent = link[now];
                        cnt++;
                    }
                    res -= cnt;
                }
            }
            System.out.println(res);
        }
    }
}
