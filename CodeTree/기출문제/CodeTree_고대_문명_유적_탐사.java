package 기출문제;
//CodeTree 고대 문명 유적 탐사

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree_고대_문명_유적_탐사 {
    static final int size = 5;
    static final int small = 3;

    private static class Board {
        int[][] map = new int[size][size];

        public Board() {
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    map[i][j] = 0;
                }
            }
        }

        private boolean isInside(int x, int y) {
            if(x<0 || y<0 || x>=size || y>=size) {
                return false;
            }
            return true;
        }

        private Board rotate(int x, int y, int cnt) {
            Board res = new Board();
            Board tmp = new Board();
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    res.map[i][j] = this.map[i][j];
                    tmp.map[i][j] = this.map[i][j];
                }
            }

            for(int c=0; c<cnt; c++) { //돌릴 횟수
                int tmpY = y;
                for(int i=x; i<x+small; i++) {
                    int tmpX = x+2;
                    for(int j=y; j<y+small; j++) {
                        res.map[i][j] = tmp.map[tmpX][tmpY];
                        tmpX -= 1;
                    }
                    tmpY += 1;
                }

                for(int i=0; i<size; i++) {
                    for(int j=0; j<size; j++) {
                        tmp.map[i][j] = res.map[i][j];
                    }
                }
            }

            return res;
        }

        private int gain() {
            int score = 0;
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            boolean[][] visit = new boolean[size][size];

            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    if(!visit[i][j]) {
                        Queue<int[]> q = new ArrayDeque<>();
                        Queue<int[]> del = new ArrayDeque<>();
                        visit[i][j] = true;
                        q.offer(new int[] {i, j});
                        del.offer(new int[] {i, j});

                        while(!q.isEmpty()) {
                            int[] now = q.poll();

                            for(int d=0; d<4; d++) {
                                int nx = now[0] + dx[d];
                                int ny = now[1] + dy[d];
                                if(isInside(nx, ny)) {
                                    if(!visit[nx][ny] && map[now[0]][now[1]] == map[nx][ny]) {
                                        visit[nx][ny] = true;
                                        q.offer(new int[] {nx, ny});
                                        del.offer(new int[] {nx, ny});
                                    }
                                }
                            }
                        }

                        if(del.size() >= 3) {
                            score += del.size();
                            while(!del.isEmpty()) {
                                int[] now = del.poll();
                                map[now[0]][now[1]] = 0;
                            }
                        }
                    }
                }
            }
            return score;
        }


        private void fill(Queue<Integer> q) {
            for(int j=0; j<size; j++) {
                for(int i=size-1; i>=0; i--) {
                    if(map[i][j] == 0 && !q.isEmpty()) {
                        map[i][j] = q.poll();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        Board board = new Board();
        for(int i=0; i<size; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++) {
                board.map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }


        while(K-- > 0) {
            Board maxScoreBoard = null;
            int maxScore = 0;

            for(int c=1; c<=3; c++) {
                for(int j=0; j<=size-small; j++) {
                    for(int i=0; i<=size-small; i++) {
                        Board nowBoard = board.rotate(i, j, c);
                        int score = nowBoard.gain();
                        if(maxScore < score) {
                            maxScore = score;
                            maxScoreBoard = nowBoard;
                        }
                    }
                }
            }

            if(maxScoreBoard == null) { //유물 획득 실패
                break;
            }
            board = maxScoreBoard;

            while (true) {
                board.fill(q);
                int tmpScore = board.gain();
                if(tmpScore == 0) {
                    break;
                }
                maxScore += tmpScore;
            }
            sb.append(maxScore).append(" ");
        }

        System.out.println(sb);
    }
}
