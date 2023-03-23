#include <iostream>
#include <string>
#include <vector>

using namespace std;

int dp[60001] = { 0 };

int solution(int n) {
	dp[0] = 1;
	dp[1] = 1;
	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 2] + dp[i - 1];
	}
	return dp[n];
}

int main(void) {
	int n = 4;
	int result = solution(n);
	cout << result << "\n";
	return 0;
}