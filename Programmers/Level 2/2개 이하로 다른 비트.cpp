//[참고] https://ansohxxn.github.io/programmers/148/
#include <iostream>
#include <vector>
#include <bitset>

using namespace std;

vector<long long> solution(vector<long long> numbers) {
	vector<long long> answer;
	for (int i = 0; i < numbers.size(); i++) {
		if (numbers[i] % 2 == 0) {
			answer.push_back(numbers[i] + 1);
		}
		else {
			long long bit = 1;
			while (1) {
				//연속된 1들 바로 앞에 0위치 찾기
				if ((numbers[i] & bit) == 0) {
					break;
				}
				bit <<= 1; //bit *= 2;
			}
			//하나 밑 자리수 비트 값 더하기(올림으로 01 -> 10)
			bit >>= 1; //bit /= 2;
			answer.push_back(numbers[i] + bit);
		}
	}
	return answer;
}

int main(void) {
	vector<long long> numbers = {2, 7 };
	vector<long long> result = solution(numbers);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}