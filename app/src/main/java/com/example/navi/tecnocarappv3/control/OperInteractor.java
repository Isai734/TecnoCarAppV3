package com.example.navi.tecnocarappv3.control;

import java.util.List;

/**
 * Created by Isai on 15/07/2017.
 */

public interface OperInteractor<E> {
    void post(E data);
    void put(E data);
    void delete(String id);
    void get(int id);
    List<E> getAll();
}
