package test.example.com.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Broker {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("agent_photo")
    @Expose
    public String agentPhoto;
    @SerializedName("agent_name")
    @Expose
    public String agentName;
    @SerializedName("lead_email_receivers")
    @Expose
    public List<String> leadEmailReceivers;
    @SerializedName("license")
    @Expose
    public String license;
    @SerializedName("agent_id")
    @Expose
    public Integer agentId;
    @SerializedName("logo")
    @Expose
    public String logo;

    public boolean isLeadEmailReceiversListNotEmpty() {
        return leadEmailReceivers != null && leadEmailReceivers.size() > 0;
    }

    public List<String> getLeadEmailReceivers() {
        return leadEmailReceivers;
    }
}
