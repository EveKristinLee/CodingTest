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
		//i == input.size() : 마지막숫자도 연산해줘야하기때문에
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
		//첫번째 숫자를 result += num해줘야 해서 여기에 둠
		if (input[i] == '-') {
			isMinus = true;
		}
	}
	cout << result << "\n";
	return 0;
}