package com.example.RelationalMappingPractice.entity;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "teacher_detail")
public class TeacherDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "identification_number", length = 255)
    private String identificationNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "hobby", length = 255)
    private String hobby;

    @Column(name = "date_of_birth", length = 255)
    private Date dateOfBirth;

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Lob
    @Column(name = "avatar" )
    private Blob avatar;

    @OneToOne(mappedBy = "teacherDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Teacher teacher;

    public TeacherDetail() {
    }

    public TeacherDetail(String identificationNumber, Blob avatar, String phoneNumber, String address, String hobby, Date dateOfBirth) {
        this.identificationNumber = identificationNumber;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.hobby = hobby;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherDetail{" +
                "id=" + id +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
