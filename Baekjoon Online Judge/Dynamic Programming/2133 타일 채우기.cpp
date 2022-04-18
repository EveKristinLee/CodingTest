#include <iostream>

using namespace std;

int dp[31] = { 0 };

int main(void) {
	int n;
	cin >> n;
	dp[0] = 1;
	dp[1] = 0;
	dp[2] = 3;
	if (n % 2 != 0) {
		cout << 0 << "\n";
		return 0;
	}
	for (int i = 4; i <= n; i+=2) {
		dp[i] = dp[i - 2] * dp[2];
		for (int j = i - 4; j >= 0; j-=2) {
			if (j % 2 == 0) {
				dp[i] += dp[j] * 2;
			}
		}
	}
	cout << dp[n] << "\n";
	return 0;
}