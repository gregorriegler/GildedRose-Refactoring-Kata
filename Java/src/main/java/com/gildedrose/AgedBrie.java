package com.gildedrose;

class AgedBrie extends Product {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        if (shouldHaveBeenSold()) {
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
