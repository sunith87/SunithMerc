package sunith.mercari.com.mercaribysunith.view.vertical;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import sunith.mercari.com.mercaribysunith.R;
import sunith.mercari.com.mercaribysunith.model.SectionItem;
import sunith.mercari.com.mercaribysunith.view.SectionViewHolder;

/**
 * Created by snair on 06/05/2017.
 */
public class SectionVerticalListAdapter extends RecyclerView.Adapter<SectionViewHolder> {


    private List<SectionItem> sectionItems;

    SectionVerticalListAdapter(List<SectionItem> sectionItem) {
        this.sectionItems = sectionItem;
    }


    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.section_item, parent, false);
        return new SectionViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.bind(sectionItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sectionItems.size();
    }
}
