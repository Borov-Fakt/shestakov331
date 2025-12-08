#include <iostream>
#include "Car.h"
#include <string>
using namespace std;

Car::Car(string names, int speeds) {
		name = names;
		speed = speeds;
}

bool Car::operator==( Car other)
{
	if (name == other.name && speed == other.speed) {
		return true;
	}
	else
	{
		return false;
	}
}

bool Car::operator<( Car other)
{
	return speed < other.speed;
}

bool Car::operator>(Car other)
{
	return speed > other.speed;
}