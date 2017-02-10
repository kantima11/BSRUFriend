package com.example.windows.bsrufriend;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;

public class SignUpActivity extends AppCompatActivity {
    //Explicit
    private EditText nameEditText, userEditText, passEditText;
    private ImageView imageView;
    private RadioGroup radioGroup;
    private Button button;
    private String nameString, userString, passString, pathImageString, nameImageString;
    private Uri uri;
    private boolean aBoolean = true;
    private int anInt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();
        //Button Controller
        buttonController();

        //Image Controller
        ImageController();

        //Radio Controller
        RadioController();


    }   //main Method

    private void RadioController() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        anInt = 0;
                        break;
                    case R.id.radioButton2:
                        anInt = 0;
                        break;
                    case R.id.radioButton3:
                        anInt = 0;
                        break;
                    case R.id.radioButton4:
                        anInt = 0;
                        break;
                    case R.id.radioButton5:
                        anInt = 0;
                        break;

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            aBoolean = false;
            uri = data.getData();
            //Setup Image Choose to ImageView
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Find Path of Image Chooe
            String[] strings = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, strings, null, null, null );

            if (cursor != null) {
                cursor.moveToFirst();
                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                pathImageString = cursor.getString(index);


            } else {
                pathImageString = uri.getPath();
            }
            Log.d("10fabV1", "pathImage ==> " + pathImageString);


        } //if

    } //onActivityResult

    private void ImageController() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "โปรดเลือกแอพดูภาพ"), 1);
            }
        });
    }

    private void buttonController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get value
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || userString.equals("") || passString.equals("")) {
                    // True ==> Have Space
                    MyAlert myAlert = new MyAlert(SignUpActivity.this);
                    myAlert.myDialog("มีช่องว่าง", "กรุณากรอกให้ครบทุกช่องค่ะ");
                } else if (aBoolean) {
                    //non
                    MyAlert myAlert1 = new MyAlert(SignUpActivity.this);
                    myAlert1.myDialog("ยังไม่เลือกรูปภาพ", "กรุณาเลือกรูปภาพสิค่ะ");


                } else {
                 //
                    uploadValueToSever();
                }
            }   // onClick
        });
    }

    private void uploadValueToSever() {
        try {
            //Upload Image
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                    .Builder()
                    .permitAll()
                    .build();
            StrictMode.setThreadPolicy(policy);
            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.Swiftcodingthai.com", 21, "bsru@swiftcodingthai.com", "Abc12345");
            simpleFTP.bin();
            simpleFTP.cwd("Image_Ying");
            simpleFTP.stor(new File(pathImageString));
            simpleFTP.disconnect();

            //upload Text
            String tag = "10fabV2";
            Log.d(tag, "Name ==> " + nameString);
            Log.d(tag, "User ==> " + userString);
            Log.d(tag, "Password ==> " + passString);

            nameImageString = "http://swiftcodingthai.com/bsru/Image_Ying" + pathImageString.substring(pathImageString.lastIndexOf("/"));
            Log.d(tag, "Image ==> " + nameImageString);
            Log.d(tag, "Avata ==> " + anInt);


        } catch (Exception e) {
            Log.d("10fabV1", "e upload ==>" + e.toString());
        }
    }

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passEditText = (EditText) findViewById(R.id.editText5);
        imageView = (ImageView) findViewById(R.id.imageView4);
        radioGroup = (RadioGroup) findViewById(R.id.ragAvata);
        button = (Button) findViewById(R.id.button3);

    }
}
