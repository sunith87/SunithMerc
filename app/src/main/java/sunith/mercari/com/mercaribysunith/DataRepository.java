package sunith.mercari.com.mercaribysunith;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sunith.mercari.com.mercaribysunith.model.SectionData;

public class DataRepository {

    private static final String ALL_ASSETS[] = { "men.json", "all.json","women.json"};

    private final Context context;
    private final Gson gson;

    DataRepository(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    public Observable<List<SectionData>> getData() {

        return Observable.from(ALL_ASSETS)
                .flatMap(getExtractAssetObservable())
                .filter(sectionRawPredicate())
                .flatMap(mapRawToData())
                .takeLastBuffer(ALL_ASSETS.length)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    private Func1<SectionRaw, Boolean> sectionRawPredicate() {
        return new Func1<SectionRaw, Boolean>() {
            @Override
            public Boolean call(SectionRaw sectionRaw) {
                return sectionRaw.getSectionData() != null;
            }
        };
    }

    @NonNull
    private Func1<SectionRaw, Observable<SectionData>> mapRawToData() {
        return new Func1<SectionRaw, Observable<SectionData>>() {
            @Override
            public Observable<SectionData> call(SectionRaw sectionRaw) {
                SectionData sectionData = gson.fromJson(sectionRaw.getSectionData(), SectionData.class);
                sectionData.setItemName(sectionRaw.getName());
                return Observable.just(sectionData);
            }
        };
    }

    @NonNull
    private Func1<String, Observable<SectionRaw>> getExtractAssetObservable() {
        return new Func1<String, Observable<SectionRaw>>() {
            @Override
            public Observable<SectionRaw> call(String folderName) {

                try {
                    String rawValue = extractAssets(folderName);
                    String sectionName = folderName.split(".json")[0];
                    return Observable.just(new SectionRaw(sectionName, rawValue));
                }catch (Exception e){
                    return Observable.just(new SectionRaw(null, null));
                }
            }
        };
    }

    private String extractAssets(String folder) {
        try {
            AssetManager assets = context.getAssets();
            InputStream inputStreamAll = assets.open(folder);
            return convertStreamToData(inputStreamAll);
        } catch (IOException e) {
            throw Exceptions.propagate(e);
        }
    }

    private String convertStreamToData(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String read;
        StringBuilder builder = new StringBuilder();
        while ((read = reader.readLine()) != null) {
            builder.append(read);
        }
        reader.close();
        return  builder.toString();
    }


    private static class SectionRaw {

        private final String name;
        private final String sectionData;

        SectionRaw(String name, String sectionData) {
            this.name = name;
            this.sectionData = sectionData;
        }

        String getName() {
            return name;
        }

        String getSectionData() {
            return sectionData;
        }
    }
}
