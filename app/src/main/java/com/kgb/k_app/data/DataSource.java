package com.kgb.k_app.data;

import java.util.List;

/**
 * Created by Jan on 27.11.2016.
 */

public interface DataSource<Model> {
    void saveChanges(Model model);

    List<Model> retrieveData();

    int count();

    Model get(int position);

    long getItemId(int position);
}
