package com.company;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class 수정렬하기2750 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Integer> v = new Vector<Integer>();
        int n;
        n = sc.nextInt();
        for(int i=0; i<n; i++) {
            int tmp = sc.nextInt();
            v.add(tmp);
        }
        Collections.sort(v);
        for(Integer i : v) {
            System.out.println(i);
        }
    }
}
