package com.gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.legacycode.Range;
import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        String[] names = {"foo", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "Aged Brie"};
        Integer[] sellIns = Range.get(0,10);
        Integer[] qualities = Range.get(-10,50);
        CombinationApprovals.verifyAllCombinations(this::gildedRoseWith, names, sellIns, qualities);
    }

    @Test
    public void conjured() {
        Approvals.verify(gildedRoseWith("Conjured", 8, 4));
    }

    protected String gildedRoseWith(String name, int sellIn, int quality) {
        GildedRose app = new GildedRose(new Item[]{new Item(name, sellIn, quality)});
        app.updateQuality();
        return app.toString();
    }

}
