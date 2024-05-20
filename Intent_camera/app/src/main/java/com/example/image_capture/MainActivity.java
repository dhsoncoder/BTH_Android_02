package com.example.image_capture;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import android.view.View;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.widget.ImageButton;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    ImageView myimg;
    ImageButton btncapture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimg = findViewById(R.id.myimg);
        btncapture = findViewById(R.id.btncapture);
        btncapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ACTION_IMAGE_CAPTURE);
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new
                            String[]{android.Manifest.permission.CAMERA}, 1);
                    return;
                }
                startActivityForResult(myintent,99);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == MainActivity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            myimg.setImageBitmap(photo);
        }
    }
}