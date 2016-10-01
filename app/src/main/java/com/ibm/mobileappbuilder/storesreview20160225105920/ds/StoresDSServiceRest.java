
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface StoresDSServiceRest{

	@GET("/app/57ef5bd59d17e00300d4d46e/r/storesDS")
	void queryStoresDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<StoresDSItem>> cb);

	@GET("/app/57ef5bd59d17e00300d4d46e/r/storesDS/{id}")
	void getStoresDSItemById(@Path("id") String id, Callback<StoresDSItem> cb);

	@DELETE("/app/57ef5bd59d17e00300d4d46e/r/storesDS/{id}")
  void deleteStoresDSItemById(@Path("id") String id, Callback<StoresDSItem> cb);

  @POST("/app/57ef5bd59d17e00300d4d46e/r/storesDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<StoresDSItem>> cb);

  @POST("/app/57ef5bd59d17e00300d4d46e/r/storesDS")
  void createStoresDSItem(@Body StoresDSItem item, Callback<StoresDSItem> cb);

  @PUT("/app/57ef5bd59d17e00300d4d46e/r/storesDS/{id}")
  void updateStoresDSItem(@Path("id") String id, @Body StoresDSItem item, Callback<StoresDSItem> cb);

  @GET("/app/57ef5bd59d17e00300d4d46e/r/storesDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ef5bd59d17e00300d4d46e/r/storesDS")
    void createStoresDSItem(
        @Part("data") StoresDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<StoresDSItem> cb);
    
    @Multipart
    @PUT("/app/57ef5bd59d17e00300d4d46e/r/storesDS/{id}")
    void updateStoresDSItem(
        @Path("id") String id,
        @Part("data") StoresDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<StoresDSItem> cb);
}

