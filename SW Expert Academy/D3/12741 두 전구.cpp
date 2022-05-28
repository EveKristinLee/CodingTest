#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
	vector<int> ans;
	int t;
	cin >> t;
	while (t--) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;
		int sec = min(b, d) - max(a, c);
		if (sec < 0) {
			sec = 0;
		}
		ans.push_back(sec);
	}
	for (int i = 0; i < ans.size(); i++) {
		cout << "#" << i + 1 << " " << ans[i] << "\n";
	}
	return 0;
}