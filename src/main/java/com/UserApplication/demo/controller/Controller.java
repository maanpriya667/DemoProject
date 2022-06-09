package com.UserApplication.demo.controller;

import com.UserApplication.demo.model.request.DetailsRequest;
import com.UserApplication.demo.model.request.OTPRequest;
import com.UserApplication.demo.model.request.SignupRequest;
import com.UserApplication.demo.model.response.ResponseModel;
import com.UserApplication.demo.model.response.SignupSuccessResponse;
import com.UserApplication.demo.service.UserService;
import com.UserApplication.demo.validation.request.UserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/")

public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    UserRequestValidator userRequestValidator;


    @PostMapping("/otp")
    public ResponseEntity<ResponseModel<?>> genOtp(@RequestBody OTPRequest otpRequest) {
        ResponseModel<?> responseModel = userRequestValidator.validateOtpRequest(otpRequest);
        if (responseModel != null)
            return ResponseEntity.badRequest().body(responseModel);
        userService.sendMail(otpRequest);
        return ResponseEntity.ok(new ResponseModel<String>(200, "Otp sent successfully", null, null));
    }


    @PostMapping("/verify")
    public ResponseEntity<ResponseModel<SignupSuccessResponse>> OtpVerify(@RequestBody SignupRequest signupRequest,@RequestHeader MultiValueMap<String, String> headers) {
            return userService.verify(signupRequest, headers);
    }



    @PostMapping("/details")
    public  ResponseEntity<ResponseModel<?>> details(@RequestHeader(required = false, value = "user-mail") String email,
                                    @RequestBody DetailsRequest detailsRequest) {
        ResponseModel<?> loginResponseModel = userRequestValidator.validateLoginRequest(detailsRequest);
       if (loginResponseModel != null)
           return ResponseEntity.badRequest().body(loginResponseModel);
        return userService.sendDetails(detailsRequest, email);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> userProfile(@RequestHeader(required = false, value = "user-mail") String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }

}