package game.nj.wii.utils;

import android.graphics.Bitmap;

import java.security.PublicKey;

/**
 * Created by wii on 1/20/16.
 */
public class ImagePieces {
    public int index = 0;
    public Bitmap bitmap = null;

    public void ImagePieces(int index,Bitmap bitmap){
        this.index = index;
        this.bitmap = bitmap;
    }
    public int getIndex(){

        return this.index;
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setBitmap(Bitmap bitmap){

        this.bitmap = bitmap;
    }

}
