#include <iostream>
#include <queue>

using namespace std;

int main(void) {
	priority_queue<int, vector<int>, greater<int>> pq;
	long long sum = 0;
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int cnt;
		cin >> cnt;
		pq.push(cnt);
	}
	while (pq.size() > 1) {
		int a = pq.top();
		pq.pop();
		int b = pq.top();
		pq.pop();
		sum += (a + b);
		pq.push(sum);
	}
	cout << sum << "\n";
	return 0;
}