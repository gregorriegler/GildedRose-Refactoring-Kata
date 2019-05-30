package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        addQuality(item, -1);
                    }
                }
            } else {
                if (item.quality < 50) {
                    addQuality(item, 1);

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn > 5 && item.sellIn < 11) {
                            addQuality(item, 1);
                        } else if (item.sellIn < 6) {
                            addQuality(item, 2);
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                decreaseSellIn(item);
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                                addQuality(item, -1);
                            }
                        }
                    } else {
                        qualityToZero(item);
                    }
                } else {
                    if (item.quality < 50) {
                        addQuality(item, 1);
                    }
                }
            }
        }
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