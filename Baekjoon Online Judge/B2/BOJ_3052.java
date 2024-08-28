package homework;
//BOJ B2 3052 나머지

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ_3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<10; i++) {
            int now = sc.nextInt();
            set.add(now % 42);
        }

        System.out.println(set.size());
    }
}
