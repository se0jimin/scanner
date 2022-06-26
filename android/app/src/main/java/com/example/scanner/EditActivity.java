package com.example.scanner;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditActivity extends Activity {

    //        RecyclerView recyclerView;
    ImageView imageView;
    ImageView hori1;
    ImageView hori2;
    ImageView hori3;
    String imageNum = "";
//        int images[] = {R.drawable.bear, R.drawable.dog, R.drawable.cat, R.drawable.elephant};
//        String names[] = {"William", "Brian", "Elizabeth", "Job"};
//        String description[] = {"This is a bear", "This is a dog", "This is a cat","This is an elephant"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        imageView = (ImageView)findViewById(R.id.imageView);
        ImageView cameraPic = (ImageView)findViewById(R.id.cameraPic);
        ImageView trashPic = (ImageView)findViewById(R.id.trashPic);
        ImageView checkPic = (ImageView)findViewById(R.id.checkPic);
        hori1 = (ImageView)findViewById(R.id.hori1);
        hori2 = (ImageView)findViewById(R.id.hori2);
        hori3 = (ImageView)findViewById(R.id.hori3);

        ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();
        imageViewList.add(hori1);
        imageViewList.add(hori2);
        imageViewList.add(hori3);


        if (HomeActivity.savedImages.size() == 1) {
            hori1.setImageBitmap(HomeActivity.savedImages.get(0));
        }

//        if (HomeActivity.savedImages.size() < 4) {
//            for (int i = 0; i < HomeActivity.savedImages.size(); i++) {
//                Uri photoUri = HomeActivity.savedImages.get(i);
//                ImageView me = imageViewList.get(i);
//                me.setImageURI(photoUri);
//            }
//        }


//                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//                recyclerView.setHasFixedSize(true);
//                RecyclerAdapter recyclerAdapter = new RecyclerAdapter (names, this, images, description);
//                recyclerView.setAdapter(recyclerAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
        trashPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        checkPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), CompileActivity.class);
                //startActivity(intent);
            }
        });

       // request for camera acesss
        if (ContextCompat.checkSelfPermission(
                EditActivity.this,
                Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED) { // != "not"
            // if camera is not granted then the following code will follow up for request permissions
            ActivityCompat.requestPermissions(EditActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 0);


            //press button to move to Camera
            cameraPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File filePath = null;
                    try {
                        if(!dir.exists()) {
                            dir.mkdirs();
                        }
                        System.out.println(dir);
                        filePath = File.createTempFile("IMG", ".jpg", dir);

                        if(!filePath.exists()) {
                            filePath.createNewFile();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Uri photoUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                            BuildConfig.APPLICATION_ID + ".provider", filePath);
                    Pattern pat = Pattern.compile("IMG(\\w+)");
                    Matcher matcher = pat.matcher(photoUri.toString());
                    if (matcher.find())
                    {
                        System.out.println(matcher.group(1));
                        imageNum = matcher.group(1);
                    }
                    Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, 100);
                }
            });
            cameraPic.callOnClick();
//            System.out.println("June 18 " + photoUri);
//            Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
//            //intent.putExtra("ImageUri", photoUri);
//
//            startActivityForResult(intent, 100);
        }
    }
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 100) {
//                Bundle bundle = data.getExtras();
//                Bitmap finalPhoto = (Bitmap) bundle.get("data");
//                imageView.setImageBitmap(finalPhoto);
                //Uri photoUri = data.getData();
                File file = new File(Environment.getExternalStorageDirectory(), "Pictures/IMG" + imageNum +".jpg");
                Uri photoUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
                HomeActivity.savedImages.add((Bitmap)data.getExtras().get("data"));
                System.out.println("meow " + HomeActivity.savedImages);
                //Uri photoUri = data.getParcelableExtra("ImageUri");
                System.out.println(photoUri);
                imageView.setImageURI(photoUri);
                //hori1.setImageURI(photoUri);
            }
        }
    }

