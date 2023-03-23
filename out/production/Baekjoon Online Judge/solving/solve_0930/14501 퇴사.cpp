#include <iostream>
#include <algorithm>

using namespace std;

int time[16];
int pay[16];
int dp[16];

int main(void) {
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> time[i] >> pay[i];
	}

	for (int i = n; i > 0; i--) {
		if (i + time[i] > n + 1) {
			dp[i] = dp[i + 1];
		}
		else {
			//i+1�ϱ����� dp���� i���� �޿� + i���Ŀ� ���Ҽ��ִ� �������� 
			//dp���� ���� ���߿� ū���� i�Ͽ� �����ִ� ���� ū������
			dp[i] = max(dp[i + 1], pay[i] + dp[i + time[i]]);
		}
	}

	cout << dp[1] << "\n";
	return 0;
}