#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int n;
	int arr[1001];
	int dp[1001];
	int maxLen = 0;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		dp[i] = 1; //�ڱ� �ڽ��� ���� = 1
	}
	//i��° ������ �� ���� ���ڵ��� ���� �κм����� ������ 
	//���� �� �κм����� ���� + 1 == ���� �� �����ϴ� �κм����� ����
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[i] > arr[j]) {
				dp[i] = max(dp[i], dp[j] + 1);
			}
		}
	}
	//���� �� ���� ã��
	for (int i = 0; i < n; i++) {
		maxLen = max(maxLen, dp[i]);
	}
	cout << maxLen << "\n";
	return 0;
}