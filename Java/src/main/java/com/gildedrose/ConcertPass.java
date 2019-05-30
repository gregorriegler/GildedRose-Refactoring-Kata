package com.gildedrose;

class ConcertPass extends Product {

    public ConcertPass(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        if (shouldHaveBeenSold()) {
            qualityToZero();
        } else if (lessThanDaysLeft(6)) {
            increaseQuality(3);
        } else if (daysLeftBetween(5, 11)) {
            increaseQuality(2);
        } else {
            increaseQuality(1);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn();
    }
}
