package DAO.Interfaces;

import Models.Livre;

import java.util.List;

public interface ILivreRepository extends IRepository<Livre> {
//    public List<String> getAllMails();
//    public List<String> getAllVendors();
//    public List<String> getAllTypes();
//    public Livre findById(int id);
    public List<Livre> getAll();

//    public void addType(ToyType toyType);
//    public List<SalesByPerson> GetSalesByPerson(Date startDate, Date endDat);
//    public List<SalesByPerson> GetMaxSalesByPerson(Date startDate, Date endDat);
//    public List<Double> GetTotalSales(Date startDate, Date endDat);
//    public List<SalesByToy> GetSalesByToy(Date startDate, Date endDat);
    public List<Livre> findByType(String type) ;
//    public Livre getToy() ;
//    public int GetTotaltoys();

}
