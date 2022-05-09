#include <iostream>
#include <string>
#include <vector>

using namespace std;

long long fac(int n) {
	long long ans = 1;
	for (int i = 1; i <= n; i++) {
		ans *= i;
	}
	return ans;
}

vector<int> solution(int n, long long k) {
	vector<int> answer;
	vector<int> num;
	long long now = k;
	int cnt = 1;
	for (int i = 1; i <= n; i++) {
		num.push_back(i);
	}
	while (cnt != n) {
		long long tmp = fac(n - cnt);
		int idx = (now - 1) / tmp;
		answer.push_back(num[idx]);
		num.erase(num.begin() + idx);
		cnt++;
		now %= tmp;
		if (now == 0) {
			now = tmp;
		}
	}
	answer.push_back(num[0]);
	return answer;
}

int main(void) {
	int n = 3;
	long long k = 5;
	vector<int> result = solution(n, k);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}