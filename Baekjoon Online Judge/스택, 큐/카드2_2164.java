package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 카드2_2164 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=n; i++) {
			q.offer(i);
		}
		
		while(n != 1) {
			q.poll(); //맨 위 카드 버리기
			q.offer(q.poll()); //맨 위 카드 뒤에 넣고 버리기
			n--;
		}
		System.out.println(q.peek());
	}
}
