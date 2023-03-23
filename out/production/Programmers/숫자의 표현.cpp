#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(int n) {
	int answer = 1;
	int half = (n + 1) / 2;
	for (int i = 1; i <= half; i++) {
		int sum = i;
		for (int j = i + 1; j <= half; j++) {
			sum += j;
			if (sum == n) {
				answer++;
			}
			else if (sum > n) {
				break;
			}
		}
	}
	return answer;
}

int main(void) {
	int n = 15;
	int result = solution(n);
	cout << result << "\n";
	return 0;
}