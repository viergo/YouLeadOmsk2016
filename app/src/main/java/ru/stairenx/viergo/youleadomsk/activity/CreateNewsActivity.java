package ru.stairenx.viergo.youleadomsk.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.MainActivity;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

/**
 * Created by viergo on 06.10.16.
 */
public class CreateNewsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText text;
    private ImageView img;
    private static final int REQUEST = 1;
    private String link;
    private int serverResponseCode = 0;
    private String nameImg = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);
        initToolbar();
        text = (EditText) findViewById(R.id.news_text);
        img = (ImageView) findViewById(R.id.newNewsImg);
        initImgView();
        initFab();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap setImg = null;
        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                setImg = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            img.setImageBitmap(setImg);
            link = getPath(selectedImage);
            nameImg = setNameImg(link);
            new SEND_IMG().execute();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Создание новости");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initFab(){
        fab = (FloatingActionButton) findViewById(R.id.add_news);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String news = text.getText().toString();
                if(news.equals("")) {
                    Toast.makeText(CreateNewsActivity.this, "Поле не должно быть пустым", Toast.LENGTH_SHORT).show();
                }else{
                    if(nameImg.equals("")) {
                        Toast.makeText(CreateNewsActivity.this, "Прикрепите изображение к Новости", Toast.LENGTH_SHORT).show();
                    }else{
                        Time time = new Time(Time.getCurrentTimezone());
                        time.setToNow();
                        String date = time.format("%H:%M") + "  " + time.format("%d.%m.%Y");
                        ServerAction.addNews(DataBaseAction.getLoginImg(), DataBaseAction.getLoginName(), date, news, nameImg);
                        DataBaseAction.initContext(getApplicationContext());
                        ServerAction.getNews();
                        startActivity(new Intent(CreateNewsActivity.this, MainActivity.class));
                        CreateNewsActivity.this.finish();
                        }
                    }
                }
        });
    }

    private void initImgView(){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST);
            }
        });
    }

    public String getPath(Uri uri) {
        int column_index;
        String imagePath;
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);
        return imagePath;
    }

    private static String setNameImg(String res){
        String name;
        int max;
        List<String> arr = new ArrayList<>();
        StringTokenizer strToken = new StringTokenizer(res, " /");
        try {
            for (int i = 0; i <= res.length(); i++) {
                String text;
                text = strToken.nextToken();
                arr.add(text);
            }
        }catch (Exception e){
            e.getMessage();
        }
        max = arr.size() - 1;
        name = arr.get(max);
        return name;
    }

    private class SEND_IMG extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            uploadFile(link);
            return null;
        }
    }

        public int uploadFile(String sourceFileUri) {
            String fileName = sourceFileUri;
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = new File(sourceFileUri);
            if (!sourceFile.isFile()) {
                Log.e("uploadFile", "Source File not exist :"+sourceFileUri);
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(CreateNewsActivity.this, "Файл не найден", Toast.LENGTH_SHORT).show();
                    }
                });
                return 0;
            }else{
                try {
                    // open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(Constants.UPLOAD_NEWS_IMAGE);
                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("userfile", fileName);

                    dos = new DataOutputStream(conn.getOutputStream());
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name='userfile';filename='"+nameImg+"'"+lineEnd);
                    dos.writeBytes(lineEnd);

                    // create a buffer of  maximum size
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    // send multipart form data necesssary after file data...
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Responses from the server (code and message)
                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();
                    Log.i("uploadFile", "HTTP Response is : "+ serverResponseMessage + ": " + serverResponseCode);

                    if(serverResponseCode == 200){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(CreateNewsActivity.this, "Изображение добавлено",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    //close the streams //
                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(CreateNewsActivity.this, "MalformedURLException",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("server Exception", "Exception : "+ e.getMessage(), e);
                }
                return serverResponseCode;
            } // End else block
        }
    }