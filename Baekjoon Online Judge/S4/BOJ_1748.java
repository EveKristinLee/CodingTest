package solving;
//BOJ S4 1748 수 이어 쓰기 1

import java.util.Scanner;

public class BOJ_1748 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = 0;
        int cnt = 1;
        int num = 10;
        for(int i=1; i<=n; i++) {
            if(i % num == 0) {
                cnt += 1;
                num *= 10;
            }
            res += cnt;
        }
        System.out.println(res);
    }
}
