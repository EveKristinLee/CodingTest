package homework;
//BOJ B1 11005 진법 변환 2

import java.io.*;
import java.util.*;

public class BOJ_11005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        List<Character> res = new ArrayList<>();
        while(N > 0) {
            int tmp = N % B;
            if(tmp < 10) {
                res.add((char)(tmp + '0'));
            }
            else {
                res.add((char)((tmp-10) + 'A'));
            }
            N /= B;
        }
        for(int i=res.size()-1; i>=0; i--) {
            System.out.print(res.get(i));
        }
    }
}
