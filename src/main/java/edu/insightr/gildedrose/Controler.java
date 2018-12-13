package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import  javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Date;
import  javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import  javafx.scene.Scene;


public class Controler implements Initializable {

    @FXML
    private TableView<Item> items;

    @FXML
    private TableColumn<Item,String> name;
    @FXML
    private TableColumn<Item,Integer> sellIn;
    @FXML
    private TableColumn<Item,Integer> quality;
    @FXML
    private TableColumn<Item,Integer> dateColBuy;
    @FXML
    private TableColumn<Item,Integer> idCol;
    @FXML
    private TableColumn<Item,Integer> colDateSell;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label number;

    @FXML
    private Pane monPane;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private TextField sellInInput;
    @FXML
    private Button btnAdd;
    @FXML
    private BarChart<?, ?> sellChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private BarChart<?, ?> dateChart;
    @FXML
    private CategoryAxis xa;
    @FXML
    private NumberAxis ya;


    public Inventory inventory;

    public  static ObservableList<Item> liste;

    int [] numberOfKind ;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

       initTable();

       loadData();


       countKinds();
       for(int i=0;i<6;i++)
       {
        System.out.println(numberOfKind[i]);
       }
        salut();
    }

    private void initTable() {
        initCols();
    }
    private void initCols(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellIn.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        quality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        dateColBuy.setCellValueFactory(new PropertyValueFactory<>("stringBuy"));
        colDateSell.setCellValueFactory(new PropertyValueFactory<>("stringSell"));

    }
    private void loadData()
    {
        liste = FXCollections.observableArrayList();

        inventory =new Inventory();
        for(int i=0; i<inventory.getItems().length;i++)
        {
            liste.add(inventory.getItems()[i]);
        }
        items.setItems(liste);
        number.setText("Number of items : "+ inventory.getItems().length);
    }

    public void onEdit()
    {
        inventory.updateQuality();
        items.refresh();
    }

    public void onAdd()
    {

        Item itemAdd = new Item(12000,nameInput.getText(),Integer.parseInt(sellInInput.getText()),Integer.parseInt(quantityInput.getText()),new Date());
        Item [] itemTemp= new Item[inventory.getItems().length+1];

        for(int i=0;i< inventory.getItems().length;i++)
        {
            itemTemp[i]=inventory.getItems()[i];
        }
        itemTemp[inventory.getItems().length]=itemAdd;
        inventory.setItems(itemTemp);
        ObservableList<Item >liste3 =FXCollections.observableArrayList();
        for(int j=0; j<inventory.getItems().length;j++)
        {
            liste3.add(inventory.getItems()[j]);
        }
        items.setItems(liste3);
        countKinds();
        salut();

    }
    public void loadFromFile()
    {
        JSONParser parser = new JSONParser();

        try {

            JSONArray a = (JSONArray) parser.parse(new FileReader("items.json"));

            Item[] itemsTab =new Item[a.size()];

            int i=0;

            for(Object o : a){
                JSONObject jsonObject =(JSONObject) o;
                String name = (String) jsonObject.get("name");
                int quality =(int)(long)jsonObject.get("quality");
                int sellIn =(int)(long)jsonObject.get("sellIn");

                itemsTab[i]=new Item(i,name,sellIn,quality, new Date());
                i++;
            }

            inventory.setItems(itemsTab);
            ObservableList<Item >liste2 =FXCollections.observableArrayList();

           for(int j=0; j<inventory.getItems().length;j++)
           {
                liste2.add(inventory.getItems()[j]);
           }

          items.setItems(liste2);

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("salut  1");
        countKinds();
        System.out.println("________________________");
        for(int i=0;i<6;i++)
        { System.out.println(numberOfKind[i]);
        }
        number.setText("Number of items : "+ inventory.getItems().length);

        salut();
    }

    public void countKinds()
    {

        this.numberOfKind=new int[inventory.getItems().length];
        for(int i=0; i<inventory.getItems().length;i++)
        {
            this.numberOfKind[i]=0;
        }


        for(int j=0;j<inventory.getItems().length;j++) {

            switch (inventory.getItems()[j].getName()){
                case "+5 Dexterity Vest" : numberOfKind[0]++;
                break;
                case "Aged Brie" : numberOfKind[1]++;
                    break;
                case "Elixir of the Mongoose" : numberOfKind[2]++;
                    break;
                case "Sulfuras, Hand of Ragnaros" : numberOfKind[3]++;
                    break;
                case "Backstage passes to a TAFKAL80ETC concert" : numberOfKind[4]++;
                    break;
                case "Conjured Mana Cake" : numberOfKind[5]++;
                    break;
                    default:
                        System.out.println("pas trouvé");

            }
        }
    }

    private void salut(){

          monPane.getChildren().clear();
          ObservableList<PieChart.Data> listPie= FXCollections.observableArrayList();
          listPie.add(new PieChart.Data("Dexterity Vest", numberOfKind[0]));
          listPie.add(new PieChart.Data("Aged Brie", numberOfKind[1]));
          listPie.add(new PieChart.Data("Elixir of the Mongoose", numberOfKind[2]));
          listPie.add(new PieChart.Data("Sulfuras", numberOfKind[3]));
          listPie.add(new PieChart.Data("Backstage passes", numberOfKind[4]));
          listPie.add(new PieChart.Data("Conjured Mana Cake", numberOfKind[5]));


        PieChart kindChart = new PieChart(listPie);
          kindChart.setTitle("number of the kind");
          monPane.getChildren().add(kindChart);

    }

    public void changeScene(){

        Item[] items =inventory.getItems();

        String[] doneDate=new String[inventory.getItems().length];
        int [] done = new int[inventory.getItems().length];
        for(int i=0;i<done.length;i++){
            done[i]=0;
            doneDate[i]="zero";
        }
        int k=0;
        int z=0;
        for(int i=0; i < items.length;i++){

            if(!alreadyDone(items[i].getSellIn(),done)){
                done[k]=items[i].getSellIn();
                k++;
            }
            if(!alreadyDoneString(items[i].getStringBuy(),doneDate)){

                doneDate[z]=items[i].getStringBuy();
                z++;
            }
        }

        for(int i=0;i<doneDate.length;i++){
            System.out.println("Here"+doneDate[i]);
        }
        for(int i=0;i<done.length;i++){
            System.out.println("Here sell"+done[i]);
        }


        int tempTab[] = new int[items.length];
        String tempStringTab[]= new String[items.length];
        for(int i=0;i<items.length;i++){
            tempTab[i]=items[i].getSellIn();
            tempStringTab[i]=items[i].getStringBuy();
        }

        int sellIns[][] =new int [diffrentSell(done)][2];
        String dates[]=new String[diffrentDates(doneDate)];
        int datesNumbers[]=new  int[diffrentDates(doneDate)];
        for(int i =0;i<diffrentSell(done);i++){
            sellIns[i][0]=done[i];
            sellIns[i][1]=Occurences(tempTab,done[i]);
        }

        for(int i =0;i<diffrentDates(doneDate);i++){
            dates[i]=doneDate[i];
            datesNumbers[i]=OccurencesString(tempStringTab,doneDate[i]);
            System.out.println(dates[i]+ " "+datesNumbers[i]);
        }


        XYChart.Series set1 =new XYChart.Series<>();

        for(int i=0;i<diffrentSell(done);i++){
            set1.getData().add(new XYChart.Data(new Integer(sellIns[i][0]).toString() ,sellIns[i][1])) ;
        }

        sellChart.getData().addAll(set1);

        XYChart.Series set2 =new XYChart.Series<>();

        for(int i=0;i<diffrentDates(doneDate);i++){
            set2.getData().add(new XYChart.Data(dates [i],datesNumbers[i])) ;
        }

        dateChart.getData().addAll(set2);
    }
    public  boolean alreadyDone(int x,int done[])
    {
        boolean fait=false;
        for(int i=0;i<done.length;i++){
            if(done[i]==x){
                fait=true;
            }
        }
        return fait;
    }
    public  boolean alreadyDoneString(String date,String doneString[])
    {
        boolean fait=false;

        for(int i=0;i<doneString.length;i++){

            if(doneString[i].equals(date)){

                fait=true;
            }
        }
        return fait;
    }

    public   int diffrentSell(int [] done){
        int different=done.length;

        for(int i=0;i<done.length;i++){
            if(done[i]==0){
                return i;
            }
        }
        return different;

    }
    public   int diffrentDates(String [] doneDate){
        int different=doneDate.length;

        for(int i=0;i<doneDate.length;i++){
            if(doneDate[i].equals("zero")){
                return i;
            }
        }
        return different;

    }
    public  int Occurences(int tab[], int x){
        int compteur=0;
        for(int i=0;i<tab.length;i++){
            if(x==tab[i]){
                compteur++;
            }
        }
        return compteur;
    }
    public  int OccurencesString(String tab[], String chaine){
        int compteur=0;
        for(int i=0;i<tab.length;i++){
            if(chaine.equals(tab[i])){
                compteur++;
            }
        }
        return compteur;
    }

}

