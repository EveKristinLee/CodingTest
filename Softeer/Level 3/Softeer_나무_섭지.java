import java.io.*;
import java.util.*;

public class Softeer_나무_섭지 {
    private static class Pos {
        int x;
        int y;
        boolean isPerson;
        public Pos(int x, int y, boolean isPerson) {
            this.x = x;
            this.y = y;
            this.isPerson = isPerson;
        }
    }
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visitN;
    static boolean[][] visitG;
    static Queue<Pos> q;
    static List<Pos> ghost;
    static Pos dest;
    static Pos namwoo;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=n || y>=m) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visitN = new boolean[n][m];
        visitG = new boolean[n][m];
        ghost = new ArrayList<>();
        q = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            String now = br.readLine();
            map[i] = now.toCharArray();
            for(int j=0; j<m; j++) {
                if(map[i][j] == 'N') {
                    namwoo = new Pos(i, j, true);
                    visitN[i][j] = true;
                }
                else if(map[i][j] == 'G') {
                    ghost.add(new Pos(i, j, false));
                    visitG[i][j] = true;
                }
                else if(map[i][j] == 'D') {
                    dest = new Pos(i, j, true);
                }
            }
        }
        q.offer(namwoo);
        for(int i=0; i<ghost.size(); i++) {
            q.offer(ghost.get(i));
        }


        if(bfs()) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }

    private static boolean bfs() {
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i=0; i<qSize; i++) {
                Pos now = q.poll();
                if(now.x == dest.x && now.y == dest.y) {
                    if(now.isPerson && map[now.x][now.y] == 'N') {
                        return true;
                    }
                    else {
                        return false;
                    }
                }

                for(int d=0; d<4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if(!isInside(nx, ny)) {
                        continue;
                    }

                    if(now.isPerson) {
                        if(visitN[nx][ny]) {
                            continue;
                        }

                        if(map[nx][ny] != '#' && map[nx][ny] != 'G') {
                            visitN[nx][ny] = true;
                            map[nx][ny] = 'N';
                            q.offer(new Pos(nx, ny, true));
                        }
                    }

                    else if(!now.isPerson) {
                        if(visitG[nx][ny]) {
                            continue;
                        }

                        visitG[nx][ny] = true;
                        map[nx][ny] = 'G';
                        q.offer(new Pos(nx, ny, false));
                    }
                }
            }
        }
        return false;
    }
}
