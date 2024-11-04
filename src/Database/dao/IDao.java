package Database.dao;

import java.util.ArrayList;
import java.util.List;

public interface IDao<T> {
    ArrayList<T> findAll();
    T findById(String id);
    void save(T newElement);
    void update(T newValuesElement);
    //void delete(String id);
}
