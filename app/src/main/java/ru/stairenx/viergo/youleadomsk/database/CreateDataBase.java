package ru.stairenx.viergo.youleadomsk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by viergo on 26.09.16.
 */
public class CreateDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="stairenx_youlead";
    public static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME = "firstday";
    public static final String TABLE_NAME_2 = "seconddaycareer";
    public static final String TABLE_NAME_3 = "seconddayyouth";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_PROGRAM = "program";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SPEAKER= "speaker";
    public static final String COLUMN_BACKGROUND = "background";


    public static final String TABLE_SPEAKER = "speakers";

    public static final String COLUMN_SP_NAME = "name";
    public static final String COLUMN_SP_IMAGE = "image";
    public static final String COLUMN_SP_TITLE = "title";
    public static final String COLUMN_SP_INFO = "info";


    public static final String TABLE_ORG = "orgs";

    public static final String COLUMN_ORG_IMG = "image";
    public static final String COLUMN_ORG_NAME = "name";
    public static final String COLUMN_ORG_POSITION = "position";
    public static final String COLUMN_ORG_EDU = "edu";
    public static final String COLUMN_ORG_DATE = "date";

    public static final String TABLE_NEWS = "news";

    public static final String COLUMN_NEWS_SERVER_ID = "server";
    public static final String COLUMN_NEWS_IMAGE_AUTHOR = "imgAuthor";
    public static final String COLUMN_NEWS_AUTHOR = "author";
    public static final String COLUMN_NEWS_DATE = "dateNews";
    public static final String COLUMN_NEWS_TEXT = "textNews";
    public static final String COLUMN_NEWS_IMG = "img";

    public static final String TABLE_USERS = "users";

    public static final String COLUMN_USERS_PHONE = "phone";
    public static final String COLUMN_USERS_NAME = "name";
    public static final String COLUMN_USERS_IMG = "img";
    public static final String COLUMN_USERS_INFO = "info";
    public static final String COLUMN_USERS_EMAIL = "email";


    public static final String TABLE_LOGIN = "login";

    public static final String COLUMN_LOGIN_PHONE = "phone";
    public static final String COLUMN_LOGIN_NAME = "name";
    public static final String COLUMN_LOGIN_IMG = "img";
    public static final String COLUMN_LOGIN_EMAIL = "email";
    public static final String COLUMN_LOGIN_INFO = "info";



    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_TIME+" text, "+
            COLUMN_PROGRAM+" text,"+
            COLUMN_TITLE+" text, "+
            COLUMN_SPEAKER+" text, "+
            COLUMN_BACKGROUND+" text);";
    private static final String CREATE_TABLE_2 = "CREATE TABLE "+TABLE_NAME_2+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_TIME+" text, "+
            COLUMN_PROGRAM+" text,"+
            COLUMN_TITLE+" text, "+
            COLUMN_SPEAKER+" text, "+
            COLUMN_BACKGROUND+" text);";
    private static final String CREATE_TABLE_3 = "CREATE TABLE "+TABLE_NAME_3+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_TIME+" text, "+
            COLUMN_PROGRAM+" text,"+
            COLUMN_TITLE+" text, "+
            COLUMN_SPEAKER+" text, "+
            COLUMN_BACKGROUND+" text);";
    private static final String CREATE_TABLE_SPEAKERS = "CREATE TABLE "+TABLE_SPEAKER+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_SP_NAME+" text, "+
            COLUMN_SP_IMAGE+" text,"+
            COLUMN_SP_TITLE+" text, "+
            COLUMN_SP_INFO+" text);";
    private static final String CREATE_TABLE_ORG = "CREATE TABLE "+TABLE_ORG+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_ORG_IMG+" text, "+
            COLUMN_ORG_NAME+" text,"+
            COLUMN_ORG_POSITION+" text, "+
            COLUMN_ORG_EDU+" text, "+
            COLUMN_ORG_DATE+" text);";
    private static final String CREATE_TABLE_NEWS = "CREATE TABLE "+TABLE_NEWS+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_NEWS_SERVER_ID+" int, "+
            COLUMN_NEWS_IMAGE_AUTHOR+" text, "+
            COLUMN_NEWS_AUTHOR+" text, "+
            COLUMN_NEWS_DATE+" text, "+
            COLUMN_NEWS_TEXT+" text, "+
            COLUMN_NEWS_IMG+" text);";
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "+TABLE_LOGIN+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_LOGIN_PHONE+" text, "+
            COLUMN_LOGIN_EMAIL+" text, "+
            COLUMN_LOGIN_INFO+" text, "+
            COLUMN_LOGIN_NAME+" text, "+
            COLUMN_LOGIN_IMG+" text);";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+TABLE_USERS+
            " ( "+COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_NEWS_SERVER_ID+" int, "+
            COLUMN_USERS_PHONE+"text, "+
            COLUMN_USERS_NAME+" text, "+
            COLUMN_USERS_IMG+" text, "+
            COLUMN_USERS_INFO+" text, "+
            COLUMN_USERS_EMAIL+" text);";


    public CreateDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_2);
        db.execSQL(CREATE_TABLE_3);
        db.execSQL(CREATE_TABLE_SPEAKERS);
        db.execSQL(CREATE_TABLE_ORG);
        db.execSQL(CREATE_TABLE_NEWS);
        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL("INSERT INTO news (server,imgAuthor,author,dateNews,textNews, img) VALUES (1, 'http://stairenx.ru/res/img/iam.jpg', 'Глеб Шушарин', '10:44 / 08.10.16', 'Вот и вышло приложение для всероссийского молодёжного форума YouLead в Омске.\n" +
                "Пользуйтесь, делитесь новостями касаемых YouLead, находите новых знакомых, обменивайтесь контактами и Реализуйте Энергию своей Мечты!', 'http://stairenx.ru/res/api/youlead/img_news/baner_stairenx_green_430x269.png')");
        db.execSQL("INSERT INTO users (server, name, img,info) VALUES (1, 'Глеб Шушарин', 'http://stairenx.ru/res/img/iam.jpg', 'Занимаюсь мобильной и web разработкой. Учусь в Летном. Разбираюсь в электронике. Слежу на новостями в сфере технических и IT стартапов.')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SPEAKER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ORG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NEWS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


}