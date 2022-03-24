#include <iostream>

using namespace std;

long long dp[91][3]; //크기 고려!!!!!!제발

int main(void) {
	int n;
	cin >> n;
	dp[1][0] = 0;
	dp[1][1] = 1;
	for (int i = 2; i <= n; i++) {
		for (int j = 0; j < 2; j++) {
			if (j == 1) {
				dp[i][j] = dp[i - 1][0];
			}
			if(j == 0) {
				dp[i][j] = dp[i - 1][0] + dp[i - 1][1];
			}
		}
	}
	cout << dp[n][1] + dp[n][0] << "\n";

	return 0;
}