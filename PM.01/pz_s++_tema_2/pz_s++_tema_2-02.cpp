#include <iostream>
#include <cmath>

double Fun(double x) {
	double res = pow(cos(x + 5), 2);
	return res;
}

void run() {
	int number = 0;


	do {
		double a = 0;

		std::cout << "vedite chislo:";
		std::cin >> a;

		double functiont = Fun(a);
		std::cout << "resultat " << functiont << std::endl;
		std::cout << "prodolzim 1 = da 0 = net" << std::endl;
		std::cin >> number;
	} while (number == 1);
}
int main() {

	run();

	std::cout << "koneh programi" << std::endl;

	return 0;

}

