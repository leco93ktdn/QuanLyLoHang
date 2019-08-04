package com.gcbleco.quanlylohang.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Gcb Le Co.
 */
public class LoHangModel implements Parcelable {

    private String soFile;
    private int tongChi;
    private int tongUng;
    private int conLai;
    private boolean checkHoanThanh;
    List<GiaoDichModel> dsGiaoDich;

    protected LoHangModel(Parcel in) {
        soFile = in.readString();
        tongChi = in.readInt();
        tongUng = in.readInt();
        conLai = in.readInt();
        checkHoanThanh = in.readByte() != 0;
        dsGiaoDich = in.createTypedArrayList(GiaoDichModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(soFile);
        dest.writeInt(tongChi);
        dest.writeInt(tongUng);
        dest.writeInt(conLai);
        dest.writeByte((byte) (checkHoanThanh ? 1 : 0));
        dest.writeTypedList(dsGiaoDich);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoHangModel> CREATOR = new Creator<LoHangModel>() {
        @Override
        public LoHangModel createFromParcel(Parcel in) {
            return new LoHangModel(in);
        }

        @Override
        public LoHangModel[] newArray(int size) {
            return new LoHangModel[size];
        }
    };

    public String getSoFile() {
        return soFile;
    }

    public void setSoFile(String soFile) {
        this.soFile = soFile;
    }

    public int getTongChi() {
        return tongChi;
    }

    public void setTongChi(int tongChi) {
        this.tongChi = tongChi;
    }

    public int getTongUng() {
        return tongUng;
    }

    public void setTongUng(int tongUng) {
        this.tongUng = tongUng;
    }

    public int getConLai() {
        return conLai;
    }

    public void setConLai(int conLai) {
        this.conLai = conLai;
    }

    public boolean isCheckHoanThanh() {
        return checkHoanThanh;
    }

    public void setCheckHoanThanh(boolean checkHoanThanh) {
        this.checkHoanThanh = checkHoanThanh;
    }

    public List<GiaoDichModel> getDsGiaoDich() {
        return dsGiaoDich;
    }

    public void setDsGiaoDich(List<GiaoDichModel> dsGiaoDich) {
        this.dsGiaoDich = dsGiaoDich;
    }

    public LoHangModel() {
    }

    public LoHangModel(String soFile, int tongChi, int tongUng, int conLai, boolean checkHoanThanh, List<GiaoDichModel> dsGiaoDich) {
        this.soFile = soFile;
        this.tongChi = tongChi;
        this.tongUng = tongUng;
        this.conLai = conLai;
        this.checkHoanThanh = checkHoanThanh;
        this.dsGiaoDich = dsGiaoDich;
    }
}
