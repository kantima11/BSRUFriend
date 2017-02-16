package com.example.windows.bsrufriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Explicit การประกาศตัวเเปร
    private Button signInButton, signUpButton;
    private EditText userEditText, passEditText;
    private String userString, passString;
    private String[] loginString;
    private static final String urlPHP = "http://swiftcodingthai.com/bsru/get_user_ying.php";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Widget คือการ Initial Var กับ View บน XML
        signInButton = (Button) findViewById(R.id.button2);
        signUpButton = (Button) findViewById(R.id.button);
        userEditText = (EditText) findViewById(R.id.editText);
        passEditText = (EditText) findViewById(R.id.editText2);

        // Button Controller
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check Space and get Value from Edit Text
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();
                if (userString.equals("") || passString.equals("")) {
                    //Have space
                    MyAlert myAlert = new MyAlert(MainActivity.this);
                    myAlert.myDialog("มีช่องว่าง", "กรุณากรอกทุกช่องค่ะ");
                } else {
                    //No Space
                    checkUserPass();
                }

            }   // onClick
        });

    } // Main Method

    private void checkUserPass() {
        try {

            GetUser getUser = new GetUser(MainActivity.this);
            getUser.execute(urlPHP);
            String strJSON = getUser.get();
            Log.d("16fabV1", "strJSON ==> " + strJSON);



        } catch (Exception e) {
            Log.d("16fabV1", "e checkUserPass ==>" + e.toString());
        }
    }   //checkUserPass

} // Main Class นี่คือ คลาสหลัก
