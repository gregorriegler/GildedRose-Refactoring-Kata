package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void doesNotChangeName() {
        GildedRose rose = gildedRoseOf(SOME_ITEM, 0, 0);

        rose.updateQuality();

        assertEquals(SOME_ITEM, rose.items[0].name);
    }

    @Test
    public void someItem_sellInDecreases() {
        GildedRose rose = gildedRoseOf(SOME_ITEM, 10, 0);

        rose.updateQuality();

        assertEquals(9, rose.items[0].sellIn);
    }

    @Test
    public void someItem_qualityDoesNotDropBelow0() {
        GildedRose rose = gildedRoseOf(SOME_ITEM, 0, 0);

        rose.updateQuality();

        assertEquals(0, rose.items[0].quality);
    }

    @Test
    public void someItem_qualityDecreasesBy1() {
        GildedRose rose = gildedRoseOf(SOME_ITEM, 1, 5);

        rose.updateQuality();

        assertEquals(4, rose.items[0].quality);
    }

    @Test
    public void someItem_conjured_qualityDecreasesBy2() {
        GildedRose rose = gildedRoseOf(SOME_ITEM, -1, 5);

        rose.updateQuality();

        assertEquals(3, rose.items[0].quality);
    }

    @Test
    public void legendary_sellInStaysTheSame() {
        GildedRose rose = gildedRoseOf(LEGENDARY, 5, 20);

        rose.updateQuality();

        assertEquals(5, rose.items[0].sellIn);
    }

    @Test
    public void legendary_qualityStaysTheSame() {
        GildedRose rose = gildedRoseOf(LEGENDARY, 5, 10);

        rose.updateQuality();

        assertEquals(10, rose.items[0].quality);
    }

    @Test
    public void legendary_shouldHaveBeenSold_qualityStaysTheSame() {
        GildedRose rose = gildedRoseOf(LEGENDARY, -1, 50);

        rose.updateQuality();

        assertEquals(50, rose.items[0].quality);
    }

    @Test
    public void agedBrie_shouldHaveBeenSold_qualityLessThan50_qualityIncreases() {
        GildedRose rose = gildedRoseOf(AGED_BRIE, -1, 10);

        rose.updateQuality();

        assertEquals(12, rose.items[0].quality);
    }

    @Test
    public void agedBrie_shouldHaveBeenSold_qualityWontPass50() {
        GildedRose rose = gildedRoseOf(AGED_BRIE, -1, 50);

        rose.updateQuality();

        assertEquals(50, rose.items[0].quality);
    }

    @Test
    public void agedBrie_qualityIncreases() {
        GildedRose rose = gildedRoseOf(AGED_BRIE, 2, 30);

        rose.updateQuality();

        assertEquals(31, rose.items[0].quality);
    }

    @Test
    public void concertPasses_sellInMoreThan10Days_qualityIncreases() {
        GildedRose rose = gildedRoseOf(CONCERT_PASS, 11, 30);

        rose.updateQuality();

        assertEquals(31, rose.items[0].quality);
    }

    @Test
    public void concertPasses_sellInLessThan11Days_QualityAt50_qualityStaysSame() {
        GildedRose rose = gildedRoseOf(CONCERT_PASS, 10, 50);

        rose.updateQuality();

        assertEquals(50, rose.items[0].quality);
    }

    @Test
    public void concertPasses_sellInLessThan11Days_QualityBelow50_qualityIncreasesByTwo() {
        GildedRose rose = gildedRoseOf(CONCERT_PASS, 10, 30);

        rose.updateQuality();

        assertEquals(32, rose.items[0].quality);
    }

    @Test
    public void concertPasses_sellInLessThan6Days_qualityIncreasesByThree() {
        GildedRose rose = gildedRoseOf(CONCERT_PASS, 5, 30);

        rose.updateQuality();

        assertEquals(33, rose.items[0].quality);
    }

    @Test
    public void concertPasses_shouldHaveBeenSold_qualityGoesTo0() {
        GildedRose rose = gildedRoseOf(CONCERT_PASS, -1, 50);

        rose.updateQuality();

        assertEquals(0, rose.items[0].quality);
    }

    public static final String SOME_ITEM = "foo";
    public static final String LEGENDARY = "Sulfuras, Hand of Ragnaros";
    public static final String CONCERT_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";

    private GildedRose gildedRoseOf(String aged_brie, int sellIn, int quality) {
        return new GildedRose(anItemOf(aged_brie, sellIn, quality));
    }

    private Item anItemOf(String foo, int sellIn, int quality) {
        return new Item(foo, sellIn, quality);
    }

}