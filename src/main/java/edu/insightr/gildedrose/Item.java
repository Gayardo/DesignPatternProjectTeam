package edu.insightr.gildedrose;

import java.util.Date;
import java.text.SimpleDateFormat;
import  java.text.DateFormat;


public class Item {

    private String name;
    private int sellIn;
    private int quality;
    private Date dateBuy;
    private String stringBuy;
    private Date dateSell;
    private String stringSell;
    private Date dateCreation;
    private String stringCreation;
    private  int id;

    public Item(){

    }
    public Item(int id, String name, int sellIn, int quality,Date dateCreation) {
        super();
        this.id=id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.dateCreation=dateCreation;


        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        this.stringBuy="acheté";
        this.stringSell="non vendu";
        this.stringCreation=formater.format(dateCreation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getStringBuy() {
        return stringBuy;
    }

    public void setStringBuy(String stringBuy) {
        this.stringBuy = stringBuy;
    }

    public Date getDateSell() {
        return dateSell;
    }

    public void setDateSell(Date dateSell) {
        this.dateSell = dateSell;
    }

    public String getStringSell() {
        return stringSell;
    }

    public void setStringSell(String stringSell) {
        this.stringSell = stringSell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStringCreation() {
        return stringCreation;
    }

    public void setStringCreation(String stringCreation) {
        this.stringCreation = stringCreation;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }
}