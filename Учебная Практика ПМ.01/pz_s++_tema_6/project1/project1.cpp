#include <iostream>
using namespace std;

void svapArr(int *arr, int number) {

	for (int i = 0; i < number; i += 2) {
		if (i + 1 < number) {
			int a = arr[i];
			arr[i] = arr[i + 1];
			arr[i + 1] = a;
		}
	}
}

int main() {
	setlocale(LC_ALL, "Russian");
	const int array = 6;

	int *arrs = new int[array];

	for (int i = 0; i < array; i++) {
		cout << "¬ведите " << i + 1 << " элемент: ";
		cin >> arrs[i];
	}

	cout << "исходный массив: ";
	for (int i = 0; i < array; i++) {
		cout << arrs[i] << " ";
	}

	svapArr(arrs, array);

	cout << "\nизмененный массив: ";
	for (int i = 0; i < array; i++) {
		cout << arrs[i] << " ";
	}

	return 0;

}
