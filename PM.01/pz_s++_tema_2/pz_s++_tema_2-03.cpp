#include <iostream>
#include <cmath>

double Fun(double x) {
	double res = pow(cos(x + 5), 2);
	return res;
}

double run(double Sum) {
	double a = 0;

	std::cout << "vedite chislo:";
	std::cin >> a;

	double functiont = Fun(a);
	std::cout << "resultat " << functiont << std::endl;

	double newsum = Sum + functiont;
	std::cout << "suma resultatov " << newsum << std::endl;


	int number;
	std::cout << "prodolzim 1 = da 0 = net" << std::endl;
	std::cin >> number;

	if (number == 1) {
		return run(newsum);
	}
	else {
		return newsum;
	}

}

int main() {

	double allsum = run(0);

	std::cout << "koneh programi" << std::endl;

	std::cout << "vse resultati " << allsum << std::endl;

	return 0;

}


