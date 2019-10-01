//import com.sun.org.apache.xpath.internal.operations.String;

import javafx.application.*;
import javafx.fxml.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.Insets;
import java.net.*;
import java.io.*;


public class netBenchMarkIPv4 extends Application{
    //class variables
    int variable1;
    int numOfLoops = 0;

    //GUI element variables
    Text txt1, txt2, txt3;
    TextField URLTextField, tf2, tf3;
    Label lb1, lb2, lb3, lb4, lb5, lb6, lb7;
    String a, b, c, d, e, f;
    MenuButton mbt1, mbt2, mbt3;
    ChoiceBox TestOnChoiceBox, ch4, ch5;
    
    //public static final ObservableList ch4 = FXCollections.observableArrayList();

    //GUI Layout options
    Pane mainPane, root;
    HBox hb1, hb2, hb3, hb4;
    VBox vb1, vb2;
    FlowPane fpane1, fpane2, fpane3;
    GridPane gpane1, gpane2;

    @Override
    public void start(Stage networkSetUpWindow){
        //GUI elements
        txt1 = new Text("Configure the network : ");
        txt1.setTextAlignment(TextAlignment.LEFT);
        txt1.setFont(Font.font("HPSimplified", FontWeight.LIGHT, 14));
        
        lb1 = new Label("URL");
        lb2 = new Label("Test On");
        lb3 = new Label("Transmission Medium");
        lb4 = new Label("Standard");
        lb5 = new Label("Protocol");
        lb6 = new Label("Buffer Size");
        lb7 = new Label("Duration");

        URLTextField = new TextField("");
        URLTextField.setEditable(true);

        //selects the network on run on
        TestOnChoiceBox = new ChoiceBox();
        TestOnChoiceBox.getItems().addAll("lo : Loopback", "LAN", "WLAN");
        TestOnChoiceBox.setMinWidth(180);
        TestOnChoiceBox.setValue("lo : Loopback");


/*        
        //transimission medium
        String[] transmissionMedium = new String[]{"Wired", "Wireless"};
        ObservableList items = FXCollections.observableArrayList(transmissionMedium);
        ComboBox<String> TransmissionMediumMenu = new ComboBox<>();

*/


        //transimission medium
        Menu TransmissionMediumMenu = new Menu();
        //add menu to the menubar
        MenuBar TransmissionMediumMenuBar = new MenuBar();
        TransmissionMediumMenu.setText("Select Interface");
        TransmissionMediumMenuBar.getMenus().add(TransmissionMediumMenu);
        //TransmissionMediumMenu.getItems().addAll(items);
        //TransmissionMediumMenu.setMinWidth(180);
        //TransmissionMediumMenu.setValue("Wired");
        
        //protocol
        String[] protocol = new String[]{"HTTP","HTTPS","FTP","SFTP","SMB","SSH","DNS"};
        ObservableList items1 = FXCollections.observableArrayList(protocol);
        ComboBox<String> ProtocolChoiceBox = new ComboBox<>();
        ProtocolChoiceBox.getItems().addAll(items1);
        ProtocolChoiceBox.setMinWidth(180);
        ProtocolChoiceBox.setValue("HTTP");

        /*tests connection with the server by first passing
        an index interger to the testProtocol method
        */ 
        ProtocolChoiceBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){
                boolean result = testProtocol(items1.indexOf(ProtocolChoiceBox.getValue()));
                if(result == false){
                    do{
                        //launch error dialog box
                        ErrorMessage error1 = new ErrorMessage();
                        error1.showError();
                        //warns the user three times
                        numOfLoops += 1;
                    }while(numOfLoops < 3);
                }
                else{
                    System.out.println("connection success!");
                }
            }
        });




        ch4 = new ChoiceBox();
        ch4.setPrefWidth(100);

        //GUI styling options
        hb1 = new HBox();
        hb1.setPadding(new Insets(10, 10, 480, 15));
        hb1.getChildren().add(txt1);
        gpane1 = new GridPane();
        gpane1.setPadding(new Insets(60, 320, 260, 80));
        gpane1.setVgap(5);
        gpane1.setHgap(20);

        //places the nodes to the gridpane
        gpane1.add(lb1, 0, 0);
        gpane1.add(URLTextField, 1, 0);
        gpane1.add(lb2, 0, 1);
        gpane1.add(TestOnChoiceBox, 1, 1);
        gpane1.add(lb3, 0, 2);
        gpane1.add(TransmissionMediumMenuBar, 1, 2);
        //gpane1.add(lb4, 2, 2);



/*        
        //behavior of the selected choice of transmission medium
        TransmissionMediumMenuBar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){
                setStandard(items.indexOf(TransmissionMediumMenuBar.getValue())); //sets method  
            }
        });


*/

        //gpane1.add(ch4, 3, 2);
        gpane1.add(lb5, 0, 3);
        gpane1.add(ProtocolChoiceBox, 1, 3);

        hb2 = new HBox();
        hb2.setPadding(new Insets(60, 10, 260, 10));
        //fpane1 = new FlowPane();
        //fpane1.setPadding(new Insets(10, 10, 380, 10));
        //fpane1.setVgap(10);
        //fpane1.setHgap(10);
        //fpane1.getChildren().add(txt1);
        mainPane = new Pane();
        mainPane.getChildren().addAll(hb1, gpane1);

        //FXML loader sets the GUI
        /**the string location can be changed depending where
         * the FXML file is kept
         */
        try{
            String fxmlUrlDoc = "C:/Users/H3K/Documents/code/New folder/net/netBenchMarkIPv4.fxml";
            URL fxmlUrl = new URL(fxmlUrlDoc);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxmlUrl);
            //Parent root = FXMLLoader.load(getClass().getResource("netBenchMarkIPv4.fxml"));
            try{
                root = loader.load();
            }
            catch(IOException ex){}

        }
        catch(MalformedURLException ex){}

        
        Scene scene = new Scene(root, 650, 400);
        networkSetUpWindow.setScene(scene);
        networkSetUpWindow.setTitle("Protocol Benching Mark");
        networkSetUpWindow.show();
    }


/*    
    //this method selects the standard when ever 
    //the transmission medium is selected 
    public void setStandard(int index){
        if(index == 0){
            //options for the wired interface
            ComboBox ch4 = new ComboBox();
            ch4.getItems().addAll("100BaseT (Fast Ethernet)", "1000Baset (Gigabit Ethernet)",
            "10 Gigabit Ethernet"
            );
            ch4.setPrefWidth(100);
            ch4.setValue("100BaseT (Fast Ethernet)");
            gpane1.add(ch4, 3, 2);
            
        }
        else{
            //options for the wireless interface
            ComboBox ch4 = new ComboBox();
            ch4.getItems().addAll("802.11a", "802.11b", "802.11g");
            ch4.setPrefWidth(100);
            ch4.setValue("802.11a");
            gpane1.add(ch4, 3, 2);
        }
    }
    


*/    
    /*
    -this method verifies the connection to the server
    -it is suppose return a boolean varible that
     determines whether to launch an error dialog box
    */
    public boolean testProtocol(int index){
        boolean a1 = false;
        boolean a2 = false;
        boolean a3 = false;
        boolean a4 = false;
        boolean a5 = false;
        boolean a6 = false;
        boolean a7 = false;
        //creates the object
        protocol test1 = new protocol();

        if(index == 0){
            a1 = test1.pingHTTPserver();
            return a1;            
        }
        else if(index == 1){
            a2 = test1.pingHTTPSserver();
            return a2;
        }
        else if(index == 2){
            a3 = test1.pingFTPserver();
            return a3;
        }
        else if(index == 3){
            a4 = test1.pingSFTPserver();
            return a4;
        }
        else if(index == 4){
            a5 = test1.pingSMBserver();
            return a5;
        }
        else if(index == 5){
            a6 = test1.pingSSHserver();
            return a6;
        }
        else{
            a7 = test1.pingDNSserver();
            return a7;
        }
    }
    
    /**start button handles the testing */
    @FXML protected void handleStartButton(ActionEvent event){
        
    }
    
    public static void main(String [] args){
        //starts the application
        Application.launch(args);
    }
}