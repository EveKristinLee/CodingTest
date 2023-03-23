#include <iostream>
#include <vector>

using namespace std;

int n;
vector<long long> dp;

int main(void) {
	cin >> n;
	dp[0] = 1;
	dp[1] = 1;
	
	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2] % 15746;
	}
	cout << dp[n] % 15746 << "\n";

	return 0;
}