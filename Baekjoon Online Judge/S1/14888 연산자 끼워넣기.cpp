//백트레킹으로 다시풀기

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n; //수의 개수
vector<int> num;
int calc[4] = { 0 };
int maxNum = -9876654321;
int minNum = 987654321;

void dfs(int sum, int plus, int minus, int mul, int div, int idx) {
	if (idx == n) {
		maxNum = max(sum, maxNum);
		minNum = min(sum, minNum);
		return;
	}

	if (plus != 0) {
		dfs(sum + num[idx], plus-1, minus, mul, div, idx + 1);
	}
	if (minus != 0) {
		dfs(sum - num[idx], plus, minus-1, mul, div, idx + 1);
	}
	if (mul != 0) {
		dfs(sum * num[idx], plus, minus, mul-1, div, idx + 1);
	}
	if (div != 0) {
		dfs(sum / num[idx],plus, minus, mul, div-1, idx + 1);
	}
}

int main(void) {
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		num.push_back(a);
	}
	for (int i = 0; i < 4; i++) {
		cin >> calc[i];
	}
	dfs(num[0],calc[0], calc[1], calc[2], calc[3], 1);
	cout << maxNum << "\n";
	cout << minNum << "\n";
	return 0;
}