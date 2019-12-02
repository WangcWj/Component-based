package com.example.education;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/10/30
 */
public interface ServiceApi {
   //https://www.apiopen.top/journalismApi
    @GET("journalismApi")
    Call<ResponseBody> getNews();


}
