package com.example.scanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HomeActivity extends Activity {

    ImageView cameraPic;
    static LinkedList<Bitmap> savedImages;
    static LinkedList<Uri> savedImagesUri;
    static boolean firstTimeCalling;
    static Uri photoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        builder.detectFileUriExposure();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // connecting with the activity_home.xml and naming of IDS
        cameraPic = (ImageView) findViewById(R.id.cameraPic);
        savedImages = new LinkedList<>(); //TODO: implement save and load features
        savedImagesUri = new LinkedList<Uri>();
        firstTimeCalling = true;

        RecyclerView recyclerView;

        int images[] = {R.drawable.i_sample, R.drawable.p1, R.drawable.i_sample, R.drawable.p1};
        String names[] = {"sampleA", "sampleB", "sampleC", "sampleD"};
        String description[] = {"This is a bear", "This is a dog", "This is a cat","This is an elephant"};

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter (names, this, images, description);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        //request for camera acesss
//        if (ContextCompat.checkSelfPermission(
//                HomeActivity.this,
//                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { // != "not"
//            // if camera is not granted then the following code will follow up for request permissions
//            ActivityCompat.requestPermissions(HomeActivity.this,
//                    new String[]{
//                            Manifest.permission.CAMERA
//                    }, 100);

            // press button to move to Camera
            cameraPic.setOnClickListener(new View.OnClickListener() {
                @Override // when there is a parent class, the child kid receives all the ]
                // information, so when the child class wants to rewrite the code, we use "Override"
                public void onClick(View view) {
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

//                    // open camera
//                    System.out.println("on click");
//                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                    if(!dir.exists()) {
//                        dir.mkdirs();
//                    }
//                    File filePath = null;
//                    try {
//                        filePath = File.createTempFile("IMG", ".jpg", dir);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if(!filePath.exists()) {
//                        try {
//                            filePath.createNewFile();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    Uri photoUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
//                            BuildConfig.APPLICATION_ID + ".provider", filePath);
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //image download한
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); //image information를 컴 한테 줄 예정이 (data)
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePath)); //image information를 컴 한테 중 예정이
//                    startActivityForResult(intent, 100);
                }
            });
        }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100) { // 사진 저장용 작업
//            //사진 촬영 이후 다시 앱으로 데이터를 넘겨 받으면 저장된 파일을 로드하는 코드 without bitmap
//            // tstory 나온대로 사진을 저장시키는 작업
//            File filePath = null;
//            if(filePath != null) {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                try {
//                    InputStream in = new FileInputStream(filePath);
//                    BitmapFactory.decodeStream(in, null, options);
//                    in.close();
//                    in = null;
//                } catch ( Exception e ) {
//                    e.printStackTrace();            }
//                final int width = options.outWidth;
//                final int height = options.outHeight;
//                // width, height 값에 따라 inSaampleSize 값 계산
//                BitmapFactory.Options imgOptions = new BitmapFactory.Options();
//                imgOptions.inSampleSize = inSampleSize;
//                Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), imgOptions);
//                resultImageView.setImageBitmap(bitmap);
//            }
//        }
//        if(requestCode == 40) {
//            System.out.println("40");
//            final Handler handler = new Handler(Looper.getMainLooper());
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
//                        startActivity(intent);
//                    } catch (Exception ex) {
//                    }
//                }
//            });
//        }
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
//}


