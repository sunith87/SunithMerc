package sunith.mercari.com.mercaribysunith.model;


import com.google.gson.annotations.SerializedName;

public class SectionItem {

    private static final String SOLD_OUT = "sold_out";

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String purchaseStatus;
    @SerializedName("num_likes")
    private int numOfLikes;
    @SerializedName("num_comments")
    private int numOfComments;
    @SerializedName("price")
    private int price;
    @SerializedName("photo")
    private String imageUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSoldOut() {
        return purchaseStatus.equals(SOLD_OUT);
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public int getNumOfComments() {
        return numOfComments;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
