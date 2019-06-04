package com.mjq.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aidl.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        // excutorThred();
        Uri parse = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = getContentResolver().query(parse, new String[]{"contact_id", "_id"}, null, null, null);
        while (cursor.moveToNext()) {

            String contact_id = cursor.getString(0);
            String _id = cursor.getString(1);
            System.out.println("第" + _id + "个联系人ID=" + contact_id);
            if (contact_id != null) {
                String raw_contatid = String.valueOf(contact_id);
                Cursor query = getContentResolver().query(dataUri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{raw_contatid}, null);
                while (query.moveToNext()) {
                    String data1 = query.getString(0);
                    String mimetype = query.getString(1);
                    if ("vnd.android.cursor.item/name".equals(mimetype)) {
                        System.out.println("姓名" + data1);
                    } else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                        System.out.println("电话" + data1);
                    } else if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                        System.out.println("邮箱" + data1);
                    }

                }
                if (query != null) query.close();
            }

        }
        if (cursor != null) cursor.close();


    }

    private void excutorThred() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final int[] num = {0};
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    System.out.println("线程ID" + Thread.currentThread().getId());
                    System.out.println((num[0]++));
                }


            }
        });
    }
/*


    private class  MycontetProvider extends ContentProvider{

        @Override
        public boolean onCreate() {
            return false;
        }


        @Override
        public Cursor query( Uri uri,  String[] projection, @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs, @androidx.annotation.Nullable String sortOrder) {
            return null;
        }

        @androidx.annotation.Nullable
        @Override
        public String getType(@androidx.annotation.NonNull Uri uri) {
            return null;
        }

        @androidx.annotation.Nullable
        @Override
        public Uri insert(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable ContentValues values) {
            return null;
        }

        @Override
        public int delete(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs) {
            return 0;
        }

        @Override
        public int update(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable ContentValues values, @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs) {
            return 0;
        }
    }
*/
}
