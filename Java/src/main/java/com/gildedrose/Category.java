package com.gildedrose;

abstract class Category {

    public void handle(Item item) {
        changeQuality(item);
        changeSellIn(item);
    }

    public abstract void changeQuality(Item item);

    public abstract void changeSellIn(Item item);

    protected boolean shouldHaveBeenSold(Item item) {
        return lessThanDaysLeft(item, 0);
    }

    protected boolean daysLeftBetween(Item item, int min, int max) {
        return moreThanDaysLeft(item, min) && lessThanDaysLeft(item, max);
    }

    protected boolean moreThanDaysLeft(Item item, int days) {
        return item.sellIn > days;
    }

    protected boolean lessThanDaysLeft(Item item, int days) {
        return item.sellIn < days;
    }

    protected void increaseQuality(Item item, int by) {
        addQuality(item, by);
    }

    protected void decreaseQuality(Item item, int by) {
        increaseQuality(item, by * -1);
    }

    private void addQuality(Item item, int i) {
        item.quality = item.quality + i;
        if(item.quality <= 0) item.quality = 0;
        if(item.quality >= 50) item.quality = 50;
    }

    protected void qualityToZero(Item item) {
        item.quality = item.quality - item.quality;
    }

    protected void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

}
