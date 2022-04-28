#include <iostream>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
	vector<int> answer;

	for (long long i = left; i <= right; i++) {
		int j = (i / n) + 1;
		int k = (i % n) + 1;
		if (j >= k) {
			answer.push_back(j);
		}
		else {
			answer.push_back(k);
		}
	}
	return answer;
}

int main(void) {
	int n = 4;
	long long left = 7;
	long long right = 14;
	vector<int> result = solution(n, left, right);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}