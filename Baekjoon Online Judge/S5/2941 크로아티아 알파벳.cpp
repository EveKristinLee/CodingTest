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
			//sentence.find() : �ش� ���ڿ��� ã�� ��ġ�� �˾Ƴ�
			//sentence.replace(a, b, c) : 
			//a - ���ڿ����� �ش� ���ڸ� ã�� ��ġ ��ȯ 
			//b - ã�� ������ ����
			//c - �ٲ� ����
			idx = word.find(alpha[i]);
			//ã�� ������ ���� ��� npos��ȯ
			if (idx == string::npos) {
				break;
			}
			word.replace(idx, alpha[i].length(), "a");
		}
	}

	cout << word.length() << "\n";

	return 0;
}