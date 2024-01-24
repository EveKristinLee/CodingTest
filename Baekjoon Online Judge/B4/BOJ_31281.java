package com.company;
//BOJ B4 31281 ЗЛАТНАТА СРЕДА

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_31281 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] nums = new long[3];
        for(int i=0; i<3; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(nums);
        System.out.println(nums[1]);
    }
}
