#include <iostream>

using namespace std;
#define num 1000000000

long long dp[101][11];

int main(void) {
	int n;
	cin >> n;

	for (int i = 1; i < 10; i++) {
		dp[1][i] = 1;
	}
	dp[1][0] = 0;
	for (int i = 2; i <= n; i++) {
		for (int j = 0; j < 10; j++) {
			if (j == 0) {
				//중간 과정에서도 숫자가 충분히 커질수 있기때문에 중간에도 나머지 연산진행
				//안하면 값이 너무 커져서 제대로된 계산이 안될수도 있음
				dp[i][j] = dp[i - 1][j + 1] % num;
			}
			else if (j == 9) {
				dp[i][j] = dp[i - 1][j - 1] % num;
			}
			else {
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % num;
			}
		}
	}

	long long sum = 0;
	for (int i = 0; i < 10; i++) {
		sum += dp[n][i];
	}
	cout << sum % num << "\n";

	return 0;
}