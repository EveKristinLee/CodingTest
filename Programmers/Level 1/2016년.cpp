#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

map<int, string> day = { {1, "FRI"}, {2, "SAT"}, {3, "SUN"}, {4, "MON"}, {5, "TUE"}, {6, "WED"}, {0, "THU"} };
int date[13] = { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };

string solution(int a, int b) {
	string answer = "";
	int sum = date[a - 1] + b;
	int idx = sum % 7;
	answer = day[idx];
	return answer;
}

int main(void) {
	int a = 5;
	int b = 24;
	string result = solution(a, b);
	cout << result << "\n";
	return 0;
}