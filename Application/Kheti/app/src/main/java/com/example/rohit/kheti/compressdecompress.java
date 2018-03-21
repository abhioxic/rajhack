package com.example.rohit.kheti;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Rohit on 3/19/2018.
 */

public class compressdecompress {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String compress(String data) throws IOException {
        int flags = Base64.NO_WRAP | Base64.URL_SAFE;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data.getBytes());
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();

        byte[] encoded = Base64.encode(compressed,flags);
//        byte[] encoded = Base64.getEncoder().encode(compressed);
        return new String(encoded);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decompress(String compressed) throws IOException {
        int flags = Base64.NO_WRAP | Base64.URL_SAFE;
        byte[] encoded = compressed.getBytes();
        byte[] compressed1 = Base64.decode(encoded,flags);
//        byte[] compressed1 = Base64.getDecoder().decode(encoded);
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed1);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }
}
