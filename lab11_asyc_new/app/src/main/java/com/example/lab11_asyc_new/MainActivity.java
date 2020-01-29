package com.example.lab11_asyc_new;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    int cnt1=1;
    int cnt2=1;
    int cnt3=1;
    ImageView imageView= null;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.asyncTask);
        Button button1=findViewById(R.id.asyncTask1);
        Button button2=findViewById(R.id.asyncTask2);
        imageView=findViewById(R.id.image);
        setTitle("MY APP");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask=new AsyncTaskExample();
                if(cnt1==1)
                {
                    asyncTask.execute("https://www.gstatic.com/webp/gallery/4.sm.jpg");
                    cnt1++;

                }
                else if(cnt1==2)
                {
                    asyncTask.execute("https://html5box.com/html5lightbox/images/mountain.jpg");
                    cnt1++;
                }
                else
                {
                    asyncTask.execute("https://thumbs.dreamstime.com/b/picturesque-autumn-scenery-santa-maddalena-village-church-road-colorful-trees-meadows-foreground-mountain-peaks-159426189.jpg");
                    cnt1=cnt1-2;
                }

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask=new AsyncTaskExample();

                if(cnt2==1)
                {
                    asyncTask.execute("https://media.alienwarearena.com/media/pmdbrt-pikachu.jpg");
                    cnt2++;

                }
                else if(cnt2==2)
                {
                    asyncTask.execute("https://drawiteazy.files.wordpress.com/2016/10/shinchan2.jpg");
                    cnt2++;
                }
                else
                {
                    asyncTask.execute("https://pixy.org/src/125/1259920.jpg");
                    cnt2=cnt2-2;
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask=new AsyncTaskExample();

                if(cnt3==1)
                {
                    asyncTask.execute("https://alinadigitalservices.com/wp-content/uploads/2018/10/Best-39-Free-Animals-Vectors-for-Download.jpg");
                    cnt3++;

                }
                else if(cnt3==2)
                {
                    asyncTask.execute("https://homepages.cae.wisc.edu/~ece533/images/cat.png");
                    cnt3++;
                }
                else
                {
                    asyncTask.execute("https://www.kimballstock.com/pix/DOK/01/DOK-01-RK0658-01P.JPG");
                    cnt3=cnt3-2;
                }
            }
        });
    }
    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(MainActivity.this);
            p.setMessage("      Downloading...");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmImg;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(imageView!=null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this, "Download Complete", Toast.LENGTH_SHORT).show();
            }else {
                p.show();
            }
        }
    }
}