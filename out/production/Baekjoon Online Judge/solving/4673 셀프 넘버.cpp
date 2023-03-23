#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> not_self;
vector<int> self;

int d(int n) {
	string st = to_string(n);
	int result = 0;
	result += n;
	for (int i = 0; i < st.length(); i++) {
		result += (st[i] - '0');
	}

	return result;
}

int main(void) {
	for (int i = 1; i <= 10000; i++) {
		not_self.push_back(d(i));
	}
	for (int i = 1; i <= 10000; i++) {
		if (find(not_self.begin(), not_self.end(), i) == not_self.end()) {
			self.push_back(i);
		}
	}
	sort(self.begin(), self.end());
	for (int i = 0; i < self.size(); i++) {
		cout << self[i] << "\n";
	}
}