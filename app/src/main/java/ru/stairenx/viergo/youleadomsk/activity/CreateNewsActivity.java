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
            Log.d("^^^^",getPath(selectedImage));
            new SEND_IMG().execute(selectedImage);
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
                    ServerAction.addNews(DataBaseAction.getLoginImg(), DataBaseAction.getLoginName(),"not date", news);
                    startActivity(new Intent(CreateNewsActivity.this, MainActivity.class));
                    CreateNewsActivity.this.finish();
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

    public int uploadFile(String sourceFileUri) {
        String upLoadServerUri = "http://stairenx.ru/res/api/youlead/method/addNewsImg.php";
        String fileName = sourceFileUri;
        int serverResponseCode = 0;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 2048 * 2048;
        File sourceFile = new File(sourceFileUri);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File Does not exist");
            return 0;
        }
        try { // open a URL connection to the Servlet
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(upLoadServerUri);
            conn = (HttpURLConnection) url.openConnection(); // Open a HTTP
            // connection to
            // the URL
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);
            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name="+fileName+";filename="+fileName+""+lineEnd);
            dos.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available(); // create a buffer of
            // maximum size

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

            Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage
                    + ": " + serverResponseCode);
            if (serverResponseCode == 200) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        // tv.setText("File Upload Completed.");
                        Toast.makeText(CreateNewsActivity.this,
                                "File Upload Complete.", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }

            // close the streams //CreateNews
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            Toast.makeText(CreateNewsActivity.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(CreateNewsActivity.this,
                    "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Upload file Exception",
                    "Exception : " + e.getMessage(), e);
        }
        return serverResponseCode;
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

    private class SEND_IMG extends AsyncTask<Uri,Void,Void> {
        @Override
        protected Void doInBackground(Uri... params) {
            Uri imgUri = null;
            for(Uri uriAr : params) {
                imgUri = uriAr;
            }
            uploadFile(getPath(imgUri));
            return null;
        }
    }
}
