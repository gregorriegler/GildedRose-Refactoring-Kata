package com.gildedrose;

class Conjured extends Product {

    public Conjured(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        if (shouldHaveBeenSold()) {
            decreaseQuality(4);
        } else {
            decreaseQuality(2);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn();
    }
}
