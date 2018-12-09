package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import  javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



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
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellIn.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        quality.setCellValueFactory(new PropertyValueFactory<>("quality"));
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

        Item itemAdd = new Item(nameInput.getText(),Integer.parseInt(sellInInput.getText()),Integer.parseInt(quantityInput.getText()));
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

                itemsTab[i]=new Item(name,sellIn,quality);
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
}

