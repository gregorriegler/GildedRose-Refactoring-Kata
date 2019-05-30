package com.gildedrose;

class ProductFactory {
    public ProductFactory() {
    }

    public static Product createProduct(Item item) {
        if (isAgedBrie(item)) {
            return new AgedBrie(item);
        } else if (isConcertPasses(item)) {
            return new ConcertPass(item);
        } else if (isLegendary(item)) {
            return new Legendary(item);
        } else {
            return new OtherProduct(item);
        }
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static boolean isConcertPasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private static boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}
