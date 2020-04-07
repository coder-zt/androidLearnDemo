package com.zhangtao.androidlearndemo.fourth_activity_demo.dataObject;

import android.os.Parcel;
import android.os.Parcelable;

public class data_user implements Parcelable {
    private String name;
    private int age;
    private float height;
    public data_user(){

    }

    protected data_user(Parcel in) {
        name = in.readString();
        age = in.readInt();
        height = in.readFloat();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public static final Creator<data_user> CREATOR = new Creator<data_user>() {
        @Override
        public data_user createFromParcel(Parcel in) {
            return new data_user(in);
        }

        @Override
        public data_user[] newArray(int size) {
            return new data_user[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeFloat(height);
    }
}
