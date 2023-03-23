#include <iostream>
#include <vector>

using namespace std;

int main(void) {
	vector<int> ans;
	int t;
	cin >> t;
	while (t--) {
		int a, b;
		cin >> a >> b;
		if (a + b < 24) {
			ans.push_back(a + b);
		}
		else {
			if (a + b - 24 < 24) {
				ans.push_back((a + b) - 24);
			}
			else if(a + b - 24 == 24 || a + b - 24 == 0) {
				ans.push_back(0);
			}
		}
	}
	for (int i = 0; i < ans.size(); i++) {
		cout << "#" << i + 1 << " " << ans[i] << "\n";
	}
	return 0;
}