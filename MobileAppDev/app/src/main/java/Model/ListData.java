package Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListData implements Parcelable {
    private String nama, umur, alamat;

    public ListData(String nama, String umur, String alamat) {
        this.nama = nama;
        this.umur = umur;
        this.alamat = alamat;
    }

    public ListData() {
        this.nama = "";
        this.umur = "";
        this.alamat = "";
    }

    protected ListData(Parcel in) {
        nama = in.readString();
        umur = in.readString();
        alamat = in.readString();
    }

    public static final Creator<ListData> CREATOR = new Creator<ListData>() {
        @Override
        public ListData createFromParcel(Parcel in) {
            return new ListData(in);
        }

        @Override
        public ListData[] newArray(int size) {
            return new ListData[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(umur);
        dest.writeString(alamat);
    }
}
