#include "PerishableGoodsLot.h"
#include <iostream>

void PerishableGoodsLot::init(std::string name, int initial_qty, int day, int month, int year, int shelf_life) {
    CommodityLot::init(name, initial_qty, day, month, year);
    shelf_life_days = shelf_life;
}

void PerishableGoodsLot::nextDay() {
    if (shelf_life_days > 0) {
        shelf_life_days--;
        std::cout << "srok godnosti umenshen. ostalos: " << shelf_life_days << std::endl;
    }
    else {
        std::cout << "srok godnosti uzhe istek." << std::endl;
    }
}

void PerishableGoodsLot::sell(int quantity) {
    if (shelf_life_days <= 0) {
        std::cout << "  OSHIBKA: srok godnosti istek!" << std::endl;
        return;
    }
    CommodityLot::sell(quantity);
}

void PerishableGoodsLot::printRemainder() {
    CommodityLot::printRemainder();
    std::cout << "ostavshiysya srok godnosti: " << shelf_life_days << std::endl;
}

void PerishableGoodsLot::printFullReport() {
    CommodityLot::printFullReport();
    std::cout << "ostavshiysya srok godnosti: " << shelf_life_days << std::endl;
}