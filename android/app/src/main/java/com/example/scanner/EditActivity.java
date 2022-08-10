package com.example.scanner;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.JpegImageData;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditActivity extends Activity {

    //        RecyclerView recyclerView;
    ImageView imageView;
    ImageView hori1;
    ImageView hori2;
    ImageView hori3;
    ImageView hori4;
    ImageView hori5;
    ImageView hori6;
    ImageView hori7;
    ImageView hori8;
    ImageView hori9;
    ImageView hori10;
    ImageView hori11;
    ImageView hori12;
    ImageView hori13;
    ImageView hori14;
    ImageView hori15;
    ImageView hori16;
    ImageView hori17;
    ImageView hori18;
    ImageView hori19;
    ImageView hori20;

    static String imageNum = "";
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
        hori4 = (ImageView)findViewById(R.id.hori4);
        hori5 = (ImageView)findViewById(R.id.hori5);
        hori6 = (ImageView)findViewById(R.id.hori6);
        hori7 = (ImageView)findViewById(R.id.hori7);
        hori8 = (ImageView)findViewById(R.id.hori8);
        hori9 = (ImageView)findViewById(R.id.hori9);
        hori10 = (ImageView)findViewById(R.id.hori10);
        hori11 = (ImageView)findViewById(R.id.hori11);
        hori12 = (ImageView)findViewById(R.id.hori12);
        hori13 = (ImageView)findViewById(R.id.hori13);
        hori14 = (ImageView)findViewById(R.id.hori14);
        hori15 = (ImageView)findViewById(R.id.hori15);
        hori16 = (ImageView)findViewById(R.id.hori16);
        hori17 = (ImageView)findViewById(R.id.hori17);
        hori18 = (ImageView)findViewById(R.id.hori18);
        hori19 = (ImageView)findViewById(R.id.hori19);
        hori20 = (ImageView)findViewById(R.id.hori20);

        ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();
        imageViewList.add(hori1);
        imageViewList.add(hori2);
        imageViewList.add(hori3);
        imageViewList.add(hori4);
        imageViewList.add(hori5);
        imageViewList.add(hori6);
        imageViewList.add(hori7);
        imageViewList.add(hori8);
        imageViewList.add(hori9);
        imageViewList.add(hori10);
        imageViewList.add(hori11);
        imageViewList.add(hori12);
        imageViewList.add(hori13);
        imageViewList.add(hori14);
        imageViewList.add(hori15);
        imageViewList.add(hori16);
        imageViewList.add(hori17);
        imageViewList.add(hori18);
        imageViewList.add(hori19);
        imageViewList.add(hori20);

        imageView.setImageURI(HomeActivity.photoUri);
        if (HomeActivity.savedImages.size() >= 1) {
            hori1.setImageBitmap(HomeActivity.savedImages.get(0));
        }

        if (HomeActivity.savedImages.size() >= 2) {
            hori2.setImageBitmap(HomeActivity.savedImages.get(1));
        }

        if (HomeActivity.savedImages.size() >= 3) {
            hori3.setImageBitmap(HomeActivity.savedImages.get(2));

        }
        if (HomeActivity.savedImages.size() >= 4) {
            hori4.setImageBitmap(HomeActivity.savedImages.get(3));

        }
        if (HomeActivity.savedImages.size() >= 5) {
            hori5.setImageBitmap(HomeActivity.savedImages.get(4));

        }
        if (HomeActivity.savedImages.size() >= 6) {
            hori6.setImageBitmap(HomeActivity.savedImages.get(5));

        }
        if (HomeActivity.savedImages.size() >= 7) {
            hori7.setImageBitmap(HomeActivity.savedImages.get(6));

        }
        if (HomeActivity.savedImages.size() >= 8) {
            hori8.setImageBitmap(HomeActivity.savedImages.get(7));

        }
        if (HomeActivity.savedImages.size() >= 9) {
            hori9.setImageBitmap(HomeActivity.savedImages.get(8));

        }
        if (HomeActivity.savedImages.size() >= 10) {
            hori10.setImageBitmap(HomeActivity.savedImages.get(9));

        }
        if (HomeActivity.savedImages.size() >= 11) {
            hori11.setImageBitmap(HomeActivity.savedImages.get(10));

        }
        if (HomeActivity.savedImages.size() >= 12) {
            hori12.setImageBitmap(HomeActivity.savedImages.get(11));

        }
        if (HomeActivity.savedImages.size() >= 13) {
            hori13.setImageBitmap(HomeActivity.savedImages.get(12));

        }
        if (HomeActivity.savedImages.size() >= 14) {
            hori14.setImageBitmap(HomeActivity.savedImages.get(13));

        }
        if (HomeActivity.savedImages.size() >= 15) {
            hori15.setImageBitmap(HomeActivity.savedImages.get(14));

        }
        if (HomeActivity.savedImages.size() >= 16) {
            hori16.setImageBitmap(HomeActivity.savedImages.get(15));

        }
        if (HomeActivity.savedImages.size() >= 17) {
            hori17.setImageBitmap(HomeActivity.savedImages.get(16));

        }
        if (HomeActivity.savedImages.size() >= 18) {
            hori18.setImageBitmap(HomeActivity.savedImages.get(17));

        }
        if (HomeActivity.savedImages.size() >= 19) {
            hori19.setImageBitmap(HomeActivity.savedImages.get(18));

        }
        if (HomeActivity.savedImages.size() >= 20) {
            hori20.setImageBitmap(HomeActivity.savedImages.get(19));

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
                //Creating a PdfWriter
                System.out.println("이미지~" + imageNum);
                String dest = "/storage/emulated/0/Documents/" + imageNum +".pdf";
                try {
                    // pdf 생성/몇 세
                    PdfWriter writer = new PdfWriter(dest);
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document document = new Document(pdfDoc);
                    document.setMargins(0, 0, 0, 0);

                    //image 작업
                    int numOfSavedImages = HomeActivity.savedImages.size();
                    System.out.println("알콩달콩 " + numOfSavedImages);
                    for (int i = 0; i < numOfSavedImages; i++) {
                        Bitmap image = HomeActivity.savedImages.remove();
//                        Bitmap imageForSave = image;
//                        FileOutputStream out = new FileOutputStream(imageNum);
//                        imageForSave.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
//                            // PNG is a lossless format, the compression factor (100) is ignored
                        HomeActivity.savedImagesUri.remove();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        image.compress( Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        ImageData data = ImageDataFactory.create(byteArray);
                        Image img = new Image(data);
                        document.add(img);
                        System.out.println("AAA PDF CHECKER");
                    }
                    document.close();
// dialog
                    new AlertDialog.Builder(EditActivity.this)
                            .setTitle("SUCESSFUL CONVERSION~~")
                            .setMessage("Have a great day!!")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();

                    //    Intent intent = new Intent(getApplicationContext(), OutputActivity.class);
                    //     startActivity(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
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
                    DateFormat dateFormat = new SimpleDateFormat("MMddyyyyhhmmss");
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    String todaysdate = dateFormat.format(date);
                    System.out.println("Today's date : " + todaysdate);
                    File dir = new File(Environment.getExternalStorageDirectory(), "Pictures");
                    File filePath = null;
                    try {
                        if(!dir.exists()) {
                            dir.mkdirs();
                        }
                        System.out.println(dir);
                        filePath = new File(Environment.getExternalStorageDirectory(), "Pictures/IMG" + todaysdate + ".jpg");
                                //File.createTempFile("IMG", ".jpg", new File(Environment.getExternalStorageDirectory(), "Pictures " + todaysdate);
                        System.out.println("촉촉한 초코chip" + filePath);
                        if(!filePath.exists()) {
                            filePath.createNewFile();
                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    Uri photoUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                            BuildConfig.APPLICATION_ID + ".provider", filePath);
                            //FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", dir);
                    System.out.println(photoUri);
//                    Pattern pat = Pattern.compile("IMG(\\w+)");
//                    Matcher matcher = pat.matcher(photoUri.toString());
//                    if (matcher.find())
//                    {
//                        System.out.println(matcher.group(1));
                        imageNum = todaysdate;
                        System.out.println("imageNum" + imageNum);
//                    }
                    Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, 100);
                }
            });
            if (HomeActivity.firstTimeCalling) {
                cameraPic.callOnClick();
            }
        }
    }
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            System.out.println("사진^^" + imageNum);
            File file = new File(Environment.getExternalStorageDirectory(), "Pictures/IMG" + imageNum +".jpg");
            System.out.println("July 9th " + file);
            HomeActivity.photoUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
                    //FileProvider.getUriForFile(this,  "com.Scanner.fileprovider", file);

            System.out.println("July 22th: " + HomeActivity.photoUri);


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), HomeActivity.photoUri);
                HomeActivity.savedImages.add(bitmap);
                HomeActivity.savedImagesUri.add(HomeActivity.photoUri);
                System.out.println("July first "+ HomeActivity.savedImages);
                HomeActivity.firstTimeCalling = false;
                finish();
                startActivity(getIntent());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERROR ^-^");
            }
        }
    }
}


