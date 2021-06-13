package DAO;

import DAO.Interfaces.ILivreRepository;
import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreRepository implements ILivreRepository {
    Connection connection = SingletonConnection.getConnexion();

    @Override
    public List<Livre> findByType(String type) {
        List<Livre> ToylistBytype = new ArrayList<>();
        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM livres t left JOIN vendors v on t.id=v.id JOIN toytypes" +
//                    " tt on tt.id=t.typeid where tt.Name=?");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM livre");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre(rs.getInt("Id"),
                        rs.getString("Nom"),
                        rs.getString("Libelle")
                        ,rs.getString("Type"),
                        rs.getString("Categorie"),
                        rs.getString("Auteur"),
                        rs.getString("Maison_Ed"),
                        rs.getInt("Nb_Exemp")                );
                ToylistBytype.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ToylistBytype;
    }

    @Override
    public Livre findById(int id) {
        return null;
    }

    @Override
    public List<Livre> getAll() {
        List<Livre> LivreList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Livre");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre(rs.getInt("Id"),
                        rs.getString("Nom"),
                        rs.getString("Lib")
                        , rs.getString("Type"),
                        rs.getString("Categorie"),
                        rs.getString("Auteur"),
                        rs.getString("Maison_Ed"),
                        rs.getInt("Nb_Exemp"));
                LivreList.add(livre);
            }
        } catch(SQLException e){
                e.printStackTrace();
            }

            return LivreList;
        }

    @Override
    public void add(Livre entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Livre entity) {

    }

//        @Override
//    public void add(Livre livre) {
//
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO toys(Id,Name,TypeId,MinAge,MaxAge,PicturePath,Price,VendorId,Quantity) " +
//                    "VALUES (? , ? ,? ,? ,? ,? ,? ,? , ?)") ;
//            ps.setInt(1,toy.getId());
//            ps.setString(2,toy.getName());
//            ps.setInt(3,toy.getType_id());
//            ps.setInt(4,toy.getMin_age());
//            ps.setInt(5,toy.getMax_age());
//            ps.setString(6,toy.getPhoto());
//            ps.setDouble(7,toy.getPrice());
//            ps.setInt(8,toy.getVendorID());
//            ps.setDouble(9,toy.getStock());
//
//            ps.executeUpdate();
//
//            System.out.println("toy ajouté");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("error");
//
//        }
//
//
//    }
//
//    @Override
//    public void delete(int id) {
//
//        try {
//
//            PreparedStatement st = connection.prepareStatement("DELETE FROM toys where Id = ?");
//            st.setInt(1, id);
//            st.executeUpdate();
//            System.out.println("c'est bon");
//
//        } catch (SQLException e) {
//            System.out.println("erreur");
//        }
//    }
//
//    @Override
//    public void update(Livre livre) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("UPDATE toys SET Name=? , Price= ?,TypeId=?,MinAge= ?, MaxAge=?,PicturePath=?," +
//                    "VendorId=?,Quantity=? where Id= ?");
//            ps.setString(1,toy.getName());
//            ps.setDouble(2,toy.getPrice());
//            ps.setInt(3,toy.getType_id());
//            ps.setInt(4,toy.getMin_age());
//            ps.setInt(5,toy.getMax_age());
//            ps.setString(6,toy.getPhoto());
//            ps.setInt(7,toy.getVendorID());
//            ps.setDouble(8,toy.getStock());
//            ps.setDouble(9,toy.getId());
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//
//@Override
//    public List<String> getAllMails() {
//        List<String> emails = new ArrayList<String>();
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM mailinglist");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                String email= rs.getString("Email");
//
//                emails.add(email);
//                System.out.println(emails);
//                System.out.println("--------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return emails;    }
//
//    @Override
//    public List<String> getAllVendors() {
//        List<String> Vendorlist = new ArrayList<>();
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * from vendors ");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Vendor vendor = new Vendor(rs.getInt("Id"),
//                        rs.getString("Name")
//                        ,rs.getString("Email"),rs.getString("Address")
//                        ,rs.getString("FacebookUrl"),
//                        rs.getString("phone")
//                );
//
//                Vendorlist.add(vendor.getName()+" "+"["+vendor.getId()+"]");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return Vendorlist;
//    }
//
//
//    @Override
//    public List<String> getAllTypes() {
//        List<String> ToyTypelist = new ArrayList<>();
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * from toytypes ");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                ToyType toyType = new ToyType(rs.getInt("Id"),
//                        rs.getString("Name"));
//
//                ToyTypelist.add(toyType.getName()+" "+"["+toyType.getId()+"]");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return ToyTypelist;
//    }
//    @Override
//    public void addType(ToyType toyType) {
//
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO toytypes(Id,Name) " +
//                    "VALUES (?,?)") ;
//            ps.setInt(1,toyType.getId());
//            ps.setString(2,toyType.getName());
//            ps.executeUpdate();
//
//            System.out.println("type ajouté");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("error");
//
//        }
//    }
//
//    @Override
//    public List<SalesByPerson> GetSalesByPerson(Date startDate, Date endDate) {
//        List<SalesByPerson> salesByPersonList = new ArrayList<SalesByPerson>();
//        try {
//            CallableStatement callableStatement = connection.prepareCall("{call GetSalesByPerson(?,?)}");
//            callableStatement.setDate(1, startDate);
//            callableStatement.setDate(2, endDate);
//            ResultSet rs = callableStatement.executeQuery();
//
//            while (rs.next()) {
//                SalesByPerson salesByPerson = new SalesByPerson();
//                int id= rs.getInt("SalesPersonId");
//                Double cv = rs.getDouble("SUM(od.Quantity*od.UnitPrice)") ;
//                salesByPerson.setId(id);
//                salesByPerson.setCv(cv);
//                salesByPersonList.add(salesByPerson);
//                System.out.println(salesByPersonList);
//                System.out.println("--------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return salesByPersonList;    }
//
//    @Override
//    public List<SalesByPerson> GetMaxSalesByPerson(Date startDate, Date endDate) {
//        List<SalesByPerson> MaxsalesByPersonList = new ArrayList<SalesByPerson>();
//        try {
//            CallableStatement callableStatement = connection.prepareCall("{call GetMaxSalesByPerson(?,?)}");
//            callableStatement.setDate(1, startDate);
//            callableStatement.setDate(2, endDate);
//            ResultSet rs = callableStatement.executeQuery();
//
//            while (rs.next()) {
//                SalesByPerson salesByPerson = new SalesByPerson();
//                int id= rs.getInt("SalesPersonId");
//                Double cv = rs.getDouble("SUM(od.Quantity*od.UnitPrice)") ;
//                salesByPerson.setId(id);
//                salesByPerson.setCv(cv);
//                MaxsalesByPersonList.add(salesByPerson);
//                System.out.println(MaxsalesByPersonList);
//                System.out.println("--------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return MaxsalesByPersonList;     }
//
//    @Override
//    public List<SalesByToy> GetSalesByToy(Date startDate, Date endDate) {
//        List<SalesByToy> salesByToyList = new ArrayList<SalesByToy>();
//        try {
//            CallableStatement callableStatement = connection.prepareCall("{call GetSalesByToy(?,?)}");
//            callableStatement.setDate(1, startDate);
//            callableStatement.setDate(2, endDate);
//            ResultSet rs = callableStatement.executeQuery();
//
//            while (rs.next()) {
//                SalesByToy salesByToy = new SalesByToy();
//                String name= rs.getString("Name");
//                Double cv = rs.getDouble("SUM(od.Quantity*od.UnitPrice)") ;
//                salesByToy.setName(name);
//                salesByToy.setCv(cv);
//                salesByToyList.add(salesByToy);
//                System.out.println(salesByToyList);
//                System.out.println("--------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return salesByToyList;    }
//
//    @Override
//    public Toy getToy() {
//        return null;
//    }
//
//    @Override
//    public ArrayList<Double> GetTotalSales(Date startDate, Date endDate) {
//        ArrayList<Double> totalSalesList = new ArrayList<>();
//        try {
//            CallableStatement callableStatement = connection.prepareCall("{call GetTotalSales(?,?)}");
//            callableStatement.setDate(1, startDate);
//            callableStatement.setDate(2, endDate);
//            ResultSet rs = callableStatement.executeQuery();
//
//            while (rs.next()) {
//                Double cvg = rs.getDouble("SUM(od.Quantity*od.UnitPrice)") ;
//                totalSalesList.add(cvg);
//                System.out.println(totalSalesList);
//                System.out.println("--------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return totalSalesList;    }
//
//    @Override
//    public int GetTotaltoys() {
//        int nbtoys =0 ;
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT count(*) as total from toys ");
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//              nbtoys=rs.getInt("total") ;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return nbtoys;    }
}

