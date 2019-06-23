package com.gildedrose;

class OtherProduct extends Category {

    @Override
    public void changeQuality(Item item) {
        if (shouldHaveBeenSold(item)) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 1);
        }
    }

    @Override
    public void changeSellIn(Item item) {
        decreaseSellIn(item);
    }
}
