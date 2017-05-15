package sunith.mercari.com.mercaribysunith.view;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sunith.mercari.com.mercaribysunith.R;
import sunith.mercari.com.mercaribysunith.model.SectionData;
import sunith.mercari.com.mercaribysunith.view.vertical.SectionVerticalListView;

/**
 * Created by snair on 06/05/2017.
 */

public class SectionHorizontalPagerAdapter extends PagerAdapter {


    private List<SectionData> sectionDatas;

    public SectionHorizontalPagerAdapter(List<SectionData> sectionDatas) {
        this.sectionDatas = sectionDatas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.section_list, container, false);
        view.setId(position);
        container.addView(view);

        if (view instanceof SectionVerticalListView){
            ((SectionVerticalListView) view).update(sectionDatas.get(position));
        }
        return view;
    }

    @Override
    public int getCount() {
        return sectionDatas.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
