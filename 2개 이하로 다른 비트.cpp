//[����] https://ansohxxn.github.io/programmers/148/
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
				//���ӵ� 1�� �ٷ� �տ� 0��ġ ã��
				if ((numbers[i] & bit) == 0) {
					break;
				}
				bit <<= 1; //bit *= 2;
			}
			//�ϳ� �� �ڸ��� ��Ʈ �� ���ϱ�(�ø����� 01 -> 10)
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