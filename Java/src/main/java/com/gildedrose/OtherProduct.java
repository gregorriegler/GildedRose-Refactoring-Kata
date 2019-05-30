package com.gildedrose;

class OtherProduct extends Product {

    public OtherProduct(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        if (shouldHaveBeenSold()) {
            decreaseQuality(2);
        } else {
            decreaseQuality(1);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn();
    }
}
