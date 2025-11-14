package com.gogcompany.myapplication.Model

import android.graphics.Bitmap

class VideoApp {

    var videoName  : String? = null;
    var videoPath  : String? = null;
    var videoImage : Bitmap? = null;

    constructor(
        videoAppName: String ,
        videoAppPath: String ,
        videoAppImage: Bitmap ,
    ){
        videoName = videoAppName;
        videoPath = videoAppPath;
        videoImage = videoAppImage;
    }
}