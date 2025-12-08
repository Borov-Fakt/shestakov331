#include <iostream>
#include <string>
using namespace std;

class Car {
public:
	string name;
	int speed;

	Car(string names, int speeds);

	bool operator==(Car other);
	bool operator<(Car other);
	bool operator>(Car other);
};
