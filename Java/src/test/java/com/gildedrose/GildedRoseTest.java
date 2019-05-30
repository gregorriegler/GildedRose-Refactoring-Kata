package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void doesNotChangeName() {
        GildedRose app = gildedRoseOf(SOME_ITEM, 0, 0);

        app.updateQuality();

        assertEquals(SOME_ITEM, app.items[0].name);
    }

    @Test
    public void someItem_sellInDecreases() {
        GildedRose app = gildedRoseOf(SOME_ITEM, 10, 0);

        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void someItem_qualityDoesNotDropBelow0() {
        GildedRose app = gildedRoseOf(SOME_ITEM, 0, 0);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void someItem_qualityDecreasesBy1() {
        GildedRose app = gildedRoseOf(SOME_ITEM, 1, 5);

        app.updateQuality();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void someItem_conjured_qualityDecreasesBy2() {
        GildedRose app = gildedRoseOf(SOME_ITEM, -1, 5);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void legendary_sellInStaysTheSame() {
        GildedRose app = gildedRoseOf(LEGENDARY, 5, 20);

        app.updateQuality();

        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    public void legendary_qualityStaysTheSame() {
        GildedRose app = gildedRoseOf(LEGENDARY, 5, 10);

        app.updateQuality();

        assertEquals(10, app.items[0].quality);
    }

    @Test
    public void legendary_shouldHaveBeenSold_qualityStaysTheSame() {
        GildedRose app = gildedRoseOf(LEGENDARY, -1, 50);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void agedBrie_shouldHaveBeenSold_qualityLessThan50_qualityIncreases() {
        GildedRose app = gildedRoseOf(AGED_BRIE, -1, 10);

        app.updateQuality();

        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void agedBrie_shouldHaveBeenSold_qualityWontPass50() {
        GildedRose app = gildedRoseOf(AGED_BRIE, -1, 50);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void agedBrie_qualityIncreases() {
        GildedRose app = gildedRoseOf(AGED_BRIE, 2, 30);

        app.updateQuality();

        assertEquals(31, app.items[0].quality);
    }

    @Test
    public void concertPasses_sellInMoreThan10Days_qualityIncreases() {
        GildedRose app = gildedRoseOf(CONCERT_PASS, 11, 30);

        app.updateQuality();

        assertEquals(31, app.items[0].quality);
    }

    @Test
    public void concertPasses_sellInLessThan11Days_QualityAt50_qualityStaysSame() {
        GildedRose app = gildedRoseOf(CONCERT_PASS, 10, 50);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void concertPasses_sellInLessThan11Days_QualityBelow50_qualityIncreasesByTwo() {
        GildedRose app = gildedRoseOf(CONCERT_PASS, 10, 30);

        app.updateQuality();

        assertEquals(32, app.items[0].quality);
    }

    @Test
    public void concertPasses_sellInLessThan6Days_qualityIncreasesByThree() {
        GildedRose app = gildedRoseOf(CONCERT_PASS, 5, 30);

        app.updateQuality();

        assertEquals(33, app.items[0].quality);
    }

    @Test
    public void concertPasses_shouldHaveBeenSold_qualityGoesTo0() {
        GildedRose app = gildedRoseOf(CONCERT_PASS, -1, 50);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    public static final String SOME_ITEM = "foo";
    public static final String LEGENDARY = "Sulfuras, Hand of Ragnaros";
    public static final String CONCERT_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";

    private GildedRose gildedRoseOf(String aged_brie, int sellIn, int quality) {
        return new GildedRose(
            itemsList(anItemOf(aged_brie, sellIn, quality))
        );
    }

    private Item[] itemsList(Item... items) {
        return items;
    }

    private Item anItemOf(String foo, int sellIn, int quality) {
        return new Item(foo, sellIn, quality);
    }

}