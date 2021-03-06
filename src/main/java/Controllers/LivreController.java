package Controllers;

import DAO.LivreRepository;
import Models.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LivreController implements Initializable {
    @FXML
    private TabPane tab;
    @FXML
    private ComboBox alphaFilter;

    String choix = "";

    @FXML
    private TextField nameFilter;


    @FXML
    private TextField update_min1;


    @FXML
    private TextField update_max1;

    @FXML
    private Pagination pagination;
    private FilteredList<Livre> filteredData;

    private static final int ROWS_PER_PAGE = 7;
    @FXML
    private TableView<Livre> tableView;


    @FXML
    private TableColumn<Livre, Integer> id;
    @FXML
    private TableColumn<Livre, String> nom;


    @FXML
    private TableColumn<Livre, String> libelle;

    @FXML
    private TableColumn<Livre, String> type;

    @FXML
    private TableColumn<Livre, String> categorie;

    @FXML
    private TableColumn<Livre, String> auteur;

    @FXML
    private TableColumn<Livre, Integer> nb_exemplaire;


    @FXML
    private TableColumn<Livre, String> maisond_edition;



    @FXML
    private TextField update_name;
    @FXML
    private TextField update_vendor;
    @FXML
    private TextField update_price;

    @FXML
    private TextField update_stock;
    @FXML
    private TextField update_picture;
    @FXML
    private TextField update_min;
    @FXML
    private TextField update_max;
    @FXML
    private Button PhotoUpload;
    @FXML
    private ComboBox<String> update_type;

    @FXML
    private TextField toyphoto;
    @FXML
    private TextField insert_name;

    @FXML
    private TextField insert_price;
    @FXML
    private TextField insert_stock;
    @FXML
    private TextField photoProduit;
    @FXML
    private ComboBox<String> insert_Vendor;
    @FXML
    private ComboBox<String> update_Vendor;

    @FXML
    private ComboBox<String> insert_type1;

@FXML
    private Button btn_delToy ;
    ObservableList<Livre> oblist ;

    LivreRepository livreRepository = new LivreRepository();
    private String absolutePathPhoto;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Afficher();
//        insert_Vendor.setItems(FXCollections.observableArrayList(livreRepository.getAllVendors()));
//        insert_type1.setItems(FXCollections.observableArrayList(livreRepository.getAllTypes()));
//        update_type.setItems(FXCollections.observableArrayList(livreRepository.getAllTypes()));
//        update_Vendor.setItems(FXCollections.observableArrayList(livreRepository.getAllVendors()));
            }




    public void AlphaFilter(ActionEvent event) {
        choix = alphaFilter.getValue().toString();
        System.out.println(choix);
//stateACS is a toggle button
        if (choix.equals("DE A -> Z")) {
            Comparator<Livre> comparator = Comparator.comparing(Livre::getNom);
            changeTableView2(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);

            //Sort in asc order
            oblist.sort(comparator);
        } else if (choix.equals("DE Z -> A")) {
            Comparator<Livre> comparator = Comparator.comparing(Livre::getNom).reversed();
            changeTableView2(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);

            oblist.sort(comparator);


        }
    }




    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, oblist.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Livre> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);

    }

    private void changeTableView2(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, oblist.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Livre> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        Comparator<Livre> comparator = Comparator.comparing(Livre::getNom).reversed();
        oblist.sort(comparator);
        tableView.setItems(oblist);

    }

    @FXML
    private void show_addForm(ActionEvent event) {
        tab.getSelectionModel().select(1);
    }



//    @FXML
//    private void show_updateForm(ActionEvent event) {
//
//        update_name.setText(tableView.getSelectionModel().getSelectedItem().getName());
//        update_price.setText(Double.toString(tableView.getSelectionModel().getSelectedItem().getPrice()));
//        update_min.setText(Integer.toString(tableView.getSelectionModel().getSelectedItem().getMin_age()));
//        update_max.setText(Integer.toString(tableView.getSelectionModel().getSelectedItem().getMax_age()));
//        update_stock.setText(Double.toString(tableView.getSelectionModel().getSelectedItem().getStock()));
//        update_picture.setText(tableView.getSelectionModel().getSelectedItem().getPhoto());
//
//
//        tab.getSelectionModel().select(2);
//        //prenom.setText(utilisateur.getSelectionModel().getSelectedItem().getPrenom());
//
//        // valider.setVisible(false);
////        btn_suppProd.setVisible(true);
//        //  modifier.setVisible(false);
//        //annuler.setVisible(false);
//        // ajouter.setVisible(true);
//    }
//    @FXML
//    private void save_Update(ActionEvent event) {
//        deplacerVers(update_picture, absolutePathPhoto,"C:\\Users\\hp\\IdeaProjects\\ToysPOO\\src\\main\\resources\\toysphoto");
//        deplacerVers(update_picture, absolutePathPhoto,"C:\\wamp64\\www\\toys\\photos");
//
//        if (!tableView.getSelectionModel().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Modification d'un produit");
//            alert.setHeaderText("Etes-vous sur de vouloir modifier "
//                    + tableView.getSelectionModel().getSelectedItem().getName() + "?");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//
//                LivreRepository livreRepository = new LivreRepository();
//                int start =update_type.getSelectionModel().getSelectedItem().lastIndexOf('[')+1;
//                int end =update_type.getSelectionModel().getSelectedItem().indexOf(']');
//
//                Toy toy = new Toy(tableView.getSelectionModel().getSelectedItem().getId(),
//                        update_name.getText(),
//                        "http://localhost/toys/photos/"+update_picture.getText(),
//                        Double.parseDouble(update_price.getText()),
//                        Integer.parseInt(update_min.getText()),
//                        Integer.parseInt(update_max.getText()),
//                        Double.parseDouble(update_stock.getText()),
//                        Integer.parseInt(update_type.getSelectionModel().getSelectedItem().substring(start,end)),                        update_name.getText(),
//                        Integer.parseInt(update_Vendor.getSelectionModel().getSelectedItem().substring(update_Vendor.getSelectionModel().getSelectedItem().lastIndexOf('[')+1,update_Vendor.getSelectionModel().getSelectedItem().indexOf(']'))),
//                        update_Vendor.getSelectionModel().getSelectedItem().substring(0,update_Vendor.getSelectionModel().getSelectedItem().lastIndexOf('[')-1));
//
//
//                livreRepository.update(toy);
//
//                refresh();
//            }
//
//            update_name.setText("");
//            update_price.setText("");
//            update_min.setText("");
//            update_max.setText("");
//
//            //afficher.setVisible(false);
//            tab.getSelectionModel().select(0);
//
//        }
//    }
//
//    @FXML
//    private void photoChooser(ActionEvent event)
//    {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
//        );
//
//        File choix = fileChooser.showOpenDialog(null);
//        if (choix != null) {
//
//            absolutePathPhoto = choix.getAbsolutePath();
//            toyphoto.setText(choix.getName());
//        } else {
//            System.out.println("Image introuvable");
//        }
//
//    }
//
//    @FXML
//    private void upphotoChooser(ActionEvent event)
//    {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
//        );
//
//        File choix = fileChooser.showOpenDialog(null);
//        if (choix != null) {
//
//            absolutePathPhoto = choix.getAbsolutePath();
//            update_picture.setText(choix.getName());
//        } else {
//            System.out.println("Image introuvable");
//        }
//
//    }
//
//    @FXML
//    private void SaveAddToy(ActionEvent event) {
//
//
//        deplacerVers(toyphoto, absolutePathPhoto,"C:\\Users\\hp\\IdeaProjects\\ToysPOO\\src\\main\\resources\\toysphoto");
//        deplacerVers(toyphoto, absolutePathPhoto,"C:\\wamp64\\www\\toys\\photos");
//        Toy toy = new Toy() ;
//
//        toy.setName(insert_name.getText());
//        int start =insert_type1.getSelectionModel().getSelectedItem().lastIndexOf('[')+1;
//        int end =insert_type1.getSelectionModel().getSelectedItem().indexOf(']');
//
//        toy.setType_id(Integer.parseInt(insert_type1.getSelectionModel().getSelectedItem().substring(start,end))) ;//type
//        toy.setPhoto("http://localhost/toys/photos/"+toyphoto.getText()) ;
//        toy.setPrice( Double.parseDouble(insert_price.getText())) ;
//
//        toy.setVendor_name(insert_Vendor.getSelectionModel().getSelectedItem().substring(0,insert_Vendor.getSelectionModel().getSelectedItem().lastIndexOf('[')-1));
//        toy.setMin_age(Integer.parseInt(update_min1.getText())) ;//minage
//        toy.setMax_age(Integer.parseInt(update_max1.getText())); //maxage
//        toy.setVendorID(Integer.parseInt(insert_Vendor.getSelectionModel().getSelectedItem().substring(insert_Vendor.getSelectionModel().getSelectedItem().lastIndexOf('[')+1,insert_Vendor.getSelectionModel().getSelectedItem().indexOf(']')))) ;
//        toy.setStock(Double.parseDouble(insert_stock.getText()));
//
//
//        if (insert_name.getText().equals("") || insert_price.getText().equals("") ||
//                insert_price.getText()==null || toyphoto.getText()==null || insert_stock.getText().equals("")  ) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Alerte");
//            alert.setHeaderText("Vous devez remplir TOUT LES CHAMPS SVP");
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                tab.getSelectionModel().select(1);
//                Afficher();
//                insert_name.setText("");
//                insert_price.setText("");
//                toyphoto.setText("");
//                insert_stock.setText("");
//
//            }
//
//        }
//
//        else {  livreRepository.add(toy);
//            Afficher();
//            insert_name.setText("");
//            insert_price.setText("");
//            toyphoto.setText("");
//            insert_stock.setText("");
//            tab.getSelectionModel().select(0);
//
//            //maillist
//            List<String> emails = livreRepository.getAllMails();
//            String subject = "Nouvelle collection MBA && CBYTE !";
//            // message
//            String message = "La nouvelle collection est arriv??e ! D??couvrez-vite les nouveaut??s ! .\n";
//            // send
//            mailingList(subject, emails, message);
//        }
//
//    }
//
//    @FXML
//    private void deleteToy(ActionEvent event) {
//        if (!tableView.getSelectionModel().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Suppression d'un produit");
//            alert.setHeaderText("Etes-vous sur de vouloir supprimer le jouet "
//                    + tableView.getSelectionModel().getSelectedItem().getName() + "?");
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                LivreRepository livreRepository = new LivreRepository();
//                System.out.println("aywah"+tableView.getSelectionModel().getSelectedItem().getId());
//                livreRepository.delete(tableView.getSelectionModel().getSelectedItem().getId());
//                refresh();
//
//            }
//
//        }
//    }


    void Afficher() {

        oblist = FXCollections.observableArrayList(livreRepository.getAll());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));

        nb_exemplaire.setCellValueFactory(new PropertyValueFactory<>("nb_exemp"));
        maisond_edition.setCellValueFactory(new PropertyValueFactory<>("maison_ed"));

        filteredData = new FilteredList<>(oblist, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        nameFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(livre -> newValue == null || newValue.isEmpty() || livre.getNom().toLowerCase()
                    .contains(newValue.toLowerCase()));
            changeTableView(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);
        });
        pagination.setPageCount((int) (Math.ceil(filteredData.size() * 1.0 / ROWS_PER_PAGE)));


        int totalPage = (int) (Math.ceil(oblist.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));


    }

    // actualiser la liste apr??s chaque op??ration
    public void refresh(){
        oblist.clear();
        Afficher();
    }


    public void mailingList(final String subject, final List<String> emailToAddresses,
                                   final String emailBodyText) {

        // from email address
        final String username = "3laiag.fsegn@gmail.com";
        // make sure you put your correct password
        final String password = "3laiag2020";
        // smtp email server
        final String smtpHost = "smtp.googlemail.com";
        // We will put some properties for smtp configurations
        Properties props = new Properties();
        // do not change - start
        props.put("mail.smtp.user", "username");
        props.put("mail.smtp.host", smtpHost);
        // props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        // do not change - end

        // we authentcate using your email and password and on successful
        // we create the session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String emails = null;

        try {
            // we create new message
            Message message = new MimeMessage(session);

            // set the from 'email address'
            message.setFrom(new InternetAddress(username));

            // set email subject
            message.setSubject(subject);

            // set email message
            // this will send html mail to the intended recipients
            // if you do not want to send html mail then you do not need to wrap the message
            // inside html tags
            String content = "<html>\n<body>\n";
            content += emailBodyText + "\n";
            content += "\n";
            content += "</body>\n</html>";
            message.setContent(content, "text/html");

            // form all emails in a comma separated string
            StringBuilder sb = new StringBuilder();

            int i = 0;
            for (String email : emailToAddresses) {
                sb.append(email);
                i++;
                if (emailToAddresses.size() > i) {
                    sb.append(", ");
                }
            }

            emails = sb.toString();

            // set 'to email address'
            // you can set also CC or TO for recipient type
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(sb.toString()));

            System.out.println("Sending Email to " + emails + " from " + username + " with Subject - " + subject);

            // send the email
            Transport.send(message);

            System.out.println("Email successfully sent to " + emails);
        } catch (MessagingException e) {
            System.out.println("Email sending failed to " + emails);
            System.out.println(e);
        }
    }

//
//    @FXML
//    private void ToysTypeAdd(ActionEvent event) throws SQLException {
//
//        TextInputDialog dialog = new TextInputDialog("");
//        dialog.setTitle("Ajouter un nouveau type");
//        dialog.setContentText("Veuillez introduire le nouveau type d??sir??");
//
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()){
//            if(result.get()!=""){
//            //System.out.println("Type: " + result.get());
//                ToyType toyType = new ToyType();
//                toyType.setName(result.get());
//                livreRepository.addType(toyType);
//                insert_type1.setItems(FXCollections.observableArrayList(livreRepository.getAllTypes()));
//           }
//        }
//
//    }


    public void deplacerVers(TextField txtField, String path, String copyTo) {
        if (!(txtField.getText().equals("")) ){
            try {
                String[] args = { "CMD", "/C", "COPY", "/Y", path, copyTo };
                Process p = Runtime.getRuntime().exec(args);
                p.waitFor();
                System.out.println("execut??");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    /**** Sidebar Menu*/

    @FXML
    public void OpenHome(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        Scene scene = new Scene(parent);
        Stage homeStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        homeStage.setScene(scene);
        homeStage.centerOnScreen();

        homeStage.show();
    }

    @FXML
    public void OpenJouet(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Books.fxml"));
        Scene scene = new Scene(parent);
        Stage homeStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        homeStage.setScene(scene);
        homeStage.centerOnScreen();

        homeStage.show();
    }

    @FXML
    public void OpenFournisseur(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Vendor.fxml"));
        Scene scene = new Scene(parent);
        Stage homeStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        homeStage.setScene(scene);
        homeStage.centerOnScreen();

        homeStage.show();
    }

    @FXML
    public void OpenVentes(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Sales.fxml"));
        Scene scene = new Scene(parent);
        Stage statsStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        statsStage.setScene(scene);
        statsStage.centerOnScreen();

        statsStage.show();
    }

    @FXML
    public void openstats(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Stats.fxml"));
        Scene scene = new Scene(parent);
        Stage statsStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        statsStage.setScene(scene);
        statsStage.centerOnScreen();

        statsStage.show();
    }

    @FXML
    public void OpenUsers(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/User.fxml"));
        Scene scene = new Scene(parent);
        Stage statsStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        statsStage.setScene(scene);
        statsStage.centerOnScreen();

        statsStage.show();
    }



    @FXML
    public void OpenMaillist(ActionEvent actionEvent) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Maillist.fxml"));
        Scene scene = new Scene(parent);
        Stage homeStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        homeStage.setScene(scene);
        homeStage.centerOnScreen();

        homeStage.show();
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        ButtonType yes = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", yes, no);

        alert.setTitle("Confimer la d??connection");
        alert.setHeaderText("Voulez vous vraimenet d??connecter ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
            Scene scene = new Scene(parent);
            Stage homeStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            homeStage.setScene(scene);
            homeStage.centerOnScreen();

            homeStage.show();
        } else {
            alert.close();
        } }






}




