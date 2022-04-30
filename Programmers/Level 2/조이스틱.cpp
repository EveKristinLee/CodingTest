#include <iostream> 
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

//���ذ� �ȵǿ�..
int solution(string name) {
	int answer = 0;
	int len = name.size();

	for (int i = 0; i < len; i++) {
		answer += min(name[i] - 'A', 'Z' - name[i] + 1);
	}

	int move = len - 1; //�ִ� �̵�Ƚ��
	int next;
	for (int i = 0; i < len; i++) {
		next = i + 1; //���� A�� �ƴ� ����
		while (next < len && name[next] == 'A') {
			next++;
		}
		//i + (len - next) = �������� �̵��Ҷ� �Ÿ�
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