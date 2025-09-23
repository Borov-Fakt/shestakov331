#include "CommodityLot.h"
#include <iostream>

void CommodityLot::init(std::string name, int initial_qty, int day, int month, int year) {
    item_name = name;
    initial_quantity = initial_qty;
    delivery_day = day;
    delivery_month = month;
    delivery_year = year;
    sold_quantity = 0;
    written_off_quantity = 0;
}

void CommodityLot::sell(int quantity) {
    sold_quantity += quantity;
}

void CommodityLot::writeOff(int quantity) {
    written_off_quantity += quantity;
}

void CommodityLot::printRemainder() {
    std::cout << "ostatok tovara '" << item_name << calculateRemainder() << std::endl;
}

void CommodityLot::printFullReport() {
    std::cout << "  naimenovanie tovara: " << item_name << std::endl;
    std::cout << "  data postavki: " << delivery_day << delivery_month << delivery_year << std::endl;
    std::cout << "  nachalnoe kolichestvo: " << initial_quantity << std::endl;
    std::cout << "  prodano: " << sold_quantity << std::endl;
    std::cout << "  spisano: " << written_off_quantity << std::endl;
    std::cout << "  tekuschiy ostatok: " << calculateRemainder() << std::endl;
}

int CommodityLot::calculateRemainder() {
    return initial_quantity - sold_quantity - written_off_quantity;
}
