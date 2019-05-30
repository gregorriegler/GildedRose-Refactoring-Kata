package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isAgedBrie(item)) {
                AgedBrie agedBrie = new AgedBrie(item);
                agedBrie.handle(this);
            } else if (isConcertPasses(item)) {
                ConcertPass concertPass = new ConcertPass(item);
                concertPass.handle(this);
            } else if (isLegendary(item)) {
                Legendary legendary = new Legendary(item);
                legendary.handle(this);
            } else {
                OtherProduct otherProduct = new OtherProduct(item);
                otherProduct.handle(this);
            }
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

}

class AgedBrie {
    private final Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfBrie(getItem());
        gildedRose.changeSellInOfBrie(getItem());
    }
}

class ConcertPass {
    private final Item item;

    public ConcertPass(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfConcertPasses(getItem());
        gildedRose.changeSellInOfConcertPasses(getItem());
    }
}

class Legendary {
    private final Item item;

    public Legendary(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfLegendary(getItem());
        gildedRose.changeSellInOfLegendary(getItem());
    }
}

class OtherProduct {
    private final Item item;

    public OtherProduct(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void handle(GildedRose gildedRose) {
        gildedRose.changeQualityOfOtherItem(getItem());
        gildedRose.changeSellInOfOtherItem(getItem());
    }
}