package com.gcbleco.quanlylohang.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gcb Le Co.
 */
public class GiaoDichModel implements Parcelable {
    private String soFile;
    private String maGiaoDich;
    private String tenGiaoDich;
    private boolean chi;
    private boolean thu;

    private GiaoDichModel(Parcel in) {
        soFile = in.readString();
        maGiaoDich = in.readString();
        tenGiaoDich = in.readString();
        chi = in.readByte() != 0;
        thu = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(soFile);
        dest.writeString(maGiaoDich);
        dest.writeString(tenGiaoDich);
        dest.writeByte((byte) (chi ? 1 : 0));
        dest.writeByte((byte) (thu ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GiaoDichModel> CREATOR = new Creator<GiaoDichModel>() {
        @Override
        public GiaoDichModel createFromParcel(Parcel in) {
            return new GiaoDichModel(in);
        }

        @Override
        public GiaoDichModel[] newArray(int size) {
            return new GiaoDichModel[size];
        }
    };

    public String getSoFile() {
        return soFile;
    }

    public void setSoFile(String soFile) {
        this.soFile = soFile;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getTenGiaoDich() {
        return tenGiaoDich;
    }

    public void setTenGiaoDich(String tenGiaoDich) {
        this.tenGiaoDich = tenGiaoDich;
    }

    public boolean isChi() {
        return chi;
    }

    public void setChi(boolean chi) {
        this.chi = chi;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public GiaoDichModel() {
    }

    public GiaoDichModel(String soFile, String maGiaoDich, String tenGiaoDich, boolean chi, boolean thu) {
        this.soFile = soFile;
        this.maGiaoDich = maGiaoDich;
        this.tenGiaoDich = tenGiaoDich;
        this.chi = chi;
        this.thu = thu;
    }
}
