package edu.insightr.gildedrose;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Inventory {

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            items = new Item[]{
                    new Item(0,"+5 Dexterity Vest", 10, 20, simpleDateFormat.parse("22/06/2006")),
                    new Item(1,"Aged Brie", 10, 0, simpleDateFormat.parse("26/06/2006")),
                    new Item(2,"Elixir of the Mongoose", 5, 7, simpleDateFormat.parse("23/06/2006")),
                    new Item(3,"Sulfuras, Hand of Ragnaros", 20, 80, simpleDateFormat.parse("22/06/2006")),
                    new Item(4,"Backstage passes to a TAFKAL80ETC concert", 5, 20, simpleDateFormat.parse("22/06/2006")),
                    new Item(5,"Conjured Mana Cake", 5, 6, simpleDateFormat.parse("23/06/2006"))
            };
        }catch (ParseException e) {

            e.printStackTrace();

        }
    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName() != "Aged Brie"
                    && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].getQuality() > 0) {
                    if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);

                    if (items[i].getName() == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }

            if (items[i].getSellIn() < 0) {
                if (items[i].getName() != "Aged Brie") {
                    if (items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(items[i].getQuality() - 1);
                            }
                        }
                    } else {
                        items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
