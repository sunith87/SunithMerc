package sunith.mercari.com.mercaribysunith.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SectionData {

    @SerializedName("data")
    private List<SectionItem> allData;
    private String itemName;

    public List<SectionItem> getAllData() {
        return allData;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
