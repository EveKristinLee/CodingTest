#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		long long sum = 0;
		int n;
		vector<int> p;
		cin >> n;
		for (int i = 0; i < n; i++) {
			int tmp;
			cin >> tmp;
			p.push_back(tmp);
		}
		int idx = p[n - 1];
		for (int i = n - 1; i >= 0; i--) {
			if (idx >= p[i]) {
				sum += (idx - p[i]);
			}
			else {
				idx = p[i];
			}
		}

		cout << "#" << t << " " << sum << "\n";
	}
	return 0;
}