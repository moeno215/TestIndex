package com.example.testindex;

import com.example.testindex.model.DataTandaJadi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.testindex.model.DeleteTandaJadi;
import com.example.testindex.model.InsertTandaJadi;
import com.example.testindex.model.UpdateTandaJadi;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class API {

    static Retrofit retrofit;
    public static String baseURL = "http://192.168.26.217/";


    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL + "master/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }

    public static Call<InsertTandaJadi> addTandaJadi(RequestBody pi, RequestBody main_item,
                                                     RequestBody item, RequestBody latitude,
                                                     RequestBody longitude, RequestBody no_dlr,
                                                     RequestBody remark, RequestBody create_by,
                                                     RequestBody status_pica, RequestBody type, RequestBody nama_dlr, MultipartBody.Part pic) {
        SupervisiService service = getInstance().create(SupervisiService.class);
        return service.addTandaJadi(pi, main_item, item, latitude, longitude, no_dlr, remark, create_by, status_pica, type, nama_dlr, pic);
    }

    public static Call<ArrayList<DataTandaJadi>> getDataTandaJadi() {
        SupervisiService service = getInstance().create(SupervisiService.class);
        return service.getDataTandaJadi();
    }

    public static Call<DeleteTandaJadi> deleteTandaJadi(String id){
        SupervisiService service = getInstance().create(SupervisiService.class);
        return service.deleteTandaJadi(id);
    }

    public static Call<UpdateTandaJadi> editTandaJadi(String id, String tanda_jadi, String mod_by) {
        SupervisiService service = getInstance().create(SupervisiService.class);
        return service.editTandaJadi(id, tanda_jadi, mod_by);
    }

    public interface SupervisiService {

        @Multipart
        @POST("insert_tanda_jadi.php")
        Call<InsertTandaJadi> addTandaJadi(
                @Part("pi") RequestBody pi,
                @Part("main_item") RequestBody main_item,
                @Part("item") RequestBody item,
                @Part("latitude") RequestBody latitude,
                @Part("longitude") RequestBody longitude,
                @Part("no_dlr") RequestBody no_dlr,
                @Part("remark") RequestBody jawaban,
                @Part("create_by") RequestBody create_by,
                @Part("status_pica") RequestBody status_pica,
                @Part("type") RequestBody type,
                @Part("nama_dlr") RequestBody nama_dlr,
                @Part MultipartBody.Part pic);

        @GET("get_tanda_jadi.php")
        Call<ArrayList<DataTandaJadi>> getDataTandaJadi();


        @DELETE("delete_tanda_jadi.php")
        Call<DeleteTandaJadi> deleteTandaJadi(
                @Query("id") String id);

        @POST("update_tanda_jadi.php")
        Call<UpdateTandaJadi> editTandaJadi(
                @Query("id") String id,
                @Query("tanda_jadi") String tanda_jadi,
                @Query("mod_by") String mod_by);

    }

}