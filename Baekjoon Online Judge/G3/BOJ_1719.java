package solving.solve_0925;

//BOJ G3 1719 택배

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1719 {
    static int N;
    static int M;
    static int[][] dist;
    static int[][] road;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N+1][N+1];
        road = new int[N+1][N+1];
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
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = cost;
            dist[end][start] = cost;
            road[start][end] = end;
            road[end][start] = start;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(dist[i][j] > (dist[i][k] + dist[k][j])) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        road[i][j] = road[i][k];
                    }
                }
            }
        }
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N;j++) {
                if(road[i][j] == 0) {
                    System.out.print("- ");
                }
                else{
                    System.out.print(road[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
