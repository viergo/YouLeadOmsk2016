package ru.stairenx.viergo.youleadomsk.ItemPack;

/**
 * Created by viergo on 30.09.16.
 */
public class OrgItem {
    private String img;
    private String name;
    private String position;
    private String edu;
    private String date;

    public OrgItem(String img, String name, String position, String edu, String date) {
        this.img = img;
        this.name = name;
        this.position = position;
        this.edu = edu;
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
