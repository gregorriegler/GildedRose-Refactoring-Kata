package com.gildedrose;

public class ProductFactory {
    private ProductFactory() {
    }

    public static Product createProduct(Item item) {
        if (isAgedBrie(item)) {
            return new AgedBrie(item);
        } else if (isConcertPasses(item)) {
            return new ConcertPass(item);
        } else if (isLegendary(item)) {
            return new Legendary(item);
        } else if (isConjured(item)) {
            return new Conjured(item);
        } else {
            return new OtherProduct(item);
        }
    }

    private static boolean isConjured(Item item) {
        return item.name.equals("Conjured Mana Cake");
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
