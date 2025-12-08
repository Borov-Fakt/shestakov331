#include <iostream>
using namespace std;

void decrementValue(int x) {
	x -= 1;
	cout << x << endl;

}

void decrementPointer(int *x) {
	cout << *x << endl;
}

int main() {
	setlocale(LC_ALL, "Russian");
	int a = 5;
	int b = 5;

	decrementValue(a);
	decrementPointer(&b);


	return 0;

}
