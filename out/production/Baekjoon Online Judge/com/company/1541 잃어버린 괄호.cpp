#include <iostream>
#include <string>

using namespace std;

string input;
int result = 0;
string num = "";
bool isMinus = false;

int main(void) {
	cin >> input;

	for (int i = 0; i <= input.size(); i++) {
		//i == input.size() : ���������ڵ� ����������ϱ⶧����
		if (input[i] == '-' || input[i] == '+' || i == input.size()) {
			if (isMinus) {
				result -= stoi(num);
				num = "";
			}
			else {
				result += stoi(num);
				num = "";
			}
		}
		else {
			num += input[i];
		}
		//ù��° ���ڸ� result += num����� �ؼ� ���⿡ ��
		if (input[i] == '-') {
			isMinus = true;
		}
	}
	cout << result << "\n";
	return 0;
}