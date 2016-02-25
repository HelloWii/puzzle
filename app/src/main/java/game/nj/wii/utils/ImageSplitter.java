package game.nj.wii.utils;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wii on 1/20/16.
 */
public class ImageSplitter {

    /*
    * bitmap  to  piece*piece
    * */

    public static List<ImagePieces> splitImage(Bitmap bitmap,int piece){
        List<ImagePieces> pieces = new ArrayList<>(piece * piece);
        int width  = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pieceLength = Math.min(width,height) / piece;
        for(int i = 0; i < piece;i++)
            for (int j= 0;j<piece;j++){
                ImagePieces imagePieces = new ImagePieces();
                imagePieces.setIndex(j+i*piece);
                Log.e("TAG", "pieces index" + (j + i * piece));

                int x = j * pieceLength;
                int y = i * pieceLength;

                imagePieces.setBitmap(Bitmap.createBitmap(bitmap,x,y,pieceLength,pieceLength));

                pieces.add(imagePieces);
            }

        return pieces;
    }
}
