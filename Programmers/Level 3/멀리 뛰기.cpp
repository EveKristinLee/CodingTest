#include <iostream>
#include <string>
#include <vector>

using namespace std;

int dp[2001] = { 0 };

long long solution(int n) {
	long long answer = 0;
	dp[0] = 1;
	dp[1] = 1;
	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}
	return dp[n] % 1234567;;
}

int main(void) {
	int n = 3;
	long long result = solution(n);
	cout << result << "\n";
	return 0;
}