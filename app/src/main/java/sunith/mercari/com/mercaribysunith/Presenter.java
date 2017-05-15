package sunith.mercari.com.mercaribysunith;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import sunith.mercari.com.mercaribysunith.model.SectionData;

public class Presenter {

    private final DataRepository dataRepository;

    interface Listener{
        void updateView(List<SectionData> sectionDatas);
        void showMessage(String message);
    }


    Presenter(Context context) {
        dataRepository = new DataRepository(context);
    }

    void setupView(final Listener listener) {
        dataRepository
                .getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataObservable(listener));
    }

    @NonNull
    private Observer<List<SectionData>> dataObservable(final Listener listener) {
        return new Observer<List<SectionData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.showMessage("Something went wrong - " + e.getMessage());
            }

            @Override
            public void onNext(List<SectionData> sectionDatas) {
                listener.updateView(sectionDatas);
            }
        };
    }
}
