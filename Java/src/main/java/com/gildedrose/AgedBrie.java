package com.gildedrose;

class AgedBrie extends Category {

    @Override
    public void changeQuality(Item item) {
        if (shouldHaveBeenSold(item)) {
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
