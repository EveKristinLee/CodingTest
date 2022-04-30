#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
	vector<int> answer;
	if (n > s) {
		answer.push_back(-1);
		return answer;
	}
	for (int i = n; i >= 1; i--) {
		int num = s / i;
		s -= num;
		answer.push_back(num);
	}
	return answer;
}

int main(void) {
	int n = 2;
	int s = 9;
	vector<int> result = solution(n, s);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}
	return 0;
}