#pragma once
#include <string>

class CommodityLot {
private:
    int initial_quantity;
    std::string item_name;
    int delivery_day, delivery_month, delivery_year;
    int sold_quantity;
    int written_off_quantity;

    int calculateRemainder();

public:
    void init(std::string name, int initial_qty, int day, int month, int year);
    void sell(int quantity = 1);
    void writeOff(int quantity = 1);
    void printRemainder();
    void printFullReport();
};

