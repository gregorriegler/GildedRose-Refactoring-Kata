package com.gildedrose;

abstract class Product {
    protected final Item item;

    Product(Item item) {
        this.item = item;
    }

    public void handle() {
        changeQuality();
        changeSellIn();
    }

    public abstract void changeQuality();

    public abstract void changeSellIn();

    protected boolean shouldHaveBeenSold() {
        return item.sellIn < 0;
    }

    protected boolean daysLeftBetween(int min, int max) {
        return moreThanDaysLeft(min) && lessThanDaysLeft(max);
    }

    protected boolean moreThanDaysLeft(int days) {
        return item.sellIn > days;
    }

    protected boolean lessThanDaysLeft(int days) {
        return item.sellIn < days;
    }

    protected void increaseQuality(int by) {
        addQuality(by);
    }

    protected void decreaseQuality(int by) {
        addQuality(by * -1);
    }

    private void addQuality(int i) {
        item.quality = item.quality + i;
        if(item.quality <= 0) item.quality = 0;
        if(item.quality >= 50) item.quality = 50;
    }

    protected void qualityToZero() {
        item.quality = item.quality - item.quality;
    }

    protected void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

}
