//package com.example.admincollegeapp;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.CalendarContract;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//public class uploadNotice extends AppCompatActivity {
//
//    private CardView addImage;
//    private EditText noticeTitle;
//    private final int REQ = 1;
//    private Bitmap bitmap;
//    private ImageView noticeImageView;
//    private Button uploadNoticeBtn;
//    private DatabaseReference reference,dbref;
//    private StorageReference storageReference;
//    String downloadUrl = "";
//    private ProgressDialog pd;
//
//
//    /** @noinspection deprecation*/
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_notice);
//
//        addImage = findViewById(R.id.addImage);
//        noticeImageView = findViewById(R.id.noticeImageView);
//        noticeTitle = findViewById(R.id.noticeTitle);
//        uploadNoticeBtn = findViewById(R.id.uploadNoticeBtn);
//        reference = FirebaseDatabase.getInstance().getReference();
//        storageReference = FirebaseStorage.getInstance().getReference();
//
//         pd = new ProgressDialog(this);
//
//        addImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openGallery();
//            }
//        });
//
//        uploadNoticeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (noticeTitle.getText().toString().isEmpty()){
//                    noticeTitle.setError("Empty");
//                    noticeTitle.requestFocus();
//                }else if (bitmap == null){
//                    uploadData();
//                }else {
//                    uploadImage();
//                }
//            }
//        });
//    }
//
//
//
//    private void uploadImage() {
//        //noinspection deprecation
//        pd.setMessage("Uploading...");
//                pd.show();
//                ByteArrayOutputStream baos = new  ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
//                byte[] finalImage = baos.toByteArray();
//
//                final StorageReference filePath;
//                filePath = storageReference.child("Notice").child(finalImage+"jpg");
//                final UploadTask uploadTask = filePath.putBytes(finalImage);
//                uploadTask.addOnCompleteListener(uploadNotice.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                        if (task.isSuccessful()){
//                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            downloadUrl = String.valueOf(uri);
//                                            uploadData();
//                                            pd.dismiss();
//                                        }
//                                    });
//                                }
//                            });
//                        }else {
//                            pd.dismiss();
//                            Toast.makeText(uploadNotice.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//
//            private void uploadData() {
//                dbref = reference.child("Notice");
//                final String uniqueKey = dbref.push().getKey();
//
//                String title = noticeTitle.getText().toString();
//                Calendar callForDate = Calendar.getInstance();
//                SimpleDateFormat currentDate = new SimpleDateFormat("dd--MM--yyyy");
//                String date = currentDate.format(callForDate.getTime());
//
//                Calendar calForTime = Calendar.getInstance();
//                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
//                String time = currentTime.format(calForTime.getTime());
//
//                NoticeData noticeData = new NoticeData(title,downloadUrl,date,time,uniqueKey);
//
//                dbref.child(uniqueKey).setValue(noticeData).addOnSuccessListener(unused -> {
//                    Toast.makeText(uploadNotice.this, "Notice Uploaded", Toast.LENGTH_SHORT).show();
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        pd.dismiss();
//                        Toast.makeText(uploadNotice.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//    private void openGallery() {
//        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        //noinspection deprecation
//        startActivityForResult(pickImage,REQ);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQ  && resultCode == RESULT_OK){
//            Uri uri = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            noticeImageView.setImageBitmap(bitmap);
//        }
//    }
//}


package com.example.admincollegeapp.notice;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admincollegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class uploadNotice extends AppCompatActivity {

    private CardView addImg;
    private Button uploadnoticeBtn;
    private Bitmap bitmap;
    private DatabaseReference reference,dbRef;
    private StorageReference storageReference;
    private EditText noticeTitle;
    private ImageView noticeimageview;
    private final int Req = 1;
    String downloadUrl = "";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);

        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        addImg = findViewById(R.id.addImage);
        uploadnoticeBtn = findViewById(R.id.uploadNoticeBtn);
        noticeTitle = findViewById(R.id.noticeTitle);
        pd = new ProgressDialog(this);
        noticeimageview = findViewById(R.id.noticeImageView);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        uploadnoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noticeTitle.getText().toString().isEmpty()){
                    noticeTitle.setError("Empty");
                    noticeTitle.requestFocus();
                }
                else if(bitmap==null){
                    uploadData();
                }else {
                    uploadImage();
                }
            }
        });
    }



    private void uploadImage() {
            pd.setMessage("Uploading...");
            pd.show();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,70,baos);
            byte[] finalimg = baos.toByteArray();
            final StorageReference filePath;
            filePath = storageReference.child("Notice").child(finalimg+"jpg");
            final UploadTask uploadTask = filePath.putBytes(finalimg);
            uploadTask.addOnCompleteListener(uploadNotice.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        downloadUrl = String.valueOf(uri);
                                        uploadData();
                                    }
                                });
                            }
                        });
                    }else {
                        pd.dismiss();
                        Toast.makeText(uploadNotice.this, "Something went wrong in storage!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    private void uploadData() {
        pd.setMessage("Uploading...");
        pd.show();
        dbRef = reference.child("Notice");
        final String uniquekey = dbRef.push().getKey();

        String title = noticeTitle.getText().toString();

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calForTime.getTime());


        NoticeData noticeData = new NoticeData(title,downloadUrl,date,time,uniquekey);
        dbRef.child(title + uniquekey).setValue(noticeData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(uploadNotice.this, "Notice uploaded", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(uploadNotice.this, "Something went wrong in upload data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent pickimg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimg,Req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Req && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            noticeimageview.setImageBitmap(bitmap);
        }
    }
}