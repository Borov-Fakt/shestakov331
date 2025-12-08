#include <iostream>
#include <string>
#include "Car.h"
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");
	Car one("Ford", 100);
	Car two("BMW", 300);
	Car oneClone("Ford", 100);

	if (one == oneClone) {
		cout << "равны" << endl;
	}

	if (one < two) {
		cout << "Ford медление BMW" << endl;
	}

	if (two > one) {
		cout << "BMW быстрее Ford" << endl;
	}

	return 0;
}