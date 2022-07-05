package com.company;

import java.util.ArrayList;

public class 같은숫자는싫어 {
    public static int[] solution(int []arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int tmp = -1;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] != tmp) {
                list.add(arr[i]);
                tmp = arr[i];
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        int[] result = solution(arr);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }
}
