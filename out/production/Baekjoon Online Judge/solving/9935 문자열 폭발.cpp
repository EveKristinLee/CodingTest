#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(void) {
	string s;
	string boom;
	cin >> s;
	cin >> boom;
	vector<char> chk;
	for (int i = 0; i < s.size(); i++) {
		chk.push_back(s[i]);
		if (chk.size() >= boom.size()) {
			string tmp = "";
			for (int i = chk.size() - boom.size(); i < chk.size(); i++) {
				tmp += chk[i];
			}
			if (tmp == boom) {
				for (int i = 0; i < boom.size(); i++) {
					chk.pop_back();
				}
			}
		}
	}
	if (chk.size() <= 0) {
		s = "FRULA";
	}
	else {
		s = "";
		for (int i = 0; i < chk.size(); i++) {
			s += chk[i];
		}
	}
	cout << s << "\n";
	return 0;
}