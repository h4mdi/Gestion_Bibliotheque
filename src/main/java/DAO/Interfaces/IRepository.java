package DAO.Interfaces;

import java.util.List;

public interface IRepository<T> {

    public T findById(int id);
    public List<T> getAll() ;
    public void add(T entity);
    public void delete(int id);
    public void update(T entity);

}
