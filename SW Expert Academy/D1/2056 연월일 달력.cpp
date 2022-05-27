#include <iostream>
#include <string>

using namespace std;

bool isOk(int month, int day) {
	if (month == 1 || month == 3 || month == 5 ||
		month == 7 || month == 8 || month == 10 ||
		month == 12) {
		if (day > 0 && day <= 31) {
			return true;
		}
	}

	if (month == 4 || month == 6 || month == 9 ||
		month == 11) {
		if (day > 0 && day <= 30) {
			return true;
		}
	}

	if (month == 2) {
		if (day > 0 && day <= 28) {
			return true;
		}
	}
	return false;
}

int main(void) {
	int T;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		string date;
		string ans;
		cin >> date;
		ans = date;
		string month = date.substr(4, 2);
		string day = date.substr(6, 2);
		if (isOk(stoi(month), stoi(day))) {
			ans.insert(4, "/");
			ans.insert(7, "/");
		}
		else {
			ans = "-1";
		}

		cout << "#" << t << " " << ans << "\n";
	}
	return 0;
}