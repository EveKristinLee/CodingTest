package solving.solve_0914;
//BOJ G5 1038 감소하는 수

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_1038 {
    static int N;
    static List<Long> list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if(N < 10) { //한자리수이면 그대로 출력
            System.out.println(N);
        }
        else if(N >= 1023) {
            //9876543210 보다 큰 감소하는 수는 없음
            //10자리 수를 만드는 방법은 2^10인데, 선택하지 않는 경우 하나를 빼면 1023
            System.out.println(-1);
        }
        else {
            list = new ArrayList<>();
            for(int i=0; i<10; i++) {
                comb(i);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }
    }

    private static void comb(long num) {
        list.add(num);
        long tmp = num % 10;
        if(tmp == 0) {
            return;
        }

        for(long i=tmp-1; i>=0; i--) {
            long val = num * 10 + i;
            comb(val);
        }
    }

}
