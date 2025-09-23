#pragma once
#include "CommodityLot.h"

class PerishableGoodsLot : public CommodityLot {
private:
    int shelf_life_days;

public:
    void init(std::string name, int initial_qty, int day, int month, int year, int shelf_life);
    void nextDay();
    void sell(int quantity = 1) override;
    void printRemainder() override;
    void printFullReport() override;
};
