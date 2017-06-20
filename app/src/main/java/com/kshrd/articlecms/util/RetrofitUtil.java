package com.kshrd.articlecms.util;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by pirang on 6/20/17.
 */

public class RetrofitUtil {

    public static RequestBody toRequestBody(String value){
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

}
