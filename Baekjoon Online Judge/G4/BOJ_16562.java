package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G4 16562 친구비
public class BOJ_16562 {
    static int N;
    static int M;
    static int K;
    static int[] p;
    static int[] money;
    static boolean[] visit;

    private static void init() {
        p = new int[N+1];
        for(int i=1; i<=N; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if(p[a] == a) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b){
        a = find(a);
        b = find(b);

        if(a==b){
            return false;
        }
        if(money[a] > money[b]){
            p[a] = b;
        }
        else{
            p[b] = a;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[N+1];
        money = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }
        init();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);

        }

        visit = new boolean[N+1];
        long pay = 0;
        for(int i=1; i<=N; i++) {
            int idx = find(i);
            if(!visit[idx]) {
                pay += money[idx];
                visit[idx] = true;
            }
        }
        if(pay > K) {
            System.out.println("Oh no");
        }
        else{
            System.out.println(pay);
        }
    }
}
