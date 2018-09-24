package com.kgb.k_app.data;

import java.util.List;

/**
 * Created by Jan on 27.11.2016.
 */

public interface DataSource<Model extends Data> {
    void confirmedChanges(Model model);

    List<Model> retrieveData();

    List<Model> retrieveData(String where);

    int count();

    Model get(int position);

    long getItemId(int position);
}
