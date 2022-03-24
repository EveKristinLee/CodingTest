#include <iostream>
#include <algorithm>

using namespace std;
int n;
int dp[10001]{ 0 };
int wine[10001];

int main(void) {
	cin >> n;
	int maxWine = 0;
	for (int i = 1; i <= n; i++) {
		cin >> wine[i];
	}
	dp[0] = 0;
	dp[1] = wine[1];
	dp[2] = wine[1] + wine[2];
	for (int i = 3; i <= n; i++) {
		//n-3��°������ �ִ� + n-1��° �� + n��° ��
		//n-2��°������ �ִ� + n��° ��
		//n-1��°������ �ִ� == ���������� �� ���ž��� �ʿ� x
		dp[i] = max(wine[i] + wine[i - 1] + dp[i - 3], max(wine[i] + dp[i - 2], dp[i - 1]));
	}
	cout << dp[n] << "\n";
	return 0;
}