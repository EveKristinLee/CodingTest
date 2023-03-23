#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int main(void) {
	int n, m;
	vector<string> see;
	vector<string> result;

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		string tmp;
		cin >> tmp;
		see.push_back(tmp);
	}
	sort(see.begin(), see.end());
	for (int i = 0; i < m; i++) {
		string tmp;
		cin >> tmp;
		if (binary_search(see.begin(), see.end(), tmp)) {
			result.push_back(tmp);
		}
	}

	cout << result.size() << "\n";
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}
	return 0;
}