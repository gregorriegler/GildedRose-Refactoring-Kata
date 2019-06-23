package com.gildedrose;

public class CategoryFactory {
    private CategoryFactory() {
    }

    public static Category createCategory(Item item) {
        if (isAgedBrie(item)) {
            return new AgedBrie();
        } else if (isConcertPasses(item)) {
            return new ConcertPass();
        } else if (isLegendary(item)) {
            return new Legendary();
        } else if (isConjured(item)) {
            return new Conjured();
        } else {
            return new OtherProduct();
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
