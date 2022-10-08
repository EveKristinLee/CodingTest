#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	int tc;
	cin >> tc;
	priority_queue<int> maxQ;
	priority_queue<int, vector<int>, greater<int>> minQ;
	for (int i = 1; i <= tc; i++) {
		int n;
		cin >> n;
		if (maxQ.size() == minQ.size()) {
			maxQ.push(n);
		}
		else {
			minQ.push(n);
		}
		
		if (!minQ.empty() && !maxQ.empty() && minQ.top() < maxQ.top()) {
			int tmpMin = minQ.top();
			minQ.pop();
			int tmpMax = maxQ.top();
			maxQ.pop();
			minQ.push(tmpMax);
			maxQ.push(tmpMin);
		}
		cout << maxQ.top() << "\n";
	}
	return 0;
}