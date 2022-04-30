#include <iostream> 
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

//이해가 안되요..
int solution(string name) {
	int answer = 0;
	int len = name.size();

	for (int i = 0; i < len; i++) {
		answer += min(name[i] - 'A', 'Z' - name[i] + 1);
	}

	int move = len - 1; //최대 이동횟수
	int next;
	for (int i = 0; i < len; i++) {
		next = i + 1; //다음 A가 아닌 글자
		while (next < len && name[next] == 'A') {
			next++;
		}
		//i + (len - next) = 왼쪽으로 이동할때 거리
		move = min(move, i + (len - next) + min(i, len - next));
	}
	answer += move;


	return answer;
}

int main(void) {
	string name = "RABAMATAWADLAFAVAAE";
	int result = solution(name);
	cout << result << "\n";
	return 0;
}