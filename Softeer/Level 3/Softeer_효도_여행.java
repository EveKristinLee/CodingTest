import java.io.*;
import java.util.*;

public class Softeer_효도_여행 {
    private static class Node {
        int idx;
        char alpha;

        public Node(int idx, char alpha) {
            this.idx = idx;
            this.alpha = alpha;
        }
    }

    static int N; //정점의 개수
    static int M; //문자열의 길이
    static List<List<Node>> link;
    static String fav;
    static boolean[] visit;
    static int[][] dp;
    static int res;

    static final int MAX = 5002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fav = br.readLine();
        link = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            link.add(new ArrayList<>());
        }
        visit = new boolean[N+1];
        dp = new int[MAX][MAX];

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            char alpha = st.nextToken().charAt(0);
            link.get(s).add(new Node(e, alpha));
            link.get(e).add(new Node(s, alpha));
        }

        res = 0;
        dfs(1, 0);

        System.out.println(res);
    }

    private static void dfs(int cur, int depth) {
        visit[cur] = true;

        boolean isLeaf = true;
        for(int i=0; i<link.get(cur).size(); i++) {
            Node next = link.get(cur).get(i);
            if(!visit[next.idx]) {
                isLeaf = false;

                for(int j=0; j<M; j++) {
                    char c = fav.charAt(j);

                    dp[depth+1][j+1] = Math.max(dp[depth][j] + ((next.alpha == c) ? 1 : 0),
                            Math.max(dp[depth][j+1], dp[depth+1][j]));
                }
                dfs(next.idx, depth+1);
                visit[next.idx] = false;
            }
        }

        if(isLeaf) {
            for(int j=0; j<M; j++) {
                res = Math.max(res, dp[depth][j+1]);
            }
        }
    }
}
