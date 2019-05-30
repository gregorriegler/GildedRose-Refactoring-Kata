package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isAgedBrie(item)) {
                increaseQuality(item, 1);

                if (isConcertPasses(item)) {
                    if (item.sellIn > 5 && item.sellIn < 11) {
                        increaseQuality(item, 1);
                    } else if (item.sellIn < 6) {
                        increaseQuality(item, 2);
                    }
                }

            } else if (isConcertPasses(item)) {
                increaseQuality(item, 1);

                if (isConcertPasses(item)) {
                    if (item.sellIn > 5 && item.sellIn < 11) {
                        increaseQuality(item, 1);
                    } else if (item.sellIn < 6) {
                        increaseQuality(item, 2);
                    }
                }

            } else {
                if (item.quality > 0) {
                    decreaseQualityOfNonLegendary(item);
                }
            }

            if (item.sellIn < 0) {
                if (isAgedBrie(item)) {
                    increaseQuality(item, 1);
                } else if (isConcertPasses(item)) {
                    qualityToZero(item);
                } else if (item.quality > 0) {
                    decreaseQualityOfNonLegendary(item);
                }
            }

            decreaseSellInOfNonLegendary(item);
        }
    }

    private void increaseQuality(Item item, int by) {
        if (by < 0) return;
        if (item.quality + by >= 50) {
            item.quality = 50;
        } else {
            item.quality += by;
        }
    }

    private void decreaseSellInOfNonLegendary(Item item) {
        if (!isLegendary(item)) {
            decreaseSellIn(item);
        }
    }

    private void decreaseQualityOfNonLegendary(Item item) {
        if (!isLegendary(item)) {
            addQuality(item, -1);
        }
    }


    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isConcertPasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void qualityToZero(Item item) {
        item.quality = item.quality - item.quality;
    }

    private void addQuality(Item item, int i) {
        item.quality = item.quality + i;
    }
}