#include <iostream>

using namespace std;

//5kg ¥���� �ִ��� ���� ��� �ϹǷ�
//ó���� ���� 5�� ����� �ƴϸ� 5�� ����� �ɶ����� -3
//-3�� �ϴٰ� ������ �ȴٸ� -1���
int main(void) {
	int num3 = 0;
	int num5 = 0;
	int N;
	cin >> N;
	while (N % 5 != 0 && N >= 0) {
		N -= 3;
		num3++;
	}
	if (N < 0) {
		cout << "-1" << "\n";
	}
	else {
		num5 = N / 5;
		cout << num3 + num5 << "\n";
	}
	return 0;
}