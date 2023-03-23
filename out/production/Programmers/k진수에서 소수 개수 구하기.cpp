#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

bool isPrime(long long n) {
	if (n < 2) {
		return false;
	}
	long long a = (long long)sqrt(n);
	for (long long i = 2; i <= a; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

string change(int number, int t) {
	string tmp = "";
	while (number > 0) {
		tmp += to_string(number % t);
		number /= t;
	}
	reverse(tmp.begin(), tmp.end());
	cout << tmp << "\n";
	return tmp;
}

int solution(int n, int k) {
	int answer = 0;
	string ch = change(n, k);
	string num = "";
	for (int i = 0; i < ch.size(); i++) {
		if (ch[i] != '0') {
			num += ch[i];
		}
		if (ch[i] == '0' || i == (ch.size() - 1)) {
			if (num != "" && isPrime(stoi(num))) {
				cout << "num : " << num << "\n";
				answer++;
			}
			num = "";
		}
	}
	return answer;
}

int main(void) {
	int n = 110011;
	int k = 10;
	int result = solution(n, k);
	cout << result << "\n";
	return 0;
}