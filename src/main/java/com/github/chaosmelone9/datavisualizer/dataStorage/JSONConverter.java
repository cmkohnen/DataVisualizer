package com.github.chaosmelone9.datavisualizer.dataStorage;

import com.github.chaosmelone9.datavisualizer.CustomGSON;
import com.github.chaosmelone9.datavisualizer.config.Config;
import com.github.chaosmelone9.datavisualizer.ui.GraphData.DataSet;
import com.github.chaosmelone9.datavisualizer.ui.GraphData.GraphDataSet;

public class JSONConverter {
    public static String convertToJSON(Object object) throws Exception {
        return CustomGSON.GSON.toJson(object);
    }

    public static DataSet convertFromJSON(String json) throws Exception {
        return CustomGSON.GSON.fromJson(json, DataSet.class);
    }

    public static Config convertConfigFromJSON(String json) throws Exception {
        return CustomGSON.GSON.fromJson(json, Config.class);
    }
}
