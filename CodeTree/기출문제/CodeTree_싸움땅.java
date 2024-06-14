package 기출문제;
//CodeTree 싸움땅

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree_싸움땅 {
    private static class Gun implements Comparable<Gun>{
        int x;
        int y;
        int power;

        public Gun(int x, int y, int power) {
            this.x = x;
            this.y = y;
            this.power = power;
        }

        public int compareTo(Gun g) {
            return g.power - this.power;
        }
    }
    private static class Player {
        int idx;
        int x;
        int y;
        int power;
        int dir;

        public Player(int idx, int x, int y, int power, int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.power = power;
            this.dir = dir;
        }
    }

    static int N; //격자의 수
    static int M; //플레이어의 수
    static int K; //라운드의 수
    static PriorityQueue<Gun>[][] gunMap;
    static Player[][] playerMap;
    static List<Player> player;
    static int[] haveGun; //가진 총의 공격력
    static int[] point;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; //상, 우, 하, 좌

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>N || y>N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gunMap = new PriorityQueue[N+1][N+1];
        playerMap = new Player[N+1][N+1];
        player = new ArrayList<>();
        point = new int[M+1];
        haveGun = new int[M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                gunMap[i][j] = new PriorityQueue<>();
                int p = Integer.parseInt(st.nextToken());
                if(p > 0) {
                    gunMap[i][j].offer(new Gun(i, j, p));
                }
            }
        }

        player.add(new Player(-1, -1, -1, -1, -1));
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            playerMap[x][y] = new Player(i, x, y, s, d);
            player.add(new Player(i, x, y, s, d));
        }

        for(int k=1; k<=K; k++) {
            for(int i=1; i<=M; i++) {
                playerMove(i);
            }
        }

        for(int i=1; i<=M; i++) {
            System.out.print(point[i] + " ");
        }
    }

    private static void playerMove(int idx) {
        Player p = player.get(idx);

        int ndir = p.dir;
        int nx = p.x + dx[ndir];
        int ny = p.y + dy[ndir];
        if(!isInside(nx, ny)) { //격자를 벗어나는 경우, 정반대방향으로 방향 바꾸기
            ndir = (p.dir + 4 + 2) % 4;
            nx = p.x + dx[ndir];
            ny = p.y + dy[ndir];
        }
        p = new Player(p.idx, p.x, p.y, p.power, ndir);
        playerMap[p.x][p.y] = null;

        //이동한 방향에 플레이어가 없다면
        if(playerMap[nx][ny] == null) {
            //해당 칸에 총이 있을 경우
            if(gunMap[nx][ny].size() > 0) {
                //해당 플레이어가 총을 가지고 있는 경우
                if(haveGun[idx] != 0) {
                    //공격력이 큰 총을 획득
                    if(haveGun[idx] < gunMap[nx][ny].peek().power) {
                        Gun nowGun = gunMap[nx][ny].poll();
                        gunMap[nx][ny].offer(new Gun(nx, ny, haveGun[idx]));
                        haveGun[idx] = nowGun.power;
                    }
                }
                else {
                    //총이 없는 경우, 그 총을 획득
                    Gun nowGun = gunMap[nx][ny].poll();
                    haveGun[idx] = nowGun.power;
                }
            }
            //해당 칸으로 이동
            playerMap[nx][ny] = new Player(p.idx, nx, ny, p.power, ndir);
            player.get(p.idx).x = nx;
            player.get(p.idx).y = ny;
            player.get(p.idx).dir = ndir;
        }
        else if(playerMap[nx][ny] != null) { //이동한 방향에 플레이어가 있다면
            Player nextPlayer = playerMap[nx][ny];
            Player win = null;
            Player lose = null;
            int nextFight = nextPlayer.power + haveGun[nextPlayer.idx];
            int nowFight = p.power + haveGun[p.idx];
            if(nextFight == nowFight) {
                if(nextPlayer.power > p.power) {
                    win = nextPlayer;
                    lose = p;
                }
                else {
                    win = p;
                    lose = nextPlayer;
                }
            }
            else {
                if(nextFight > nowFight) {
                    win = nextPlayer;
                    lose = p;
                }
                else {
                    win = p;
                    lose = nextPlayer;
                }
            }

            if(win != null && lose != null) { //두 플레이어의 싸움 끝
                //이긴 플레이어는 포인트를 획득
                int getPoint = Math.abs((win.power + haveGun[win.idx]) - (lose.power + haveGun[lose.idx]));
                point[win.idx] += getPoint;

                //진 플레이어는 본인이 가지고 있는 총을 격자에 내려놓고
                gunMap[nx][ny].offer(new Gun(nx, ny, haveGun[lose.idx]));
                haveGun[lose.idx] = 0;
                //진 플레이어가 가지고 있던 방향대로 한칸 이동
                int ldir = lose.dir;
                int moveDir = 1;
                int lnx = nx + dx[ldir];
                int lny = ny + dy[ldir];
                while(!isInside(lnx, lny) || playerMap[lnx][lny] != null) {
                    ldir = (lose.dir + 4 + moveDir) % 4;
                    lnx = nx + dx[ldir];
                    lny = ny + dy[ldir];
                    moveDir++;
                }
                //빈칸 발견하면 이동
                playerMap[lnx][lny] = new Player(lose.idx, lnx, lny, lose.power, ldir);
                player.get(lose.idx).x = lnx;
                player.get(lose.idx).y = lny;
                player.get(lose.idx).dir = ldir;

                //해당 칸에 총이 있으면 높은 총을 가지고, 나머지는 내려놓기
                if(gunMap[lnx][lny].size()>0) {
                    Gun loseGun = gunMap[lnx][lny].poll();
                    haveGun[lose.idx] = loseGun.power;
                }

                //이긴 플레이어는 승리한 칸의 떨어져 있는 초와 원래 총중 가장 공격력이 높은 총 획득
                if(gunMap[nx][ny].size() > 0) {
                    if(haveGun[win.idx] < gunMap[nx][ny].peek().power) {
                        //총 교환
                        Gun winGun = gunMap[nx][ny].poll();
                        gunMap[nx][ny].offer(new Gun(nx, ny, haveGun[win.idx]));
                        haveGun[win.idx] = winGun.power;
                    }
                }
                //이긴 플레이어 이동 (현재 이동한 플레이어일 경우에만 수행)
                if(win.idx == p.idx) {
                    playerMap[nx][ny] = new Player(win.idx, nx, ny, win.power, ndir);
                    player.get(win.idx).x = nx;
                    player.get(win.idx).y = ny;
                    player.get(win.idx).dir = ndir;
                }
            }
        }
    }
}
