package com.company;
//BOJ S5 2303 숫자 게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2303 {
    static int N;
    static int[] last_num;
    static List<int[]> card;
    static int[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        card = new ArrayList<>();

        for(int i=0; i<N; i++) {
            int[] num = new int[5];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                num[j] = Integer.parseInt(st.nextToken());
            }
            card.add(num);
        }

        last_num = new int[N];
        int res = -1;
        int maxNum = 0;
        for(int i=0; i<N; i++) {
            select = new int[3];
            combi(i, 0, 0);
            if(maxNum <= last_num[i]) {
                maxNum = last_num[i];
                res = i+1;
            }
        }
        System.out.println(res);
    }

    private static void combi(int person, int idx, int start) {
        if(idx == 3) {
//            System.out.println(Arrays.toString(select));
            int lastNum = getLastNum();
            last_num[person] = Math.max(last_num[person], lastNum);
//            System.out.println(lastNum);
            return;
        }

        for(int i=start; i<5; i++) {
            select[idx] = card.get(person)[i];
            combi(person, idx+1, i+1);
        }
    }

    private static int getLastNum() {
        int sum = 0;
        for(int i=0; i<3; i++) {
            sum += select[i];
        }

        String s = String.valueOf(sum);
        return Integer.parseInt(String.valueOf(s.charAt(s.length()-1)));
    }
}
