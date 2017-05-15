package sunith.mercari.com.mercaribysunith;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.List;
import sunith.mercari.com.mercaribysunith.model.SectionData;
import sunith.mercari.com.mercaribysunith.view.SectionHorizontalPagerAdapter;


public class HomeActivity extends AppCompatActivity implements Presenter.Listener{

    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3010;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private View rootView;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rootView = findViewById(R.id.rootView);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        presenter = new Presenter(this);

        setupFloatingButton();
        setupView();
    }

    private void setupFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingBarAction();
            }
        });
    }

    private void setupView() {
        presenter.setupView(this);
    }

    private void floatingBarAction() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA);
            if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                showCamera();
            } else {
                String[] allPermissions = {Manifest.permission.CAMERA};
                requestPermissions(allPermissions, CAMERA_PERMISSION_REQUEST_CODE);
            }
        } else {
            showCamera();
        }
    }

    private void showCamera() {
        showSnackBar("Opening camera");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    private void showSnackBar(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCamera();
                } else {
                    showSnackBar("Permission not granted for camera");
                }
            }
        }
    }

    @Override
    public void updateView(List<SectionData> sectionDatas) {
        viewPager.setAdapter(getAdapter(sectionDatas));
        tabLayout.setupWithViewPager(viewPager);

        for (int position = 0; position < sectionDatas.size(); position++) {
            String itemName = sectionDatas.get(position).getItemName();
            TabLayout.Tab tabAt = tabLayout.getTabAt(position);
            tabAt.setText(itemName);

            if (itemName.equalsIgnoreCase("all")){
                tabAt.select();
            }
        }
    }

    @Override
    public void showMessage(String message) {
        showSnackBar(message);
    }

    public SectionHorizontalPagerAdapter getAdapter(List<SectionData> sectionDatas) {
        return new SectionHorizontalPagerAdapter(sectionDatas);
    }
}
