#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <deque>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	while (T--) {
		string s;
		cin >> s;
		int n; 
		cin >> n;
		string number;
		cin >> number;
		deque<int> num;
		string tmp = "";
		for (int i = 1; i < number.size() -1; i++) {
			if (number[i] != ',') {
				tmp += number[i];
			}
			else {
				num.push_back(stoi(tmp));
				tmp = "";
			}
		}
		if (tmp != "") {
			num.push_back(stoi(tmp));
		}

		bool rev = false;
		bool err = false;
		for (int i = 0; i < s.size(); i++) {
			if (s[i] == 'R') {
				if (rev) {
					rev = false;
				}
				else {
					rev = true;
				}
			}
			else {
				if (!num.empty()) {
					if (rev) {
						num.pop_back();
					}
					else {
						num.pop_front();
					}
				}
				else {
					err = true;
					break;
				}
			}
		}

		if (err) {
			cout << "error" << "\n";
			continue;
		}
		cout << "[";
		if (rev) {
			for (auto it = num.rbegin(); it != num.rend(); it++) {
				if (it == num.rend() - 1) {
					cout << *it;
				}
				else {
					cout << *it << ",";
				}
			}
		}
		else {
			for (auto it = num.begin(); it != num.end(); it++) {
				if (it == num.end() - 1) {
					cout << *it;
				}
				else {
					cout << *it << ",";
				}
			}
		}
		cout << "]" << "\n";
	}
}