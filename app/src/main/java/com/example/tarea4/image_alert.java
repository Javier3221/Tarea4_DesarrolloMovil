package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class image_alert extends DialogFragment {
    int imageToShow;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_image_alert, null);
        builder.setView(view)
                .setNegativeButton("Hide", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        ImageView imageBox = view.findViewById(R.id.imageBox);
        Bundle mArgs = getArguments();
        imageToShow = mArgs.getInt("image");
        imageBox.setBackgroundResource(imageToShow);

        ImageButton share = view.findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareImage(getContext(), imageToShow, "cachedImage");
            }
        });
        return builder.create();
    }

    private void shareImage(Context context, int imageId, String fileName){
        try{
            Bitmap bitMap = BitmapFactory.decodeResource(context.getResources(), imageId);

            File output = new File(context.getCacheDir(), fileName + ".jpg");
            FileOutputStream outPutStream = new FileOutputStream(output);
            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, outPutStream);
            outPutStream.flush();
            outPutStream.close();
            output.setReadable(true, false);

            Uri imageUri = FileProvider.getUriForFile(context, "com.example.tarea4.provider", output);

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.setType("image/jpeg");
            context.startActivity(shareIntent);
        }catch (Exception e){
            Toast.makeText(context, "error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}