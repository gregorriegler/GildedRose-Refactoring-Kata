package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = ProductFactory.createProduct(item);
            product.handle(this);
        }
    }

    public void changeQualityOfOtherItem(Item item) {
        decreaseQuality(item);

        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    public void changeQualityOfConcertPasses(Item item) {
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

    public void changeQualityOfBrie(Item item) {
        increaseQuality(item, 1);

        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    public void changeQualityOfLegendary(Item item) {
    }

    public void changeSellInOfLegendary(Item item) {
    }

    public void changeSellInOfOtherItem(Item item) {
        decreaseSellIn(item);
    }

    public void changeSellInOfConcertPasses(Item item) {
        decreaseSellIn(item);
    }

    public void changeSellInOfBrie(Item item) {
        decreaseSellIn(item);
    }

    public void decreaseQuality(Item item) {
        if (item.quality == 0) return;
        addQuality(item, -1);
    }

    public void increaseQuality(Item item, int by) {
        if (by < 0) return;
        if (item.quality + by >= 50) {
            item.quality = 50;
        } else {
            item.quality += by;
        }
    }

    public void qualityToZero(Item item) {
        item.quality = item.quality - item.quality;
    }

    public void addQuality(Item item, int i) {
        item.quality = item.quality + i;
    }

    public void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
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

interface Product {
    void handle(GildedRose gildedRose);
}

class AgedBrie implements Product {
    private final Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfBrie(getItem());
        gildedRose.changeSellInOfBrie(getItem());
    }
}

class ConcertPass implements Product {
    private final Item item;

    public ConcertPass(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfConcertPasses(getItem());
        gildedRose.changeSellInOfConcertPasses(getItem());
    }
}

class Legendary implements Product {
    private final Item item;

    public Legendary(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfLegendary(getItem());
        gildedRose.changeSellInOfLegendary(getItem());
    }
}

class OtherProduct implements Product {
    private final Item item;

    public OtherProduct(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfOtherItem(getItem());
        gildedRose.changeSellInOfOtherItem(getItem());
    }
}