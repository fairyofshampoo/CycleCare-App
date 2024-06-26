package com.ikariscraft.cyclecare.activities.view_content_user_pov;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ikariscraft.cyclecare.api.RequestStatus;
import com.ikariscraft.cyclecare.api.requests.RateInformativeContentRequest;
import com.ikariscraft.cyclecare.api.responses.InformativeContentJSONResponse;
import com.ikariscraft.cyclecare.repository.ContentRepository;
import com.ikariscraft.cyclecare.repository.IEmptyProcessListener;
import com.ikariscraft.cyclecare.repository.IProcessStatusListener;
import com.ikariscraft.cyclecare.repository.ProcessErrorCodes;

import java.util.List;

public class ViewContentViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isErrorLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> statusCodeLiveData = new MutableLiveData<>();

    private final MutableLiveData<Integer> rate = new MutableLiveData<>();

    private final MutableLiveData<String> detailsLiveData = new MutableLiveData<>();

    private final MutableLiveData<RequestStatus> rateContentRequestStatus = new MutableLiveData<>();

    private final MutableLiveData<ProcessErrorCodes> rateContentErrorCode = new MutableLiveData<>();

    private final MutableLiveData<RequestStatus> existingRateContentRequestStatus = new MutableLiveData<>();

    public ViewContentViewModel(){

    }

    public LiveData<Boolean> getIsErrorLiveData() {return isErrorLiveData;}

    public LiveData<String> getStatusCodeLiveData() {return statusCodeLiveData;}

    public LiveData<RequestStatus> getRateContentRequestStatus() {return  rateContentRequestStatus;}


    public LiveData<RequestStatus> getExistingRateContentRequestStatus() {return  existingRateContentRequestStatus;}

    public LiveData<String> getDetails(){return detailsLiveData;}

    public LiveData<ProcessErrorCodes> getRateContentErrorCode() {return rateContentErrorCode;}

    public LiveData<Integer> getRate (){return  rate;}


    public void rateContent(String token, int contentId, RateInformativeContentRequest rating){
        rateContentRequestStatus.setValue(RequestStatus.LOADING);
        new ContentRepository().rateContent(
                token,
                contentId,
                rating,
                new IEmptyProcessListener() {
                    @Override
                    public void onSuccess() {
                        rateContentRequestStatus.setValue(RequestStatus.DONE);
                    }

                    @Override
                    public void onError(ProcessErrorCodes errorCode) {
                        rateContentErrorCode.setValue(errorCode);
                        rateContentRequestStatus.setValue(RequestStatus.ERROR);
                    }
                }
        );

    }

    public void UpdateRateContent(String token, int contentId, RateInformativeContentRequest rating){
        rateContentRequestStatus.setValue(RequestStatus.LOADING);
        new ContentRepository().updateRateContent(
                token,
                contentId,
                rating,
                new IEmptyProcessListener() {
                    @Override
                    public void onSuccess() {
                        rateContentRequestStatus.setValue(RequestStatus.DONE);
                    }

                    @Override
                    public void onError(ProcessErrorCodes errorCode) {
                        rateContentErrorCode.setValue(errorCode);
                        rateContentRequestStatus.setValue(RequestStatus.ERROR);
                    }
                }
        );

    }

    public void GetRateContent(String token, int contentId){
        rateContentRequestStatus.setValue(RequestStatus.LOADING);
        new ContentRepository().getRateContent(
                token,
                contentId,
                new IProcessStatusListener() {

                    @Override
                    public void onSuccess(Object data) {
                        rate.setValue((Integer) data);
                        existingRateContentRequestStatus.setValue(RequestStatus.DONE);
                        rateContentRequestStatus.setValue(RequestStatus.DONE);
                    }

                    @Override
                    public void onError(ProcessErrorCodes errorCode) {
                        rateContentErrorCode.setValue(errorCode);
                        existingRateContentRequestStatus.setValue(RequestStatus.ERROR);
                        rateContentRequestStatus.setValue(RequestStatus.ERROR);
                    }
                }
        );

    }



    private final MutableLiveData<RequestStatus> informativeContentStatus = new MutableLiveData<>();

    private final MutableLiveData<List<InformativeContentJSONResponse>>  informativeContentList = new MutableLiveData<>();

    private final MutableLiveData<ProcessErrorCodes> informativeContentErrorCode = new MutableLiveData<>();

    public LiveData<List<InformativeContentJSONResponse>> getInformativeContentList() {return informativeContentList;}

    public LiveData<RequestStatus> getInformativeContentRequestStatus() {return informativeContentStatus;}

    public LiveData<ProcessErrorCodes> getInformativeContentErrorCode() {return informativeContentErrorCode;}

    public void getInformativeContent(String token){
        informativeContentStatus.setValue(RequestStatus.LOADING);

        new ContentRepository().getInformativeContent(
                token,
                new IProcessStatusListener() {
                    @Override
                    public void onSuccess(Object data) {
                        List<InformativeContentJSONResponse> content = (List<InformativeContentJSONResponse>) data;
                        informativeContentList.setValue(content);
                        informativeContentStatus.setValue(RequestStatus.DONE);
                    }

                    @Override
                    public void onError(ProcessErrorCodes errorCode) {
                        informativeContentList.setValue(null);
                        informativeContentErrorCode.setValue(errorCode);
                        informativeContentStatus.setValue(RequestStatus.ERROR);
                    }
                }
        );

    }



}
