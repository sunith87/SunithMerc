package sunith.mercari.com.mercaribysunith.view;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import sunith.mercari.com.mercaribysunith.R;
import sunith.mercari.com.mercaribysunith.model.SectionItem;

/**
 * Created by snair on 06/05/2017.
 */
public class SectionViewHolder extends RecyclerView.ViewHolder {

    private final TextView commentTextView;
    private final TextView likesTextView;
    private final TextView priceTextView;
    private final LinearLayout soldOutHolder;
    private final ImageView imageView;

    public SectionViewHolder(View itemView) {
        super(itemView);
        commentTextView = (TextView) itemView.findViewById(R.id.section_comment_textView);
        likesTextView = (TextView) itemView.findViewById(R.id.section_likes_textView);
        priceTextView = (TextView) itemView.findViewById(R.id.section_price_textView);
        soldOutHolder = (LinearLayout) itemView.findViewById(R.id.section_badge_sold_out_holder);
        imageView = (ImageView) itemView.findViewById(R.id.section_image);
    }


    public void bind(SectionItem sectionItem) {

        int numOfComments = sectionItem.getNumOfComments();
        commentTextView.setText(Integer.toString(numOfComments));

        int numOfLikes = sectionItem.getNumOfLikes();
        likesTextView.setText(Integer.toString(numOfLikes));

        int price = sectionItem.getPrice();
        String priceString = "$" + price;
        priceTextView.setText(priceString);

        int visibility = sectionItem.isSoldOut() ? View.VISIBLE : View.GONE;
        soldOutHolder.setVisibility(visibility);

        String imageUrl = sectionItem.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)){
            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.image_unavailable)
                    .into(imageView);

        }

    }
}
