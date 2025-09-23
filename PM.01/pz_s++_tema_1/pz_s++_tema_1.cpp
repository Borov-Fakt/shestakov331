#include <iostream>
#include <cmath>

int main() {
	double a, b;

	std::cout << "vedite chislo 1:";
	std::cin >> a;
	std::cout << "vedite chislo 2:";
	std::cin >> b;

	double i;
	if (a > b) {
		i = a;
	}
	else {
		i = b;
	}

	double x = pow(cos(i + 5), 2);
	std::cout << "rezultat " << x;

	return 0;
}