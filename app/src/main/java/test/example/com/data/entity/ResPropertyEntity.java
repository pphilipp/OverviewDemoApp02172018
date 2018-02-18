package test.example.com.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResPropertyEntity {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("update")
    @Expose
    public Integer update;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("subject")
    @Expose
    public String subject;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("type_id")
    @Expose
    public Integer typeId;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("thumbnail_big")
    @Expose
    public String thumbnailBig;
    @SerializedName("image_count")
    @Expose
    public Integer imageCount;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("price_period")
    @Expose
    public String pricePeriod;
    @SerializedName("price_period_raw")
    @Expose
    public String pricePeriodRaw;
    @SerializedName("price_label")
    @Expose
    public String priceLabel;
    @SerializedName("price_value")
    @Expose
    public String priceValue;
    @SerializedName("price_value_raw")
    @Expose
    public Integer priceValueRaw;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("featured")
    @Expose
    public Boolean featured;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("poa")
    @Expose
    public String poa;
    @SerializedName("rera_permit")
    @Expose
    public String reraPermit;
    @SerializedName("bathrooms")
    @Expose
    public Integer bathrooms;
    @SerializedName("bedrooms")
    @Expose
    public Integer bedrooms;
    @SerializedName("visited")
    @Expose
    public Boolean visited;
    @SerializedName("date_insert")
    @Expose
    public String dateInsert;
    @SerializedName("date_update")
    @Expose
    public String dateUpdate;
    @SerializedName("agent_name")
    @Expose
    public String agentName;
    @SerializedName("broker_name")
    @Expose
    public String brokerName;
    @SerializedName("agent_license")
    @Expose
    public String agentLicense;
    @SerializedName("location_id")
    @Expose
    public Integer locationId;
    @SerializedName("hide_location")
    @Expose
    public Boolean hideLocation;
    @SerializedName("broker")
    @Expose
    public Broker broker;
    @SerializedName("amenities")
    @Expose
    public List<String> amenities;
    @SerializedName("amenities_keys")
    @Expose
    public List<String> amenitiesKeys;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("long")
    @Expose
    public Double longVariable;

    public boolean isAmenitiesListNotEmpty() {
        return amenities != null && amenities.size() > 0;
    }

    public boolean isAmenitiesKeysListNotEmpty() {
        return amenitiesKeys != null && amenitiesKeys.size() > 0;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public List<String> getAmenitiesKeys() {
        return amenitiesKeys;
    }
}
