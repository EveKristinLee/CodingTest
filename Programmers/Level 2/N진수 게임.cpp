#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

string number = "0123456789ABCDEF";

string change(int n, int num) {
	string result = "";
	if (num == 0) {
		return "0";
	}
	while (num > 0) {
		result += number[num % n];
		num /= n;
	}
	reverse(result.begin(), result.end());
	return result;
}

string solution(int n, int t, int m, int p) {
	string answer = "";
	string tmp = "";
	int num = 0;
	while (tmp.size() <= t * m) {
		tmp += change(n, num++);
		cout << tmp << "\n";
	}
	for (int i = p-1; i < t * m; i += m) {
		answer += tmp[i];
	}
	return answer;
}

int main(void) {
	int n = 16;
	int t = 16;
	int m = 2;
	int p = 2;
	string result = solution(n, t, m, p);
	cout << result << "\n";

	return 0;
}