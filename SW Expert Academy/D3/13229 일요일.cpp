#include <iostream>
#include <map>
#include <string>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	map<string, int> day { {"SUN", 0}, {"MON", 1}, {"TUE", 2}, {"WED", 3}, {"TUE", 4}, {"FRI", 5}, {"SAT", 6} };
	for (int t = 1; t <= T; t++) {
		string now;
		cin >> now;
		int rest = 7 - day[now];
		cout << "#" << t << " " << rest << "\n";
	}

}