package solving;
//BOJ S1 1497 기타콘서트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1497 {
    static int N; //기타의 개수
    static int M; //곡의 개수
    static boolean[] visit;
    static String[] guitar;
    static long[] play;
    static int guitarCnt;
    static int playCnt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N+1];
        guitar = new String[N+1];
        play = new long[N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            guitar[i] = st.nextToken();

            String s = st.nextToken();
            long playN = canPlay(s);
            play[i] = playN;
        }

        guitarCnt = N;
        playCnt = 0;
        subset(1);
        if(playCnt == 0) {
            System.out.println(-1);
        }
        else{
            System.out.println(guitarCnt);
        }
    }


    private static void subset(int idx) {
        if(idx > N) {
            int nowGuitarCnt = 0;
            int nowPlayCnt = 0;
            long nowPlayBitCnt = 0L;
            for(int i=1; i<=N; i++) {
                if(visit[i]) {
                    nowGuitarCnt += 1;
                    nowPlayBitCnt |= play[i];
                }
            }
            nowPlayCnt = Long.bitCount(nowPlayBitCnt);
            if(nowPlayCnt >= playCnt) {
                playCnt = nowPlayCnt;
                if(guitarCnt > nowGuitarCnt) {
                    guitarCnt = nowGuitarCnt;
                }
            }
            return;
        }

        visit[idx] = true;
        subset(idx+1);

        visit[idx] = false;
        subset(idx+1);
    }

    private static long canPlay(String s) {
        String binaryS = "";

        for(int i=0; i<M; i++) {
            if(s.charAt(i) == 'Y') {
                binaryS += "1";
            }
            else {
                binaryS += "0";
            }
        }
        return Long.parseLong(binaryS, 2);
    }

}
