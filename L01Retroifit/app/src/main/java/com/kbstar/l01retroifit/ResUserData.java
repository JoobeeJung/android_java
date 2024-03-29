package com.kbstar.l01retroifit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//user 목록에 대한 응답 데이터
// api/user?page=2
public class ResUserData {

    @Expose
    private int page;

    @Expose
    @SerializedName("per_page")
    private int perPage;

    @Expose
    private int total;

    @Expose
    @SerializedName("total_pages")
    private int totalPage; //total_pages

    @Expose
    List<Data> data;

    @Expose
    Support support;

    public class Data
    {
        int id;
        String email;

        @Expose
        @SerializedName("first_name")
        String firstName;

        @Expose
        @SerializedName("last_name")
        String lastName;
        String avatar;

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }

    public class Support
    {
        String url;
        String text;

        @Override
        public String toString() {
            return "Support{" +
                    "url='" + url + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResUserData{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", data=" + data +
                ", support=" + support +
                '}';
    }
}
