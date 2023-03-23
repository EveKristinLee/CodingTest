#include <string>
#include <vector>
#include <cmath>
#include <iostream>

using namespace std;

string solution(vector<int> numbers, string hand) {
	string answer = "";
	int leftHand = 10;
	int rightHand = 12;
	int disLeft = 0;
	int disRigh = 0;
	for (int i = 0; i < numbers.size(); i++) {
		if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
			answer.append("L");
			leftHand = numbers[i];
		}
		else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
			answer.append("R");
			rightHand = numbers[i];
		}
		//2, 5, 8, 0
		else {
			if (numbers[i] == 0) {
				numbers[i] = 11;
			}
			int tmpLeft = abs(leftHand - numbers[i]);
			int tmpRigh = abs(rightHand - numbers[i]);

			//키패드 거리 차이 = 3으로 나눈 몫(행) + 3으로 나눈 나머지(열)
			disLeft = (tmpLeft / 3) + (tmpLeft % 3);
			disRigh = (tmpRigh / 3) + (tmpRigh % 3);
			
			if (disLeft == disRigh) {
				if (hand == "right") {
					answer.append("R");
					rightHand = numbers[i];
				}
				else {
					answer.append("L");
					leftHand = numbers[i];
				}
			}
			else {
				if (disLeft < disRigh) {
					answer.append("L");
					leftHand = numbers[i];
				}
				else {
					answer.append("R");
					rightHand = numbers[i];
				}
			}
		}
	}
	return answer;
}

int main(void) {

	string sol = solution({ 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 }, "right");
	cout << sol << "\n";
	sol = solution({ 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 }, "left");
	cout << sol << "\n";
	sol = solution({ 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }, "right");
	cout << sol << "\n";
	return 0;
}