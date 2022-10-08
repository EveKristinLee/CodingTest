#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
int arr[100001];

void binarySearch(int key) {
	int left = 0;
	int right = n - 1;
	int mid;

	while (left <= right) {
		mid = (left + right) / 2;
		if (arr[mid] == key) {
			cout << "1\n";
			return;
		}
		else if (arr[mid] > key) {
			right = mid - 1;
		}
		else {
			left = mid + 1;
		}
	}
	cout << "0\n";
	return;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr[i] = a;
	}
	sort(arr, arr+n);
	
	cin >> m;
	for (int i = 0; i < m; i++) {
		int num;
		cin >> num;
		binarySearch(num);
	}

	return 0;
}