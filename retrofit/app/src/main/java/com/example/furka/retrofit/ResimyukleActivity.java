package com.example.furka.retrofit;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResimyukleActivity extends AppCompatActivity {
    private TextView deneme;
    private Button btnsec, btnyukle;
    private ImageView imageview;
    private int GALERI = 1, KAMERA = 2;
    private Bitmap bitmap;
    Uri contentURI;
    String a;
    String mCurrentPhotoPath;
    DateFormat df;
    Toolbar toolbar;
    ProgressBar pb;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resimyukle);

        btnsec = (Button) findViewById(R.id.btnsec);
        imageview = (ImageView) findViewById(R.id.iv);
        btnyukle = (Button) findViewById(R.id.btnyukle);
        pb = findViewById(R.id.progress_bar);
        pb.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resim Yükle");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        a = AcilisActivity.prefConfig.kullaniciadkontrol();


        btnsec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        showPictureDialog();
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77700000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }


                }


                return true;
            }


        });

        btnyukle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                pb.setVisibility(View.VISIBLE);
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP: {
                        if (bitmap != null) {
                            uploadImage();
                        } else {
                            Toast.makeText(ResimyukleActivity.this, "Lütfen Resim Seçiniz", Toast.LENGTH_LONG).show();

                        }

                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77700000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }


                }


                return true;
            }


        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ResimyukleActivity.this, AcilisActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.empty_menu, menu);
        return true;
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("İşleminiz:");
        String[] pictureDialogItems = {
                "Galeriden Seç",
                "Kamera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                try {
                                    takePhotoFromCamera();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    private void uploadImage() {

        String Image = imageToString();


        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        a = AcilisActivity.prefConfig.kullaniciadkontrol();
        Call<ImageClass> call = apiInterface.uploadImage(Image, a);

        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {

                ImageClass imageclass = response.body();
                Toast.makeText(ResimyukleActivity.this, imageclass.getResponse() + "  ", Toast.LENGTH_LONG).show();
                imageview.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {
                pb.setVisibility(View.GONE);
                Toast.makeText(ResimyukleActivity.this, "Bir hata oluştu sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALERI);

    }

    private void takePhotoFromCamera() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                return;
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(ResimyukleActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        createImageFile());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, KAMERA);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".JPEG",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALERI) {
            if (data != null) {
                contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);

                    // Toast.makeText(ResimyukleActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ResimyukleActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == KAMERA) {
            super.onActivityResult(requestCode, resultCode, data);

            Uri imageUri = Uri.parse(mCurrentPhotoPath);
            File file = new File(imageUri.getPath());
            try {
                InputStream ims = new FileInputStream(file);
                bitmap = BitmapFactory.decodeStream(ims);
                imageview.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                return;
            }

            // ScanFile so it will be appeared on Gallery
            MediaScannerConnection.scanFile(ResimyukleActivity.this,
                    new String[]{imageUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });


        }
    }


    private String imageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }


}
