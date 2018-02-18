package test.example.com.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertiesEntity {
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("res")
    @Expose
    public List<ResPropertyEntity> res;

    public boolean isReEntityListNotEmpty() {
        return res != null && res.size() > 0;
    }

    public List<ResPropertyEntity> getRes() {
        return res;
    }
}
