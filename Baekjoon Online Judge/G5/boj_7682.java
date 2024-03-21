package solving.solve_0914;
//BOJ G5 7682 틱택토

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_7682 {
    static int cntX;
    static int cntO;
    static int cntDot;
    static char[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while(true) {
            String s = br.readLine();
            if(s.equals("end")) {
                break;
            }
            map = new char[3][3];
            cntX = 0;
            cntO = 0;
            cntDot = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    map[i][j] = s.charAt((i*3) + j);
                    if(map[i][j] == 'X') {
                        cntX++;
                    }
                    else if(map[i][j] == 'O') {
                        cntO++;
                    }
                    else{
                        cntDot++;
                    }
                }
            }

            //개수 확인
            if(cntDot == 0) { //모두 입력
                if(cntX - cntO == 1) {
                    if(chk(map, 'O')) {
                        sb.append("invalid").append("\n");
                    }
                    else {
                        sb.append("valid").append("\n");
                    }
                    continue;
                }
                else {
                    sb.append("invalid").append("\n");
                }
                continue;
            }
            else {
                //입력 개수 확인
                if(cntX - cntO > 1) {
                    sb.append("invalid").append("\n");
                    continue;
                }

                //한명이 이겼나 확인
                if(cntX == cntO) {
                    if(chk(map, 'O') && chk(map, 'X')) {
                        sb.append("invalid").append("\n");
                    }
                    else if(chk(map, 'O')) {
                        sb.append("valid").append("\n");
                    }
                    else {
                        sb.append("invalid").append("\n");
                    }
                }
                else {
                    if(chk(map, 'X') && chk(map, 'O')) {
                        sb.append("invalid").append("\n");
                    }
                    else if(chk(map, 'X')) {
                        sb.append("valid").append("\n");
                    }
                    else {
                        sb.append("invalid").append("\n");
                        continue;
                    }
                }
            }
        }

        System.out.println(sb);
    }

    private static boolean chk(char[][] map, char chk) {
        boolean flag = false;
        //가로 확인
        for(int i=0; i<3; i++) {
            char first = map[i][0];
            if(first == chk) {
                boolean now = true;
                for(int j=1; j<3; j++) {
                    if(first != map[i][j]) {
                        now = false;
                    }
                }
                if(now) {
                    flag = true;
                }
            }
        }

        //세로 확인
        for(int i=0; i<3; i++) {
            char first = map[0][i];
            if(first == chk) {
                boolean now = true;
                for(int j=1; j<3; j++) {
                    if(first != map[j][i]) {
                        now = false;
                    }
                }
                if(now) {
                    flag = true;
                }
            }
        }

        //대각선 확인
        int x = 0;
        int y = 0;
        char first = map[x][y];
        if(first == chk) {
            boolean now = true;
            for(int i=1; i<3; i++) {
                if(map[x+i][y+i] != chk) {
                    now = false;
                }
            }
            if(now) {
                flag = true;
            }
        }
        int x2 = 2;
        int y2 = 0;
        char first2 = map[x2][y2];
        if(first2 == chk) {
            boolean now2 = true;
            for(int i=1; i<3; i++) {
                if(map[x2-i][y2+i] != chk) {
                    now2 = false;
                }
            }
            if(now2) {
                flag = true;
            }
        }
        return flag;
    }

}
