package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = ProductFactory.createProduct(item);
            product.handle();
        }
    }

    private static class ProductFactory {
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
}

abstract class Product {
    public static void decreaseQuality(Item item) {
        if (item.quality == 0) return;
        addQuality(item, -1);
    }

    public static void increaseQuality(Item item, int by) {
        if (by < 0) return;
        if (item.quality + by >= 50) {
            item.quality = 50;
        } else {
            item.quality += by;
        }
    }

    public static void qualityToZero(Item item) {
        item.quality = item.quality - item.quality;
    }

    public static void addQuality(Item item, int i) {
        item.quality = item.quality + i;
    }

    public static void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public void handle() {
        changeQuality();
        changeSellIn();
    }

    public abstract void changeQuality();

    public abstract void changeSellIn();

}

class AgedBrie extends Product {
    private final Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void changeQuality() {
        increaseQuality(item, 1);

        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn(item);
    }
}

class ConcertPass extends Product {
    private final Item item;

    public ConcertPass(Item item) {
        this.item = item;
    }

    @Override
    public void changeQuality() {
        increaseQuality(item, 1);

        if (item.sellIn > 5 && item.sellIn < 11) {
            increaseQuality(item, 1);
        } else if (item.sellIn < 6) {
            increaseQuality(item, 2);
        }

        if (item.sellIn < 0) {
            qualityToZero(item);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn(item);
    }
}

class Legendary extends Product {
    private final Item item;

    public Legendary(Item item) {
        this.item = item;
    }

    @Override
    public void changeQuality() {
    }

    @Override
    public void changeSellIn() {
    }
}

class OtherProduct extends Product {
    private final Item item;

    public OtherProduct(Item item) {
        this.item = item;
    }

    @Override
    public void changeQuality() {
        decreaseQuality(item);

        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn(item);
    }
}