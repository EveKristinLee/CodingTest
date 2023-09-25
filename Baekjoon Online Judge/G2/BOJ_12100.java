//BOJ G2 2048(Easy)
package solving_2.solve_12.solve_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12100 {
    static int N;
    static int res;
    static Queue<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        move(map, 0);
        System.out.println(res);
    }

    private static void move(int[][] map, int cnt) {
        if(cnt >= 5) {
            res = Math.max(res, getMax(map));
            return;
        }
        int[][] tmp = copy(map);
        up(tmp, cnt);
        tmp = copy(map);
        down(tmp, cnt);
        tmp = copy(map);
        right(tmp, cnt);
        tmp = copy(map);
        left(tmp, cnt);
    }

    private static void up(int[][] map, int cnt) {
        for(int i=0; i<N; i++) { //가로
            nums = new ArrayDeque<>();
            for(int j=0; j<N; j++) { //세로
                if(map[j][i] != 0) {
                    nums.offer(map[j][i]);
                }
            }
            int idx = 0;
            while(idx < N) {
                while(!nums.isEmpty()) {
                    int now = nums.poll();
                    if(!nums.isEmpty()) {
                        if(now == nums.peek()) {
                            map[idx++][i] = now * 2;
                            nums.poll();
                        }
                        else {
                            map[idx++][i] = now;
                        }
                    }
                    else {
                        map[idx++][i] = now;
                    }
                }
                if(idx < N) {
                    map[idx++][i] = 0;
                }
            }
        }
        move(map, cnt+1);
    }

    private static void down(int[][] map, int cnt) {
        for(int i=0; i<N; i++) { //가로
            nums = new ArrayDeque<>();
            for(int j=N-1; j>=0; j--) { //세로
                if(map[j][i] != 0) {
                    nums.offer(map[j][i]);
                }
            }
            int idx = N-1;
            while(idx >= 0) {
                while(!nums.isEmpty()) {
                    int now = nums.poll();
                    if(!nums.isEmpty()) {
                        if(now == nums.peek()) {
                            map[idx--][i] = now * 2;
                            nums.poll();
                        }
                        else {
                            map[idx--][i] = now;
                        }
                    }
                    else {
                        map[idx--][i] = now;
                    }
                }
                if(idx >= 0) {
                    map[idx--][i] = 0;
                }
            }
        }
        move(map, cnt+1);
    }

    private static void right(int[][] map, int cnt) {
        for(int i=0; i<N; i++) { //세로
            nums = new ArrayDeque<>();
            for(int j=N-1; j>=0; j--) { //가로
                if(map[i][j] != 0) {
                    nums.offer(map[i][j]);
                }
            }
            int idx = N-1;
            while(idx >= 0) {
                while(!nums.isEmpty()) {
                    int now = nums.poll();
                    if(!nums.isEmpty()) {
                        if(now == nums.peek()) {
                            map[i][idx--] = now * 2;
                            nums.poll();
                        }
                        else {
                            map[i][idx--] = now;
                        }
                    }
                    else {
                        map[i][idx--] = now;
                    }
                }
                if(idx >= 0){
                    map[i][idx--] = 0;
                }
            }
        }
        move(map, cnt+1);
    }

    private static void left(int[][] map, int cnt) {
        for(int i=0; i<N; i++) { //세로
            nums = new ArrayDeque<>();
            for(int j=0; j<N; j++) { //가로
                if(map[i][j] != 0) {
                    nums.offer(map[i][j]);
                }
            }
            int idx = 0;
            while(idx < N) {
                while(!nums.isEmpty()) {
                    int now = nums.poll();
                    if(!nums.isEmpty()) {
                        if(now == nums.peek()) {
                            map[i][idx++] = now * 2;
                            nums.poll();
                        }
                        else {
                            map[i][idx++] = now;
                        }
                    }
                    else {
                        map[i][idx++] = now;
                    }
                }
                if(idx < N) {
                    map[i][idx++] = 0;
                }
            }
        }
        move(map, cnt+1);
    }

    private static int getMax(int[][] map) {
        int max = -1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(map[i][j], max);
            }
        }
        return max;
    }

    private static int[][] copy(int[][] map) {
        int[][] tmp = new int[N][N];
        for(int i=0; i<N; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, N);
        }
        return tmp;
    }
}
