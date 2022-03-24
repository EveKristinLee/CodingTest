#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main(void) {
	vector<string> alpha = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };
	string word;
	cin >> word;
	int idx;
	for (int i = 0; i < alpha.size(); i++) {
		while (true) {
			//sentence.find() : 해당 문자열을 찾아 위치를 알아냄
			//sentence.replace(a, b, c) : 
			//a - 문자열에서 해당 문자를 찾아 위치 반환 
			//b - 찾은 문자의 길이
			//c - 바꿀 문자
			idx = word.find(alpha[i]);
			//찾는 내용이 없을 경우 npos반환
			if (idx == string::npos) {
				break;
			}
			word.replace(idx, alpha[i].length(), "a");
		}
	}

	cout << word.length() << "\n";

	return 0;
}