package com.gcbleco.quanlylohang.ui.main;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gcbleco.quanlylohang.R;
import com.gcbleco.quanlylohang.data.db.model.LoHangModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Gcb Le Co.
 */
public class LoHangAdapter extends ArrayAdapter {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */

    private Context context;
    private int resource;
    private List objects;
    private OnCheckBoxClickedListener mCallback;

    public LoHangAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(this.context).inflate(R.layout.item_lohang,parent,false);

        final LoHangModel loHang = (LoHangModel) this.objects.get(position);

        TextView soFile = view.findViewById(R.id.txtSoFile);
        TextView tongUng = view.findViewById(R.id.txtTongUng);
        TextView tongChi = view.findViewById(R.id.txtTongChi);
        TextView conLai = view.findViewById(R.id.txtConLai);
        CheckBox chkHoanTat = view.findViewById(R.id.chkHoanTat);
//        ListView lvFileMoi = view.findViewById(R.id.lvFileMoi);
//        ListView lvHoanTat = view.findViewById(R.id.lvHoanTat);
//        ListView lvTong = view.findViewById(R.id.lvTong);

        soFile.setText(loHang.getSoFile());
        tongUng.setText(loHang.getTongUng()+"");
        tongChi.setText(loHang.getTongChi()+"");
        conLai.setText(loHang.getConLai()+"");
        chkHoanTat.setChecked(loHang.isCheckHoanThanh());

        chkHoanTat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (loHang.isCheckHoanThanh()) {
                    xuLyCheckHoanTat(loHang);
                    remove(loHang);
                }
                else {
                    xuLyBoChecHoanTat(loHang);
                    remove(loHang);
                    EventBus.getDefault().post(new MessageEvent());
                }
            }
        });
        return view;
    }

    private void xuLyBoChecHoanTat(LoHangModel loHang) {
        ContentValues row = new ContentValues();
        row.put("checkHoanThanh", 0);
        MainActivity.database.update("Lohang", row, "soFile = ?", new String[]{loHang.getSoFile()});
    }

    private void xuLyCheckHoanTat(LoHangModel loHang) {
        ContentValues row = new ContentValues();
        row.put("checkHoanThanh", 1);
        MainActivity.database.update("Lohang", row, "soFile = ?", new String[]{loHang.getSoFile()});
    }

    public static class MessageEvent { /* Additional fields if needed */ }

    //xu ly goi lai ham click cho main activity
    public void OnCheckBoxClickedListener(OnCheckBoxClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    //interface de implement ben main activity
    public interface OnCheckBoxClickedListener {
        public void CheckBoxClicked();
    }

}
