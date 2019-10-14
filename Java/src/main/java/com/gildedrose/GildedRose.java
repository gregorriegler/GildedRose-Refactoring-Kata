package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleItem(item);
        }
    }

    private void handleItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        } else {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                oneMoreTime(item);
            } else {
                oneMoreTime(item);
            }
        }
    }

    private void oneMoreTime(Item item) {
        if (item.quality > 0) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            } else {
                item.quality = item.quality - 1;
            }
        }

        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        } else {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    return;
                }
                item.quality = item.quality - 1;
            }
        }
    }

    @Override public String toString() {
        return Arrays.toString(items);
    }
}