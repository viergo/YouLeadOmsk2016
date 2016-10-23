package ru.stairenx.viergo.youleadomsk.ItemPack;

/**
 * Created by viergo on 06.10.16.
 */
public class UsersItem {

    private String phone;
    private String name;
    private String img;
    private String info;
    private String email;

    public UsersItem(String phone, String name, String img, String info, String email) {
        this.phone = phone;
        this.name = name;
        this.img = img;
        this.info = info;
        this.email = email;
    }

    public UsersItem(String name, String img, String info) {
        this.name = name;
        this.img = img;
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
