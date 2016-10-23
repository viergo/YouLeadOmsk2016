package ru.stairenx.viergo.youleadomsk.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.ItemPack.NewsItem;
import ru.stairenx.viergo.youleadomsk.ItemPack.OrgItem;
import ru.stairenx.viergo.youleadomsk.ItemPack.ProgramItem;
import ru.stairenx.viergo.youleadomsk.ItemPack.SpeakerItem;
import ru.stairenx.viergo.youleadomsk.ItemPack.UsersItem;

/**
 * Created by viergo on 26.09.16.
 */
public class DataBaseAction {


    private static CreateDataBase database ;
    private static SQLiteDatabase sqLiteDatabase;
    private static Context context;
    private static String LOGG = "LOG********GET TASKS***********";


    public static void initContext(Context c){
        context = c;
    }

    public static Context getContext(){
        return context;
    }


    public static List<SpeakerItem> getSpeaker() {
        List<SpeakerItem> list = new ArrayList<>();
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();

        String text = "SELECT * FROM " + CreateDataBase.TABLE_SPEAKER + ";";

        Cursor cursor = sqLiteDatabase.rawQuery(text, null);
        while (cursor.moveToNext()) {
            list.add(new SpeakerItem(
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_SP_IMAGE)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_SP_NAME)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_SP_TITLE)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_SP_INFO))
            ));
        }
            return list;
    }

    private static void addSpeaker(String img, String name, String title, String info){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "INSERT INTO "+CreateDataBase.TABLE_SPEAKER+" ( " +
                "image, name, title, info )"+" VALUES ( '"+
                img+"' , '"+
                name+"' , '"+
                title+"' , '"+
                info+
                "' );";
        sqLiteDatabase.execSQL(textQuery);
    }

    public static void addAllSpeakers(){
        sqLiteDatabase.execSQL("DELETE FROM speakers");
        addSpeaker("http://stairenx.ru/res/api/youlead/speaker_img/zatolokin.png","Артем Затолокин","Блок: \nЗа границами возможностей.","Деятельность: \nБизнес-тренер, проектный консультант, продюсер проектов, игротехник, предприниматель, общественный деятель и управляющий партнер в Start&Fly! ");
        addSpeaker("http://stairenx.ru/res/api/youlead/speaker_img/grinberg.png","Марк Гринберг","Блок: \nМолодежь как энергия будущего.","Деятельность: \nБизнес-тренер, психотерапевт, Руководитель Центра психологического сопровождения бизнеса ЛЕГЕ АРТИС.");
        addSpeaker("http://stairenx.ru/res/api/youlead/speaker_img/pritulyak.png","Иван Притуляк","Блок: \nРеализуя энергию мечты.","Деятельность: \nАктёр, продюсер, радио и телеведущий.");
        addSpeaker("http://stairenx.ru/res/api/youlead/speaker_img/dubovez.png","Максим Дубовец","Блок: \nОт мысли к действию.","Деятельность: \nПсихоаналитик, бизнес-тренер.");
        addSpeaker("http://stairenx.ru/res/api/youlead/speaker_img/belyavskiy.png","Павел Белявский","Блок: \nПлатформа успеха","Деятельность: \nБизнес тренер, НЛП практик.");
        addSpeaker("http://stairenx.ru/res/api/youlead/speaker_img/myachin.png","Сергей Мячин","Блок: \nМоя мечта","Деятельность: \nпреподаватель кафедры математики и информационных технологий при ОмГУ им. Ф.М. Достоевского.");
    }

    public static List<OrgItem> getOrgs() {
        List<OrgItem> list = new ArrayList<>();
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();

        String text = "SELECT * FROM " + CreateDataBase.TABLE_ORG + ";";

        Cursor cursor = sqLiteDatabase.rawQuery(text, null);
        while (cursor.moveToNext()) {
            list.add(new OrgItem(
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_ORG_IMG)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_ORG_NAME)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_ORG_POSITION)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_ORG_EDU)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_ORG_DATE))
            ));
        }
        return list;
    }

    public static void addOrg(String img, String name, String position, String edu, String date){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "INSERT INTO "+CreateDataBase.TABLE_ORG+" ( " +
                "image, name, position, edu, date )"+" VALUES ( '"+
                img+"' , '"+
                name+"' , '"+
                position+"' , '"+
                edu+"' , '"+
                date+
                "' );";
        sqLiteDatabase.execSQL(textQuery);
    }

    public static void addAllOrg(){
        sqLiteDatabase.execSQL("DELETE FROM orgs");
        addOrg(Constants.grikImage, Constants.grikName,Constants.grikPosition,Constants.grikEdu,"");
        addOrg(Constants.nataImage, Constants.nataName,Constants.nataPosition,Constants.nataEdu,"");
        addOrg(Constants.lonImage, Constants.lonName,Constants.lonPosition,Constants.lonEdu,"");
        addOrg(Constants.lanImage, Constants.lanName,Constants.lanPosition,Constants.lanEdu,"");
        addOrg(Constants.bakImage, Constants.bakName,Constants.bakPosition,Constants.bakEdu,"");
    }

    public static List<NewsItem> getNews(){
        List<NewsItem> news = new ArrayList<>();
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_NEWS+" ORDER BY _id DESC;";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
            news.add(new NewsItem(
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_IMAGE_AUTHOR)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_AUTHOR)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_DATE)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_TEXT)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_IMG))
            ));
        }
        cursor.close();
        return news;
    }

    public static void addNews(int idServer, String imgA, String author, String date, String text, String img){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "INSERT INTO news (server,imgAuthor,author,dateNews,textNews,img) VALUES ( "+
                idServer+" , '"+
                imgA+"' , '"+
                author+"' , '"+
                date+"' , '"+
                text+"' , '"+
                img+
                "' );";
        sqLiteDatabase.execSQL(textQuery);
    }


        public static ArrayList<ProgramItem> getProgramDay(String table){
            ArrayList<ProgramItem> arrayList = new ArrayList<>();
            database = new CreateDataBase(context);
            sqLiteDatabase = database.getWritableDatabase();

            String textQuery = "SELECT * FROM " +table+";";

            Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
            while (cursor.moveToNext()) {
                arrayList.add(new ProgramItem(
                        cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_TIME)),
                        cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_PROGRAM)),
                        cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_SPEAKER)),
                        cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_BACKGROUND))

                ));
            }
            cursor.close();
            return arrayList;
        }

    public static void addProgramDay(String table, String time,String program, String title,String speaker, String background){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "INSERT INTO "+table+" ( " +
                "time, program, title, speaker, background )"+" VALUES ( '"+
                time+"' , '"+
                program+"' , '"+
                title+"' , '"+
                speaker+"' , '"+
                background+
                "' );";
        sqLiteDatabase.execSQL(textQuery);
    }

    public static void dropAllTable(){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "DELETE FROM "+CreateDataBase.TABLE_NAME;
        String textQuery2 = "DELETE FROM "+CreateDataBase.TABLE_NAME_2;
        String textQuery3 = "DELETE FROM "+CreateDataBase.TABLE_NAME_3;
        sqLiteDatabase.execSQL(textQuery);
        sqLiteDatabase.execSQL(textQuery2);
        sqLiteDatabase.execSQL(textQuery3);
    }

    public static int getLastNewsId(){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        int id;
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_NEWS+" ORDER BY _id DESC;";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_SERVER_ID));
        cursor.close();
        return id;
    }

    public static void addUsers(int id,String name,String img,String info,String email){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "INSERT INTO users ( server,name, img, info,email) VALUES ( "+
                id+" , '"+
                name+"' , '"+
                img+"' , '"+
                info+"' , '"+
                email+
                "' );";
        sqLiteDatabase.execSQL(textQuery);
    }

    public static List<UsersItem> getUsers(){
        List<UsersItem> list = new ArrayList<>();
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_USERS+";";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
            list.add(new UsersItem(
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_NAME)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_IMG)),
                    cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_INFO))
            ));
        }
        cursor.close();
        return list;
    }

    public static int getLastUsersId(){
        int id;
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_USERS+" ORDER BY _id DESC;";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex(CreateDataBase.COLUMN_NEWS_SERVER_ID));
        cursor.close();
        return id;
    }

    public static int getLogin() throws Exception{
        int result;
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_LOGIN+" ;";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        result = cursor.getCount();
        cursor.close();
        Log.d("Кол-во записей в Login",String.valueOf(result));
        return result;
    }

    public static void addLogin(String phone, String name, String img, String email,String info){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String dl = "DELETE FROM login";
        sqLiteDatabase.execSQL(dl);
        String textQuery = "INSERT INTO login ( phone,name,img,email,info) VALUES ( "+
                phone+" , '"+
                name+"' , '"+
                img+"' , '"+
                email+"' , '"+
                info+
                "' );";
        sqLiteDatabase.execSQL(textQuery);
    }
    public static void deleteLogin(){
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String dl = "DELETE FROM login";
        sqLiteDatabase.execSQL(dl);
    }
    public static String getLoginPhone(){
        String info = "";
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_LOGIN+";";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
                    info = cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_PHONE));
        }
        cursor.close();
        return info;
    }
    public static String getLoginName(){
        String info = "";
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_LOGIN+";";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
            info = cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_NAME));
        }
        cursor.close();
        return info;
    }
    public static String getLoginEmail(){
        String info = "";
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_LOGIN+";";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
            info = cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_EMAIL));
        }
        cursor.close();
        return info;
    }
    public static String getLoginInfo(){
        String info = "";
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_LOGIN+";";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
            info = cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_INFO));
        }
        cursor.close();
        return info;
    }
    public static String getLoginImg(){
        String info = "";
        database = new CreateDataBase(context);
        sqLiteDatabase = database.getWritableDatabase();
        String textQuery = "SELECT * FROM " +CreateDataBase.TABLE_LOGIN+";";
        Cursor cursor = sqLiteDatabase.rawQuery(textQuery, null);
        while (cursor.moveToNext()) {
            info = cursor.getString(cursor.getColumnIndex(CreateDataBase.COLUMN_USERS_IMG));
        }
        cursor.close();
        return info;
    }
}
