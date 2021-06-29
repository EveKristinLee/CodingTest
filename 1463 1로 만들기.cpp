#include <iostream>
#include <algorithm>

using namespace std;

int num[1000001];
//3으로 나누어 떨어진다면 num[N] = num[N/3] + 1
//2로 나누어 떨어진다면 num[N] = num[N/2] + 1
//1을 뺀다면 num[N] = num[N-1] + 1
int main(void) {
	int N;
	cin >> N;
	num[1] = 0;
	for (int i = 2; i <= N; i++) {
		num[i] = num[i - 1] + 1;
		if (i % 2 == 0) {
			num[i] = min(num[i], num[i / 2] + 1);
		}
		if (i % 3 == 0) {
			num[i] = min(num[i], num[i / 3] + 1);
		}
	}
	cout << num[N] << "\n";
	return 0;
}