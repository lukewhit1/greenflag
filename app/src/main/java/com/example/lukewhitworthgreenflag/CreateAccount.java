package com.example.lukewhitworthgreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText etEmail;
    EditText etCreatePwd;
    EditText etRepeatPwd;
    Button btnNext;
    Button btnBack;
    TextView tvBadEmail;
    TextView tvMismatch;
    TextView tvBadPass;
    boolean validEmail = false;
    boolean validPass = false;
    boolean matchPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etEmail = findViewById(R.id.et_email);
        etCreatePwd = findViewById(R.id.et_create_pwd);
        etRepeatPwd = findViewById(R.id.et_repeat_pwd);

        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);

        tvBadEmail = findViewById(R.id.tv_invalid_email);
        tvBadPass = findViewById(R.id.tv_invalid_pwd);
        tvMismatch = findViewById(R.id.tv_mismatch_pwd);

        btnBack.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.setClass(CreateAccount.this, MainActivity.class);
                startActivity(intent);
        });

        btnNext.setOnClickListener(view -> {
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = etEmail.getText().toString();
                if (!isValidEmailAddress(email)) {
                    validEmail = false;
                    tvBadEmail.setVisibility(View.VISIBLE);
                    etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    etEmail.setBackgroundResource(R.drawable.et_redborder);
                    btnNext.setEnabled(false);
                    //TODO: borders
                }
                else {
                    validEmail = true;
                    tvBadEmail.setVisibility(View.GONE);
                    etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    etEmail.setBackgroundResource(R.drawable.et_greenborder);
                    if (validPass && matchPass) {
                        btnNext.setEnabled(true);
                    }
                }
            }

        });

        etCreatePwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pass = etCreatePwd.getText().toString();
                if (!isValidPass(pass)) {
                    validPass = false;
                    tvBadPass.setVisibility(View.VISIBLE);

                    etCreatePwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    etRepeatPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    etCreatePwd.setBackgroundResource(R.drawable.et_redborder);
                    etRepeatPwd.setBackgroundResource(R.drawable.et_redborder);

                    btnNext.setEnabled(false);
                }
                else {
                    validPass = true;
                    tvBadPass.setVisibility(View.GONE);
                    if (matchPass) {
                        etCreatePwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        etRepeatPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

                        etCreatePwd.setBackgroundResource(R.drawable.et_greenborder);
                        etRepeatPwd.setBackgroundResource(R.drawable.et_greenborder);

                        if (validEmail) {
                            btnNext.setEnabled(true);
                        }
                    }
                }
            }
        });

        etRepeatPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!passMatch()) {
                    matchPass = false;
                    tvMismatch.setVisibility(View.VISIBLE);

                    etCreatePwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    etRepeatPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    etCreatePwd.setBackgroundResource(R.drawable.et_redborder);
                    etRepeatPwd.setBackgroundResource(R.drawable.et_redborder);

                    btnNext.setEnabled(false);
                }
                else {
                    matchPass = true;
                    tvMismatch.setVisibility(View.GONE);
                    if (validPass) {
                        etCreatePwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        etRepeatPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

                        etCreatePwd.setBackgroundResource(R.drawable.et_greenborder);
                        etRepeatPwd.setBackgroundResource(R.drawable.et_greenborder);

                        if (validEmail) {
                            btnNext.setEnabled(true);
                        }
                    }

                }
            }
        });
    }

    public boolean isValidEmailAddress(String email) {
        // copied this regex from te internet
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidPass(String pass) {
        String ePattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(pass);
        return m.matches();
    }

    public boolean passMatch() {
        String pass = etCreatePwd.getText().toString();
        String confirm = etRepeatPwd.getText().toString();
        return (pass.equals(confirm));
    }
}