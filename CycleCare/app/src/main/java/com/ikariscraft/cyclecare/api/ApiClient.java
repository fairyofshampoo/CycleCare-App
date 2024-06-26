package com.ikariscraft.cyclecare.api;

import com.ikariscraft.cyclecare.api.interfaces.IChartService;
import com.ikariscraft.cyclecare.api.interfaces.IContentService;
import com.ikariscraft.cyclecare.api.interfaces.IReminderService;
import com.ikariscraft.cyclecare.api.interfaces.IUserService;
import com.ikariscraft.cyclecare.api.interfaces.ICycleService;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class ApiClient {
    private static final ApiClient apiClient = new ApiClient();
    private final Retrofit retrofit;
    private IUserService userService;
    private IChartService chartService;
    private IContentService contentService;
    private ICycleService cycleService;
    private IReminderService reminderService;

    private String baseIp = "http://192.168.100.152:8085";

    public static  ApiClient getInstance(){
        return apiClient;
    }

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseIp+"/apicyclecare/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public IUserService getUserService(){
        if(userService == null){
            userService = retrofit.create(IUserService.class);
        }
        return userService;
    }

    public IChartService getChartService(){
        if(chartService == null){
            chartService = retrofit.create(IChartService.class);
        }
        return chartService;
    }

    public IContentService getContentService(){
        if(contentService == null) {
            contentService = retrofit.create(IContentService.class);
        }
        return contentService;
    }

    public ICycleService getCycleService(){
        if(cycleService == null){
            cycleService = retrofit.create(ICycleService.class);
        }
        return cycleService;
    }

    public IReminderService getReminderService(){
        if(reminderService == null){
            reminderService = retrofit.create(IReminderService.class);
        }
        return reminderService;
    }

    public String getBaseIp(){
        return baseIp;
    }

}
