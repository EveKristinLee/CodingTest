package solving.solve_1005;
//BOJ G1 외판원 순회
//외판원 순회 = 해밀턴 순환 + 최저비용 조건

//사용 가능 알고리즘
//n≤11 일 때 : 완전탐색
//n≤12 일 때 : 백트래킹
//n≤16 일 때 : 비트필드를 이용한 DP

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2098 {
    static final int INF = 987654321;
    static int END;
    static int N;
    static int[][] W;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][(1<<N)];
        END = (1<<N) -1; //모두 방문

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(TSP(0, 1));
    }

    private static int TSP(int now, int visit) {
        if(visit == END) { //모두 방문
            if(W[now][0] > 0) { //시작점으로 돌아가는 길이 있다면
               return W[now][0];
            }
            return INF;
        }

        //현재 idx를 이미 계산했다면
        if(dp[now][visit] != 0)
            return dp[now][visit];

        dp[now][visit] = INF; //계산 안했으면 초기화

        for(int i=0; i<N; i++) {
            if(W[now][i] == 0) { //현 위치에서 i노드로 갈 수 있는 방법 X
                continue;
            }
            if(((1 << i) & visit) != 0) { //i 노드를 이미 방문했을 경우
                continue;
            }

            int next = (1<<i) | visit;
            dp[now][visit] = Math.min(dp[now][visit], TSP(i, next) + W[now][i]);
        }

        return dp[now][visit]; //최소 비용 반환
    }
}
