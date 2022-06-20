package com.example.openeyes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SoundSetting extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_setting);

        Button sound_set = (Button) findViewById(R.id.soundset);
        sound_set.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.soundset:
                        new AlertDialog.Builder(SoundSetting.this)
                                .setTitle("설정 완료")
                                .setMessage("설정이 완료되었습니다.")
                                .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                        break;
                }
            }
        });
    }

}
