#include <iostream>
#define INF 1000000009

using namespace std;

int dp[1000001] = { 0 };

int main(void) {
	int T;
	cin >> T;
	dp[0] = 1;
	dp[1] = 1;
	dp[2] = 2;
	while (T--) {
		int n;
		cin >> n;
		for (int i = 3; i <= n; i++) {
			if (dp[i] != 0) {
				continue;
			}
			dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % INF;
		}
		cout << dp[n] % INF << "\n";
	}
	return 0;
}