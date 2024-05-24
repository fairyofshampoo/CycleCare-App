package com.ikariscraft.cyclecare.repository;

import com.ikariscraft.cyclecare.api.ApiClient;
import com.ikariscraft.cyclecare.api.Interfaces.IContentService;
import com.ikariscraft.cyclecare.api.responses.RateContentJSONResponse;

import java.sql.ClientInfoStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentRepository {

    public void RateContent(String toke,int contentId, int rating, IProcessStatusListener listener){

        IContentService contentService = ApiClient.getInstance().getContentService();

        contentService.rateInformativeContent(toke, contentId, rating).enqueue(new Callback<RateContentJSONResponse>() {
            @Override
            public void onResponse(Call<RateContentJSONResponse> call, Response<RateContentJSONResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
                    RateContentJSONResponse contentResponse = new RateContentJSONResponse(
                            response.body().getError(),
                            response.body().getStatusCode(),
                            response.body().getDetails()
                    );
                    listener.onSuccess(contentResponse);
                }else{
                    listener.onError(ProcessErrorCodes.FATAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<RateContentJSONResponse> call, Throwable t) {
                listener.onError(ProcessErrorCodes.FATAL_ERROR);
            }
        });

    }

}