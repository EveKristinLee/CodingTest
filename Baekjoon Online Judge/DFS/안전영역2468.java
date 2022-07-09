import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class 안전영역2468 {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxHeight = 0;
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }
        int maxRegion = 0;
        for(int h=0; h<=maxHeight; h++) {
            visit = new boolean[N][N];
            int region = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visit[i][j] && map[i][j] > h) {
                        dfs(i, j, h);
                        region++;
                    }
                }
            }
            maxRegion = Math.max(maxRegion, region);
        }
        System.out.println(maxRegion);
    }
    static void dfs(int x, int y, int height) {
        visit[x][y] = true;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isInside(nx, ny)) {
                if(!visit[nx][ny] && map[nx][ny] > height) {
                    dfs(nx, ny, height);
                }
            }
        }
    }
    static boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
