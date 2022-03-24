#include <iostream>

using namespace std;

int main(void) {
	int T, n, m;
	int dp[31][31] = { 0 };
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> n >> m;

		for (int i = 1; i <= m; i++) {
			dp[1][i] = i;
		}

		for (int i = 2; i <= n; i++ ) {
			for (int j = 2; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
			}
		}
		cout << dp[n][m] << "\n";
	}
	return 0;
}