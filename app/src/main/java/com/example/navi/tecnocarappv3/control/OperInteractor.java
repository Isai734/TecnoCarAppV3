package com.example.navi.tecnocarappv3.control;

import java.util.List;

/**
 * Created by Isai on 15/07/2017.
 */

public interface OperInteractor<E> {
    void insert(E data);
    void update(List<E> data);
    void delete(long id);
    E query(long id);
    List<E> selectAll();
}
