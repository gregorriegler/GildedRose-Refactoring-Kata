package com.gildedrose;

class Conjured extends Category {

    @Override
    public void changeQuality(Item item) {
        if (shouldHaveBeenSold(item)) {
            decreaseQuality(item, 4);
        } else {
            decreaseQuality(item, 2);
        }
    }

    @Override
    public void changeSellIn(Item item) {
        decreaseSellIn(item);
    }
}
