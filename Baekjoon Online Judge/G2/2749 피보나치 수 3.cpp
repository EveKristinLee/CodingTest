#include <iostream>

using namespace std;

long long dp[1500001] = { 0 };

//1500000주기로 같음(피사노 주기)
int main(void) {
	long long n;
	cin >> n;
	dp[0] = 0;
	dp[1] = 1;
	for (int i = 2; i < 1500001; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
	}

	cout << dp[n % 1500000] << "\n";
	return 0;
}