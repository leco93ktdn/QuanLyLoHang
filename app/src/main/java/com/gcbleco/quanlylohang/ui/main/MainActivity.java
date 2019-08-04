package com.gcbleco.quanlylohang.ui.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.gcbleco.quanlylohang.R;
import com.gcbleco.quanlylohang.data.db.model.LoHangModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvFileMoi, lvHoanTat, lvTong;
//    private TextView txtSoFile, txtTongUng, txtDaChi, txtConLai;
    private List<LoHangModel> dsLoHang, dsLoHangMoi, dsLoHangHoanTat;
//    private List<GiaoDichModel> giaoDich;
    private LoHangAdapter loHangAdapter, loHangMoiAdapter, loHoanTatAdapter;
//    private TabHost tabHost;

    public static String DATABASE_NAME = "dbLohang.db";
    public static final String DB_PATH_SUFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xuLySaoChepCSDLTuAssetsVaoHeThongMobile();
        addControls();
        addEvent();
        showOnListView();
    }

    //EventBus: dang ky khi vao activity nay
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //EventBus: huy dang ky khi ko con vao activity nay
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void showOnListView() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        //Truy van bang Query
        Cursor cursor = database.query("Lohang", null, null, null, null, null, null);
//        //Truy van bang RawQuery
//        Cursor cursor = database.rawQuery("Select * from Contact", null);
        dsLoHang.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String soFile = cursor.getString(1);
            int tongUng = cursor.getInt(2);
            int tongChi = cursor.getInt(3);
            int conLai = cursor.getInt(4);
            int checkHoanThanh = cursor.getInt(5);

            LoHangModel loHang = new LoHangModel();
            loHang.setSoFile(soFile);
            loHang.setTongUng(tongUng);
            loHang.setTongChi(tongChi);
            loHang.setConLai(conLai);
            loHang.setCheckHoanThanh(checkHoanThanh == 1);
            dsLoHang.add(loHang);
//            dsLoHang.add(new LoHangModel(soFile, tongUng, tongChi, conLai, checkHoanThanh, null));
        }
        cursor.close();
        loHangAdapter.notifyDataSetChanged();
    }

    private void addEvent() {
        xyLyBoCheck();
        xuLyCheck();
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                if (tabId.equalsIgnoreCase("Tab3")) {
//                    showOnListView();
//                } else if (tabId.equalsIgnoreCase("Tab2")) {
//                    xyLyBoCheckHoanTat();
//                } else if (tabId.equalsIgnoreCase("Tab1")) {
//                    suLyCheckHoanTat();
//                }
//            }
//        });
    }

    public void xyLyBoCheck() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Lohang", null, "checkHoanThanh = ?", new String[]{"1"}, null, null, null);
//        Cursor cursor = database.query("Lohang", null, "checkHoanThanh = 1", null, null, null, null);
        dsLoHangMoi.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String soFile = cursor.getString(1);
            int tongUng = cursor.getInt(2);
            int tongChi = cursor.getInt(3);
            int conLai = cursor.getInt(4);
            int checkHoanThanh = cursor.getInt(5);

            LoHangModel loHang = new LoHangModel();
            loHang.setSoFile(soFile);
            loHang.setTongUng(tongUng);
            loHang.setTongChi(tongChi);
            loHang.setConLai(conLai);
            loHang.setCheckHoanThanh(checkHoanThanh == 1);
            dsLoHangMoi.add(loHang);
        }
        cursor.close();
        loHangMoiAdapter.notifyDataSetChanged();


    }

    public void xuLyCheck() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Lohang", null, "checkHoanThanh = ?", new String[]{"0"}, null, null, null);
        dsLoHangHoanTat.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String soFile = cursor.getString(1);
            int tongUng = cursor.getInt(2);
            int tongChi = cursor.getInt(3);
            int conLai = cursor.getInt(4);
            int checkHoanThanh = cursor.getInt(5);

            LoHangModel loHang = new LoHangModel();
            loHang.setSoFile(soFile);
            loHang.setTongUng(tongUng);
            loHang.setTongChi(tongChi);
            loHang.setConLai(conLai);
            loHang.setCheckHoanThanh(checkHoanThanh == 1);
            dsLoHangHoanTat.add(loHang);
//
//            if (loHang.isCheckHoanThanh()) {
//                dsLoHangMoi.add(loHang);
//            }
//            else if(!loHang.isCheckHoanThanh()){
//                dsLoHangHoanTat.add(loHang);
//            }
        }
        cursor.close();
        loHoanTatAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Tab1");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Tab2");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Tab3");

        tab1.setIndicator("Lô mới");
        tab1.setIndicator("", getResources().getDrawable(R.drawable.ic_note1));
        tab1.setContent(R.id.tab1);

        tab2.setIndicator("Hoàn tất");
        tab2.setIndicator("", getResources().getDrawable(R.drawable.ic_finish));
        tab2.setContent(R.id.tab2);

        tab3.setIndicator("Tất cả");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.ic_detail));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        lvFileMoi = findViewById(R.id.lvFileMoi);
        lvHoanTat = findViewById(R.id.lvHoanTat);
        lvTong = findViewById(R.id.lvTong);

        //==================================================
        dsLoHang = new ArrayList<>();
        loHangAdapter = new LoHangAdapter(MainActivity.this,
                R.layout.item_lohang,
                dsLoHang);
        lvTong.setAdapter(loHangAdapter);
        //==================================================
        dsLoHangMoi = new ArrayList<>();
        loHangMoiAdapter = new LoHangAdapter(MainActivity.this,
                R.layout.item_lohang,
                dsLoHangMoi);
        lvFileMoi.setAdapter(loHangMoiAdapter);
        //==================================================
        dsLoHangHoanTat = new ArrayList<>();
        loHoanTatAdapter = new LoHangAdapter(MainActivity.this,
                R.layout.item_lohang,
                dsLoHangHoanTat);
        lvHoanTat.setAdapter(loHoanTatAdapter);
    }

    private void xuLySaoChepCSDLTuAssetsVaoHeThongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Sao chep thanh cong", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("Loi_SaoChep", ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFIX + DATABASE_NAME;
    }

    //EventBus: nhan su kien thay doi khi click checkbox
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoHangAdapter.MessageEvent event) {
        loHoanTatAdapter.notifyDataSetChanged();
        loHangMoiAdapter.notifyDataSetChanged();
        loHangAdapter.notifyDataSetChanged();
    }
}
