package com.UserApplication.demo.service;

import com.UserApplication.demo.entity.DeviceDetailsEntity;
import com.UserApplication.demo.entity.LoginDetailsEntity;
import com.UserApplication.demo.entity.ReferralEntity;
import com.UserApplication.demo.entity.UserEntity;
import com.UserApplication.demo.model.request.DetailsRequest;
import com.UserApplication.demo.model.request.OTPRequest;
import com.UserApplication.demo.model.request.SignupRequest;
import com.UserApplication.demo.model.response.*;
import com.UserApplication.demo.redis.RedisUtility;

import com.UserApplication.demo.repository.DeviceDetRepository;
import com.UserApplication.demo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServiceImpl implements UserService {

    @Autowired
    RedisUtility redisUtility;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailsender;
    @Value("${auth.token.secret.key}")
    private String authSecret;
    @Value("${user.login.type1}")
    private String log_type;
    @Value("${user.login.referral.points}")
    private Long ref_points;
    @Value("${master.otp}")
    private String masterOtp;


    @Override
    @Async
    public void sendMail(OTPRequest otpRequest) {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        String otp = String.format("%04d", number);

        String key = otpRequest.getEmail() + "_OTP";
        String email = otpRequest.getEmail();
        redisUtility.setData(key, otp);


        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("springemail234@gmail.com");
        message.setTo(email);
        message.setSubject("OTP");
        message.setText(otp);
        try {
            javaMailsender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<ResponseModel<SignupSuccessResponse>> verify(SignupRequest signupRequest
            , MultiValueMap<String, String> headers) {
        String email = signupRequest.getEmail();
        String otp = signupRequest.getOtp();

        String key = email + "_OTP";
        String dbOTP = redisUtility.getData(key);

        if (dbOTP != null || otp != null) {

            if (otp.equals(dbOTP) || (otp.equals(masterOtp))) {

                redisUtility.delete(key);
                UserEntity userEntity = userRepository.findByEmail(email);

                UserEntity referrerUser = null;

                if (userEntity != null) {
                    userEntity.setIs_active(true);
                } else {
                    String refCode = RandomStringUtils.randomAlphabetic(8).toUpperCase();

                    userEntity = new UserEntity();
                    userEntity.setEmail(signupRequest.getEmail());
                    userEntity.setIs_active(true);
                    userEntity.setIs_deleted(false);
                    userEntity.setUser_referral_code(refCode);

                    if (signupRequest.getReferral_code() != null) {
                        referrerUser = userRepository.findByRefCode(signupRequest.getReferral_code());

                        if (referrerUser != null) {
                            List<ReferralEntity> referralDetails = referrerUser.getReferralEntity();


                            if (referralDetails == null)
                                referralDetails = new ArrayList<ReferralEntity>();

                            ReferralEntity referralEntity = new ReferralEntity();
                            referralEntity.setReferral_email(signupRequest.getEmail());
                            referralEntity.setReferrer_email(referrerUser.getEmail());
                            referralEntity.setPoints(ref_points);
                            referralDetails.add(referralEntity);
                            referrerUser.setReferralEntity(referralDetails);
                        } else {
                            return ResponseEntity.ok(new ResponseModel<SignupSuccessResponse>(HttpStatus.BAD_REQUEST, "Invalid Referral Code", null, null));
                        }
                    }
                }


                LoginDetailsEntity login = userEntity.getLoginDetailsEntity();

                if (login == null) {
                    login = new LoginDetailsEntity();
                }
                login.setCreated_at(new Date());
                login.setLogin_time(new Date());
                login.setLogin_type(log_type);


                userEntity.setLoginDetailsEntity(login);
                login.setUserEntity(userEntity);


                String token = generateToken(headers, userEntity);
                List<DeviceDetailsEntity> deviceDetailsEntityList = login.getDeviceDetails();
                DeviceDetailsEntity deviceDetails = new DeviceDetailsEntity();

                if (deviceDetailsEntityList == null) {
                    deviceDetailsEntityList = new ArrayList<DeviceDetailsEntity>();
                }
                deviceDetails.setDevice_name(signupRequest.getDevice_name());
                deviceDetails.setDevice_model(signupRequest.getDevice_model());
                deviceDetails.setToken(token);
                deviceDetails.setIs_active(true);
                deviceDetails.setLoginDetailsEntity(login);
                deviceDetailsEntityList.add(deviceDetails);

                login.setDeviceDetails(deviceDetailsEntityList);


                try {
                    if (referrerUser != null) {
                        userRepository.saveAll(Arrays.asList(userEntity, referrerUser));
                    } else {
                        userRepository.save(userEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SignupSuccessResponse signupSuccessResponse = new SignupSuccessResponse();
                signupSuccessResponse.setIsExistingUser(true);
                signupSuccessResponse.setToken(token);


                return ResponseEntity.ok(new ResponseModel<SignupSuccessResponse>(HttpStatus.OK, "Otp Verified", null, signupSuccessResponse));
            } else return ResponseEntity.ok(new ResponseModel<SignupSuccessResponse>(HttpStatus.BAD_REQUEST, "Invalid Otp", null, null));
        } else {
            return ResponseEntity.ok(new ResponseModel<SignupSuccessResponse>(HttpStatus.BAD_REQUEST, "Invalid Otp", null, null));

        }
    }


    @Override
    public ResponseEntity<ResponseModel<?>> sendDetails(DetailsRequest detailsRequest, String email) {

        UserEntity existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setPh_number(detailsRequest.getPh_number());
            existingUser.setAddress(detailsRequest.getAddress());
            existingUser.setDob(detailsRequest.getDob());
            existingUser.setUsername(detailsRequest.getUsername());

            try {
                userRepository.save(existingUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(new ResponseModel<String>(HttpStatus.OK,"Details Saved Successfully",null,null));
        } else {
            return ResponseEntity.ok(new ResponseModel<String>(HttpStatus.BAD_REQUEST, "Please Enter the Details Correctly", null, null));
        }
    }


    @Override
    public UIBean<ProfileResponse> getUser(String email) {

        UserEntity user = userRepository.findByEmail(email);
        UIBean<ProfileResponse> ui = new UIBean<ProfileResponse>();
        if (user != null) {
            ProfileResponse profileResponse = new ProfileResponse();

            profileResponse.setPh_number(user.getPh_number());
            profileResponse.setAddress(user.getAddress());
            profileResponse.setEmail(user.getEmail());
            profileResponse.setDob(user.getDob());
            profileResponse.setReferral_code(user.getUser_referral_code());


            List<ReferralEntity> referralEntityList = user.getReferralEntity();


            List<ReferralDetails> referralList = new ArrayList<>();
            Long points = (long) 0;

            for (ReferralEntity refEntity : referralEntityList) {
                String referralEmail = refEntity.getReferral_email();
                String maskedEmail = referralEmail.replaceAll("(^[^@]{3}|(?!^)\\G)[^@]", "$1*");

                Long getPoints = refEntity.getPoints();

                ReferralDetails referralDetails = new ReferralDetails();
                referralDetails.setPoints(getPoints);
                referralDetails.setReferral_email(maskedEmail);

                referralList.add(referralDetails);
                points = points + getPoints;

            }

            profileResponse.setTotalPoints(points);
            profileResponse.setReferralDetails(referralList);

            ui.setUserData(profileResponse);

            return ui;
        }
        return null;
    }


    private String generateToken(MultiValueMap<String, String> headers, UserEntity userEntity) {
        final Map<String, Object> claims = new ConcurrentHashMap<>();
        String userAgent = headers.getFirst("user-agent");
        claims.put("sub", userEntity.getEmail());
        claims.put("user-agent", userAgent);
        claims.put("iat", new Date());
        return Jwts.builder().setSubject(userEntity.getEmail()).setClaims(claims).signWith(SignatureAlgorithm.HS512, authSecret).compact();
    }
}

