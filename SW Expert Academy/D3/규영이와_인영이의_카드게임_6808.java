package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 규영이와_인영이의_카드게임_6808 {

	static int[] card;
	static int[] iyCard;
	static int[] numbers;
	static boolean[] visit;
	static boolean[] num;
	static int win = 0;
	static int lose = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			win = 0;
			lose = 0;
			card = new int[9];
			iyCard = new int[9];
			numbers = new int[9];
			visit = new boolean[9];
			num = new boolean[19];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				card[i] = Integer.parseInt(st.nextToken());
				num[card[i]] = true;
			}
			
			int idx = 0;
			for(int i=1; i<=18; i++) {
				if(!num[i]) {
					iyCard[idx++] = i;
				}
			}
			
			permutation(0);
			sb.append("#"+t+" "+win+" " + lose).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void calc() {
		int scoreCard = 0;
		int scoreIY = 0;
		for(int i=0; i<9; i++) {
			if(card[i] > numbers[i]) {
				scoreCard += (card[i] + numbers[i]);
			}
			else {
				scoreIY += (card[i] + numbers[i]);
			}
		}
		if(scoreCard < scoreIY) {
			lose++;
		}
		else {
			win++;
		}
	}

	private static void permutation(int cnt) {
		if(cnt == 9) {
			calc();
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(!visit[i]) {
				numbers[cnt] = iyCard[i];
				visit[i] = true;
				permutation(cnt+1);
				visit[i] = false;
			}
		}
	}

}
