#include <iostream>
#include <vector>

using namespace std;

int pisano(int m) {
	vector<int> dp;
	dp.push_back(0);
	dp.push_back(1);
	int cnt = 2;
	int idx = 2;
	while (1) {
		dp.push_back(dp[idx - 1] + dp[idx - 2]);
		dp[idx] %= m;
		if (dp[idx] == 0 && dp[idx - 1] == 1) {
			break;
		}
		idx++;
		cnt++;
	}
	return cnt;
}

int main(void) {
	int T;
	cin >> T;
	while (T--) {
		int tc;
		cin >> tc;
		int m;
		cin >> m;
		cout << tc << " " << pisano(m) << "\n";
	}
	return 0;
}