package com.gildedrose;

class ConcertPass extends Category {

    @Override
    public void changeQuality(Item item) {
        if (shouldHaveBeenSold(item)) {
            qualityToZero(item);
        } else if (lessThanDaysLeft(item, 6)) {
            increaseQuality(item, 3);
        } else if (daysLeftBetween(item, 5, 11)) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }

    @Override
    public void changeSellIn(Item item) {
        decreaseSellIn(item);
    }
}
