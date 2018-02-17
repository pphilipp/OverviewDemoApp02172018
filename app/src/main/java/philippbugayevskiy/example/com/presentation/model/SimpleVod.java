package philippbugayevskiy.example.com.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleVod implements Parcelable {
    public String url;
    public String guid;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.guid);
    }

    public SimpleVod(String url, String guid) {
        this.url = url;
        this.guid = guid;
    }

    protected SimpleVod(Parcel in) {
        this.url = in.readString();
        this.guid = in.readString();
    }

    public static final Creator<SimpleVod> CREATOR = new Creator<SimpleVod>() {
        @Override
        public SimpleVod createFromParcel(Parcel source) {
            return new SimpleVod(source);
        }

        @Override
        public SimpleVod[] newArray(int size) {
            return new SimpleVod[size];
        }
    };
}
