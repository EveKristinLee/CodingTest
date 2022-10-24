package solving.solve_1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//BOJ S5
public class 막대기_1094 {

	static int X; //구해야하는 막대의 길이
	static List<Integer> num; //자른 막대 저장공간
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //BufferedReader 선언
		X = Integer.parseInt(br.readLine()); //X입력
		num = new LinkedList<>(); //num 초기화
		num.add(64); //64넣기
		if(X < 64) { //x가 64보다 작다면
			while(true) { //while문 반복
				Collections.sort(num); //num정렬
				int minNum = num.get(0); //막대중 가장 작은 값
				num.remove(0); //짧은 막대 num에서 제거
				num.add(minNum /2); //가장 짧은 막대 이등분 해서 넣기
				num.add(minNum /2); //가장 짧은 막대 이등분 해서 넣기
				
				Collections.sort(num); //num정렬
				int tmpSum = 0; //합 저장할 공간
				for(int i=1; i<num.size(); i++) { //1부터 num의 size까지
					tmpSum += num.get(i); //tmpSum에 더하기
				}
				if(tmpSum >= X) { //tmpSum이 X보다 크거나 같다면
					num.remove(0); //첫번째 원소 제거
				}
				
				int sum = 0; //막대들의 합
				for(int i=0; i<num.size(); i++) { //0부터 num의 size까지
					sum += num.get(i); //sum에 막대길이 저장
				}
				if(sum == X) { //막대들의 합이 X와 같다면
					break; //while문 벗어나기
				}
			}
			System.out.println(num.size()); //막대 개수 출력
		}
		else { //아니라면
			System.out.println("1"); //막대 하나 출력
		}
	}

}
