package solving.solve_1028;
//BOJ G4 2458 키 순서

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {
    static int N; //학생들의 수
    static int M;
    static int[][] dist;
    static int res;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N+1][N+1];
        res = 0;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) {
                    dist[i][j] = 0;
                }
                else{
                    dist[i][j] = INF;
                }
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(dist[a][b] == INF) {
                dist[a][b] = 1;
            }
            else {
                dist[a][b] += 1;
            }
        }
        floyd();

        for(int i=1; i<=N; i++) {
            int now = 0;
            for(int j=1; j<=N; j++) {
                if(dist[i][j] != INF) {
                    now++;
                }
            }
            for(int j=1; j<=N; j++) {
                if(dist[j][i] != INF) {
                    now++;
                }
            }

            if(now-1 == N) {
                res++;
            }
        }

        System.out.println(res);
    }

    private static void floyd() {
        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
