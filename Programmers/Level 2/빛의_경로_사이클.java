import java.util.*;
public class 빛의_경로_사이클 {
    static int N; //가로
    static int M; //세로
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; //상, 우, 하, 좌
    static boolean[][][] visit;

    public static int[] solution(String[] grid) {
        List<Integer> ans = new ArrayList<>();
        N = grid.length;
        M = grid[0].length();
        visit = new boolean[N][M][4];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int k=0; k<4; k++) {
                    if(!visit[i][j][k]) {
                        ans.add(go(grid, i, j, k));
                    }
                }
            }
        }
        Collections.sort(ans);
        return ans.stream().mapToInt(i->i).toArray();
    }

    private static int go(String[] grid, int x, int y, int d) {
        int cnt = 0;

        while(true) {
            if(visit[x][y][d]) {
                break;
            }
            visit[x][y][d] = true;
            cnt++;

            if(grid[x].charAt(y) == 'L') {
                d = (d+3) % 4;
            }
            else if(grid[x].charAt(y) == 'R') {
                d = (d+1) % 4;
            }

            x = (x + dx[d] + N) % N;
            y = (y + dy[d] + M) % M;
        }
        return cnt;
    }

    public static void main(String[] args) {
        String[] grid = {"SL","LR"};
        int[] res = solution(grid);
        for(int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
