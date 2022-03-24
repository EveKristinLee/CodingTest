#include <iostream>
#include <cstring>

using namespace std;

long long dp[101];

int main(void) {
	int T;
	cin >> T;
	for (int t = 0; t < T; t++) {
		int n;
		cin >> n;
		memset(dp, 0, sizeof(long long) * n);
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i <= n; i++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}
		cout << dp[n] << "\n";
	}
	return 0;
}