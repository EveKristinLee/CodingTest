public class 조이스틱 {
	//A~N = 13 / Z~N+1 = 13 => 'N'이 기준
	public static int solution(String name) {
		int answer = 0;
		int len = name.length();
		int move = len-1;
		for(int i=0; i<len; i++) {
			if(name.charAt(i) > 'N') {
				answer += ('Z' - name.charAt(i)) + 1;
			}
			else {
				answer += name.charAt(i) - 'A';
			}

			int lastA = i+1;
			while(lastA < len && name.charAt(lastA) == 'A') {
				lastA++;
			}
			//1. 오른쪽으로만 이동
			//2. 오른쪽으로 진행하다가 왼쪽으로 이동하는게 짧을 경우
			//3. "AAAAABBAAAAAAABAAA" 오른쪽으로 진행하다가 왼쪽으로 진행하는 경우가 빠를때, 쭉 오른쪽으로 진행하는게 빠를때
			move = Math.min(move, i + (len - lastA) + Math.min(i, (len - lastA)));
		}

		return answer+move;
	}

	public static void main(String[] args) {
		String name = "JEROEN";
		int res = solution(name);
		System.out.println(res);
	}
}
