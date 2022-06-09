package com.UserApplication.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_entity")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username",unique = true)
    private String username;
    @Column(name = "ph_number", unique = true)
    private String ph_number;
    @Column(name = "user_ref", unique = true)
    private String user_referral_code;
    @Column(name = "dob")
    private String dob;
    @Column(name = "address")
    private String address;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "is_active")
    private Boolean is_active;
    @Column(name = "is_deleted")
    private Boolean is_deleted;
    @UpdateTimestamp
    @Column(name = "last_modified")
    private Date lastModified;


    @OneToMany(targetEntity = ReferralEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_referral_id", referencedColumnName = "id")
    private List<ReferralEntity> referralEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private LoginDetailsEntity loginDetailsEntity;


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }

    public String getUser_referral_code() {
        return user_referral_code;
    }

    public void setUser_referral_code(String user_referral_code) {
        this.user_referral_code = user_referral_code;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<ReferralEntity> getReferralEntity() {
        return referralEntity;
    }

    public void setReferralEntity(List<ReferralEntity> referralEntity) {
        this.referralEntity = referralEntity;
    }

    public LoginDetailsEntity getLoginDetailsEntity() {
        return loginDetailsEntity;
    }

    public void setLoginDetailsEntity(LoginDetailsEntity loginDetailsEntity) {
        this.loginDetailsEntity = loginDetailsEntity;
    }

    public void setReferral_code(String referral_code) {
        this.user_referral_code = referral_code;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", ph_number=" + ph_number +
                ", user_referral_code='" + user_referral_code + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", created_at=" + created_at +
                ", is_active=" + is_active +
                ", is_deleted=" + is_deleted +
                ", lastModified=" + lastModified +
                ", referralEntity=" + referralEntity +
                ", loginDetailsEntity=" + loginDetailsEntity +
                '}';
    }
}
