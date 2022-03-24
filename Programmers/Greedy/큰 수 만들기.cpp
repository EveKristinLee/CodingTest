//[Âü°í] https://velog.io/@woga1999/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%81%B0-%EC%88%98-%EB%A7%8C%EB%93%A4%EA%B8%B0C

#include <iostream>
#include <string>
#include <vector>

using namespace std;

string solution(string number, int k) {
	string answer = "";
	int idx = -1;
	for (int i = 0; i < number.size() - k; i++) {
		char maxChar = ' ';
		for (int j = idx + 1; j <= k + i; j++) {
			if (maxChar < number[j]) {
				maxChar = number[j];
				idx = j;
			}
		}
		answer += maxChar;
	}
	return answer;
}

int main(void) {
	string number = "1924";
	int k = 2;
	string result = solution(number, k);
	cout << result << "\n";
	return 0;
}
