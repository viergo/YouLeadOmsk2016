package ru.stairenx.viergo.youleadomsk;

/**
 * Created by viergo on 27.09.16.
 */
public class SpeakerItem {

    private String sp_img;
    private String sp_name;
    private String sp_title;
    private String sp_info;

    public SpeakerItem(String sp_img, String sp_name, String sp_title, String sp_info) {
        this.sp_img = sp_img;
        this.sp_name = sp_name;
        this.sp_title = sp_title;
        this.sp_info = sp_info;
    }

    public String getSp_img() {
        return sp_img;
    }

    public void setSp_img(String sp_img) {
        this.sp_img = sp_img;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public String getSp_title() {
        return sp_title;
    }

    public void setSp_title(String sp_title) {
        this.sp_title = sp_title;
    }

    public String getSp_info() {
        return sp_info;
    }

    public void setSp_info(String sp_info) {
        this.sp_info = sp_info;
    }
}
