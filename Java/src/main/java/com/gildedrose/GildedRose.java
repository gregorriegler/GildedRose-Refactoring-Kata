package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isAgedBrie(item)) {
                handleBrie(new AgedBrie(item));
            } else if (isConcertPasses(item)) {
                handleConcertPasses(new ConcertPass(item));
            } else if (isLegendary(item)) {
                handleLegendary(new Legendary(item));
            } else {
                handleOtherItems(new OtherProduct(item));
            }
        }
    }

    private void handleOtherItems(OtherProduct otherProduct) {
        changeQualityOfOtherItem(otherProduct.getItem());
        changeSellInOfOtherItem(otherProduct.getItem());
    }

    private void handleLegendary(Legendary legendary) {
        changeQualityOfLegendary(legendary.getItem());
        changeSellInOfLegendary(legendary.getItem());
    }

    private void handleConcertPasses(ConcertPass concertPass) {
        changeQualityOfConcertPasses(concertPass.getItem());
        changeSellInOfConcertPasses(concertPass.getItem());
    }

    private void handleBrie(AgedBrie agedBrie) {
        changeQualityOfBrie(agedBrie.getItem());
        changeSellInOfBrie(agedBrie.getItem());
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

    private void changeQualityOfOtherItem(Item item) {
        decreaseQuality(item);

        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private void changeQualityOfConcertPasses(Item item) {
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

    private void changeQualityOfBrie(Item item) {
        increaseQuality(item, 1);

        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    private void changeQualityOfLegendary(Item item) {
    }

    private void changeSellInOfLegendary(Item item) {
    }

    private void changeSellInOfOtherItem(Item item) {
        decreaseSellIn(item);
    }

    private void changeSellInOfConcertPasses(Item item) {
        decreaseSellIn(item);
    }

    private void changeSellInOfBrie(Item item) {
        decreaseSellIn(item);
    }

    private void decreaseQuality(Item item) {
        if (item.quality == 0) return;
        addQuality(item, -1);
    }

    private void increaseQuality(Item item, int by) {
        if (by < 0) return;
        if (item.quality + by >= 50) {
            item.quality = 50;
        } else {
            item.quality += by;
        }
    }

    private void qualityToZero(Item item) {
        item.quality = item.quality - item.quality;
    }

    private void addQuality(Item item, int i) {
        item.quality = item.quality + i;
    }

    private void decreaseSellIn(Item item) {
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
}

class ConcertPass {
    private final Item item;

    public ConcertPass(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
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
}

class OtherProduct {
    private final Item item;

    public OtherProduct(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}