#include <iostream>
#include <algorithm>

using namespace std;

int n;
int tri[501][501];
int dp[501][501] = { 0 };

int main(void) {
	cin >> n;
	int maxSum = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= i; j++) {
			cin >> tri[i][j];
		}
	}
	dp[1][1] = tri[1][1];
	for (int i = 2; i <= n; i++) {
		for (int j = 1; j <= i; j++) {
			dp[i][j] = tri[i][j] + dp[i - 1][j]; //¹Ù·Î À§¿¡²¨
			if (j > 1) {
				dp[i][j] = max(dp[i][j], tri[i][j] + dp[i - 1][j - 1]);
			}
		}
	}
	for (int i = 1; i <= n; i++) {
		maxSum = max(maxSum, dp[n][i]);
	}
	cout << maxSum << "\n";
	return 0;
}