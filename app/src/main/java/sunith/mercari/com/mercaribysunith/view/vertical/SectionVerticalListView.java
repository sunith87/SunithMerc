package sunith.mercari.com.mercaribysunith.view.vertical;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import sunith.mercari.com.mercaribysunith.model.SectionData;

/**
 * Created by snair on 06/05/2017.
 */

public class SectionVerticalListView extends RecyclerView {
    public SectionVerticalListView(Context context) {
        super(context);
    }

    public SectionVerticalListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SectionVerticalListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        GridLayoutManager layout = new GridLayoutManager(getContext(),2);
        setLayoutManager(layout);
    }

    public void update(SectionData data){
     setAdapter(new SectionVerticalListAdapter(data.getAllData()));
    }
}
