package com.ikariscraft.cyclecare.activities.forgot_password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ikariscraft.cyclecare.api.RequestStatus;
import com.ikariscraft.cyclecare.api.requests.PasswordResetRequest;
import com.ikariscraft.cyclecare.repository.IEmptyProcessListener;
import com.ikariscraft.cyclecare.repository.ProcessErrorCodes;
import com.ikariscraft.cyclecare.repository.UserRepository;
import com.ikariscraft.cyclecare.utilities.Validations;

public class ForgotPasswordViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isEmailValid = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCodeValid = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPasswordValid = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPasswordConfirmationValid = new MutableLiveData<>();
    private final MutableLiveData<ProcessErrorCodes> forgotPasswordErrorCode = new MutableLiveData<>();
    private final MutableLiveData<RequestStatus> forgotPasswordRequestStatus = new MutableLiveData<>();

    public ForgotPasswordViewModel() {

    }

    public LiveData<Boolean> isEmailValid() {return isEmailValid;}
    public LiveData<Boolean> isCodeValid() {return isCodeValid;}
    public LiveData<Boolean> isPasswordValid() {return isPasswordValid;}
    public LiveData<Boolean> isPasswordConfirmationValid() {return isPasswordConfirmationValid;}
    public LiveData<ProcessErrorCodes> getForgotPasswordErrorCode() {return forgotPasswordErrorCode;}
    public LiveData<RequestStatus> getForgotPasswordRequestStatus() {return forgotPasswordRequestStatus;}

    public void ValidateEmail(String email) {
        boolean validation = Validations.isValidEmail(email);

        isEmailValid.setValue(validation);
    }

    public void ValidatePassword(String password){
        boolean validation = Validations.isPasswordValid(password);
        isPasswordValid.setValue(validation);
    }

    public void ValidatePasswordConfirmation(String password, String passwordConfirmation){
        boolean validation = Validations.isPasswordValid(passwordConfirmation) && password.equals(passwordConfirmation);
        isPasswordConfirmationValid.setValue(validation);
    }

    public void ValidateCode(String code){
        boolean validation = Validations.isValidCode(code);
        isCodeValid.setValue(validation);
    }

    public void sendEmail(String email) {
        forgotPasswordRequestStatus.setValue(RequestStatus.LOADING);

        new UserRepository().sendVerificationCode(email,
                new IEmptyProcessListener() {
                    @Override
                    public void onSuccess() {
                        forgotPasswordRequestStatus.setValue(RequestStatus.DONE);
                    }

                    @Override
                    public void onError(ProcessErrorCodes errorCode) {
                        forgotPasswordErrorCode.setValue(errorCode);
                        forgotPasswordRequestStatus.setValue(RequestStatus.ERROR);
                    }
                });
    }

    public void resetPassword(PasswordResetRequest data){
        forgotPasswordRequestStatus.setValue(RequestStatus.LOADING);

            new UserRepository().changePasswordAndVerifyCode(data,
                    new IEmptyProcessListener() {
                        @Override
                        public void onSuccess() {
                            forgotPasswordRequestStatus.setValue(RequestStatus.DONE);
                        }

                        @Override
                        public void onError(ProcessErrorCodes errorCode) {
                            forgotPasswordErrorCode.setValue(errorCode);
                            forgotPasswordRequestStatus.setValue(RequestStatus.ERROR);
                        }
                    });
    }
}
