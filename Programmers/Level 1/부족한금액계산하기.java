package com.company;

public class 부족한금액계산하기 {

    public static long solution(int price, int money, int count) {
        long answer = 0;
        long sumPrice = 0;
        for(int i=count; i> 0; i--) {
            sumPrice += i * price;
        }
        if(sumPrice > money) {
            answer = sumPrice - money;
        }
        return answer;
    }

    public static void main(String[] args) {
        int price = 3;
        int money = 20;
        int count = 4;
        long result = solution(price, money, count);
        System.out.println("result = " + result);
    }
}
