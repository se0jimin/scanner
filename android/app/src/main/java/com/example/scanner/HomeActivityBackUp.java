package com.example.scanner;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Objects;

public class HomeActivityBackUp extends Activity {

    ImageView cameraPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        builder.detectFileUriExposure();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // connecting with the activity_home.xml and naming of IDS
        cameraPic = (ImageView) findViewById(R.id.cameraPic);

        RecyclerView recyclerView;

        int images[] = {R.drawable.i_sample, R.drawable.p1, R.drawable.i_sample, R.drawable.p1};
        String names[] = {"sampleA", "sampleB", "sampleC", "sampleD"};
        String description[] = {"This is a bear", "This is a dog", "This is a cat","This is an elephant"};

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter (names, this, images, description);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //request for camera acesss
        if (ContextCompat.checkSelfPermission(
                HomeActivityBackUp.this,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { // != "not"
            // if camera is not granted then the following code will follow up for request permissions
            ActivityCompat.requestPermissions(HomeActivityBackUp.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);

            // press button to move to Camera
            cameraPic.setOnClickListener(new View.OnClickListener() {
                @Override // when there is a parent class, the child kid receives all the ]
                // information, so when the child class wants to rewrite the code, we use "Override"
                public void onClick(View view) {
                    // open camera
                    System.out.println("on click");
                    Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
                   // if (intent.resolveActivity(getPackageManager()) != null) {
                     //   System.out.println("not null");
                        startActivityForResult(intent, 100);
                    //}
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 100) {
            try {
            //String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scanner";
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            //File dir = new File(dirPath);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            System.out.println(dir);
            File filePath = File.createTempFile("IMG", ".jpg", dir);
            if(!filePath.exists()) {
                filePath.createNewFile();
            }

            //Uri photoUri = FileProvider.getUriForFile(this, "scanner.provider", filePath);
            Uri photoUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider", filePath);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //image download한
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); //image information를 컴 한테 줄 예정이 (data)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePath)); //image information를 컴 한테 중 예정이
            startActivityForResult(intent, 40);
            } 
            catch(Exception e) {
                e.printStackTrace();

//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                System.out.println(ex);
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                System.out.println("not null????");
//                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),BuildConfig.APPLICATION_ID + ".provider", photoFile);
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                           // Intent intent = new Intent(getApplicationContext(), EditActivity.class);
//                            Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                            startActivityForResult(intent, 40);
//                        } catch (Exception ex) {
//                        }
//                    }
//                });
//            }
        }
        if(requestCode == 40) {
            System.out.println("40");
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(intent);
                    } catch (Exception ex) {
                    }
                }
            });
        }
    }
//    //2번: image 저장 코
//    String currentPhotoPath;
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        System.out.println("!!!"+ storageDir);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
//        return image;
   }
}