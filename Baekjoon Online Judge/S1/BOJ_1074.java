package solving;
//BOJ S1 1074 Z
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
    static int N;
    static int r;
    static int c;
    static int size; //한 변의 길이
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);

        res = 0;
        find(size, r, c);
        System.out.println(res);
    }


    private static void find(int size, int r, int c) {
        if(size == 1) {
            return;
        }
        if(r < (size/2) && c < (size/2)) { //좌상
            find(size/2, r, c);
        }
        if(r < (size/2) && c >= (size/2)) { //우상
            res += size * size / 4;
            find(size/2, r, c-(size/2));
        }
        if(r >= (size/2) && c < (size/2)) { //좌하
            res += (size * size / 4) * 2;
            find(size/2, r-(size/2), c);
        }
        if(r >= (size/2) && c >= (size/2)) { //우하
            res += (size * size / 4) * 3;
            find(size/2, r-(size/2), c-(size/2));
        }
    }
}
