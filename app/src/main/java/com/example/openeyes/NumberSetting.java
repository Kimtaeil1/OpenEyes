package com.example.openeyes;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class NumberSetting extends AppCompatActivity {

    Button button;
    TextView textView9;

    //sms 전송 구현
    private void sendSMS(String phoneNumber, String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    //전화번호부 구현
    private String user_phNumber, user_Name;
    private long person_id=0;
    private int id;
    public NumberSetting(){}
    public long getPerson_id(){
        return person_id;
    }
    public void setPerson_id(long id){
        this.person_id=id;
    }

    public String getUser_phNumber(){
        return user_phNumber;
    }
    public String getUser_Name(){
        return user_Name;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setUser_phNumber(String string){
        this.user_phNumber=string;
    }
    public void setUser_Name(String string){
        this.user_Name=string;
    }
    @Override
    public String toString(){
        return this.user_phNumber;
    }
    /*@Override
    public int hashCode(){
        return getPhNumberChanged().hashCode();
    }
    public String getPhNumberChanged(){
        return user_phNumber.replace("-","");
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof NumberSetting)
            return getPhNumberChanged().equals(((NumberSetting) o).getPhNumberChanged());
        return false;
    }*/

    private ListView listview = null;
    private ListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_setting);

        Switch sms = (Switch) findViewById(R.id.switch1);
        String phoneNo = "01093468985";
        String message = "김태일 님의 졸음운전 알림 대상자임을 알립니다.";
        sms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    sendSMS(phoneNo, message);
                }
            }
        });

        Button numLoad = (Button) findViewById(R.id.button4);
        numLoad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                /*Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
                onActivityResult(0, RESULT_OK, intent);*/

                //getContactList();

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            Cursor cursor = getContentResolver().query(data.getData(), new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
            cursor.moveToFirst();
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            cursor.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public ArrayList<NumberSetting> getContactList(){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.Contacts._ID
        };

        String[] selectionArgs = null;
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        Cursor cur = getContentResolver().query(uri, projection, null, selectionArgs, sortOrder);
        LinkedHashSet<NumberSetting> hashlist = new LinkedHashSet<>();
        ArrayList<NumberSetting> nums;

        if(cur.moveToFirst()){
            do{
                long person_id = cur.getLong(2);
                NumberSetting num = new NumberSetting();
                num.setUser_phNumber(cur.getString(0));
                num.setUser_Name(cur.getString(1));
                num.setPerson_id(person_id);

                hashlist.add(num);
            } while (cur.moveToNext());
        }

        nums = new ArrayList<NumberSetting>(hashlist);
        for(int i = 0; i<nums.size(); i++){
            nums.get(i).setId(i);
        }
        return nums;

    }


}


