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

        ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();
        imageViewList.add(hori1);
        imageViewList.add(hori2);
        imageViewList.add(hori3);

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
                    for (int i = 0; i < HomeActivity.savedImages.size(); i++) {
                        Bitmap image = HomeActivity.savedImages.remove();
                        HomeActivity.savedImages.remove();
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
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setTitle("SUCESSFUL CONVERSION~~")
//                            .setMessage("Have a great day!!")
//                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                                    startActivity(intent);
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_info)
//                            .show();

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


