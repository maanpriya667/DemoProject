package com.UserApplication.demo.service;

import com.UserApplication.demo.model.request.DetailsRequest;
import com.UserApplication.demo.model.request.OTPRequest;
import com.UserApplication.demo.model.request.SignupRequest;
import com.UserApplication.demo.model.response.ProfileResponse;
import com.UserApplication.demo.model.response.ResponseModel;
import com.UserApplication.demo.model.response.SignupSuccessResponse;
import com.UserApplication.demo.model.response.UIBean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface UserService {

    void sendMail(OTPRequest otpRequest);

    ResponseEntity<ResponseModel<SignupSuccessResponse>> verify(SignupRequest signupRequest, MultiValueMap<String, String> headers);

   ResponseEntity<ResponseModel<?>> sendDetails(DetailsRequest detailsRequest, String email);

    UIBean<ProfileResponse> getUser(String email);
}
