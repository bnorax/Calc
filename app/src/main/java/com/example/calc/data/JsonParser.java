package com.example.calc.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;

import com.example.calc.Gallery;
import com.example.calc.GalleryItem;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    Context context;
    Aead aead;
    public JsonParser(Context pContext) {
        context = pContext;
        try {
            AeadConfig.register();
            KeysetHandle keysetHandle = KeysetHandle.generateNew(
                    AeadKeyTemplates.AES256_GCM);
            aead = keysetHandle.getPrimitive(Aead.class);
        }catch (Exception e){
            return;
        }
    }
    public void savePostsToFS(List<GalleryItem> list){
        JSONArray array = new JSONArray();
        JSONObject mainObject = new JSONObject();
        for(int i = 0 ; i < list.size(); i++){
            JSONObject item = new JSONObject();
            GalleryItem galleryItem = list.get(i);
            try {
                item.put("header", galleryItem.getHeaderRes());
                item.put("description", galleryItem.getDescRes());
                if(galleryItem.getMusicRes() != null){
                    item.put("audioUri", galleryItem.getMusicRes().toString());
                }
                if(galleryItem.getImageRes() != null){
                    item.put("imageUri", galleryItem.getImageRes().toString());
                }
                array.put(item);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        try{
            mainObject.put("data", array);
        }catch (JSONException e){
            e.printStackTrace();
        }
        try{
            FileOutputStream fos = context.openFileOutput("posts.json",Context.MODE_PRIVATE);
            String jsonString  = mainObject.toString();
            try {
                fos.write(aead.encrypt(jsonString.getBytes(), "123".getBytes()));
                fos.close();
            }catch (Exception e){
                fos.close();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<GalleryItem> getPostsFromFS() {
        String fileLocation = "posts.json";
        String jsonString = "";
        List<GalleryItem> itemList = new ArrayList<>();
        try {
            FileInputStream is = context.openFileInput("posts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            try {
                byte[] asd = new byte[size];
                asd = aead.decrypt(buffer, "123".getBytes());
                jsonString = new String(asd, "UTF-8");
            }catch (Exception e){}

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray jsonArray = object.getJSONArray("data");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject singeObjectData = jsonArray.getJSONObject(i);
                String header = singeObjectData.getString("header");
                String desc = singeObjectData.getString("description");
                Uri audio;
                try{
                    singeObjectData.getString("audioUri");
                    audio = Uri.parse(singeObjectData.getString("audioUri"));
                }catch (JSONException e){
                    audio = null;
                    e.printStackTrace();
                }
                Uri image;
                try{
                    singeObjectData.getString("imageUri");
                    image = Uri.parse(singeObjectData.getString("imageUri"));
                }catch (JSONException e){
                    image = null;
                    e.printStackTrace();
                }
                itemList.add(new GalleryItem(header, desc, audio, image));
            }
            return itemList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
