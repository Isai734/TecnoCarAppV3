package com.example.navi.tecnocarappv3.control;

import java.util.List;

/**
 * Created by Isai on 15/07/2017.
 */

public abstract class AutosInteractorImpl implements OperInteractor<Autos.Auto> {


    @Override
    public void update(List<Autos.Auto> data) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Autos.Auto query(long id) {
        return null;
    }

    @Override
    public List<Autos.Auto> selectAll() {
        return null;
    }
}
