#include <iostream>
#include <algorithm>

using namespace std;

int dp[101][100001] = { 0 };

int main(void) {
	int n, k;
	cin >> n >> k;
	int w[101] = { 0 };
	int v[101] = { 0 };

	for (int i = 1; i <= n; i++) {
		cin >> w[i] >> v[i];
	}
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= k; j++) {
			if (j - w[i] >= 0) {
				dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
			}
			else {
				dp[i][j] = dp[i - 1][j];
			}
		}
	}
	cout << dp[n][k] << "\n";


	return 0;
}