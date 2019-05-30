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

    protected void decreaseQuality() {
        if (item.quality == 0) return;
        addQuality(-1);
    }

    protected void increaseQuality(int by) {
        if (by < 0) return;
        if (item.quality + by >= 50) {
            item.quality = 50;
        } else {
            item.quality += by;
        }
    }

    protected void qualityToZero() {
        item.quality = item.quality - item.quality;
    }

    protected void addQuality(int i) {
        item.quality = item.quality + i;
    }

    protected void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

}

class AgedBrie extends Product {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        increaseQuality(1);

        if (item.sellIn < 0) {
            increaseQuality(1);
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn();
    }
}

class ConcertPass extends Product {

    public ConcertPass(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        increaseQuality(1);

        if (item.sellIn > 5 && item.sellIn < 11) {
            increaseQuality(1);
        } else if (item.sellIn < 6) {
            increaseQuality(2);
        }

        if (item.sellIn < 0) {
            qualityToZero();
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn();
    }
}

class Legendary extends Product {

    public Legendary(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
    }

    @Override
    public void changeSellIn() {
    }
}

class OtherProduct extends Product {

    public OtherProduct(Item item) {
        super(item);
    }

    @Override
    public void changeQuality() {
        decreaseQuality();

        if (item.sellIn < 0) {
            decreaseQuality();
        }
    }

    @Override
    public void changeSellIn() {
        decreaseSellIn();
    }
}