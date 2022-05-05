#include <iostream>

using namespace std;

int solution(int num) {
	int answer = 0;
	long long tmp = (long long)num;
	while (tmp > 1) {
		cout << tmp << "\n";
		if (tmp % 2 == 0) {
			tmp /= 2;
		}
		else if (tmp % 2 != 0) {
			tmp *= 3;
			tmp += 1;
		}
		answer++;
		if (answer >= 500) {
			answer = -1;
			break;
		}
	}
	return answer;
}

int main(void) {
	int num = 626331;
	int result = solution(num);
	cout << result << "\n";
	return 0;
}