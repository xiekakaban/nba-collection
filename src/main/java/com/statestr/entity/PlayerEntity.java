package com.statestr.entity;

import com.statestr.util.GenerateId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@Entity
@Table(name="nba_player")
public class PlayerEntity extends AbstractEntity{
    @Id
    private String id;
    @Column(name = "name_chinese",length = 50,nullable = false)
    private String nameCh;
    @Column(name = "name_english",length = 50,nullable = false)
    private String nameEn;
    @Column(name= "full_name",length = 100,nullable = false)
    private String fullName;
    @Column(name= "avator",length = 50)
    private String avator;
    @Column(name="position",length = 50)
    private String position;
    @Column(name="height",length = 50)
    private String height;
    @Column(name="weight",length = 50)
    private String weight;
    @Column(name="birthday",length = 50)
    private String birthday;
    @Column(name="birthcity",length = 100)
    private String birthcity;
    @Column(name="highSchool",length = 100)
    private String highSchool;
    @Column(name="college",length = 100)
    private String college;
    @Column(name="cloth",length = 200)
    private String cloth;
    @Column(name="talentShow",length = 100)
    private String talentShow;
    @Column(name="detailUrl",length = 200)
    private String detailUrl;
    @Column(name="code",length = 1)
    private String code;
    @Column(name="salary",columnDefinition = "TEXT")
    private String salary;

    @OneToMany(mappedBy = "player",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PlayerInMatchEntity> playerInMatchEntitySet = new HashSet<PlayerInMatchEntity>();

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PlayerEntity() {
        this.id = GenerateId.generate();
    }

    public PlayerEntity(String nameCh, String nameEn, String fullName, String position, String height, String weight, String birthday, String birthcity, String highSchool, String college, String cloth, String talentShow, String detailUrl, String code, String salary) {
        this.nameCh = nameCh;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.birthcity = birthcity;
        this.highSchool = highSchool;
        this.college = college;
        this.cloth = cloth;
        this.talentShow = talentShow;
        this.detailUrl = detailUrl;
        this.code = code;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthcity() {
        return birthcity;
    }

    public void setBirthcity(String birthcity) {
        this.birthcity = birthcity;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCloth() {
        return cloth;
    }

    public void setCloth(String cloth) {
        this.cloth = cloth;
    }

    public String getTalentShow() {
        return talentShow;
    }

    public void setTalentShow(String talentShow) {
        this.talentShow = talentShow;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Set<PlayerInMatchEntity> getPlayerInMatchEntitySet() {
        return playerInMatchEntitySet;
    }

    public void setPlayerInMatchEntitySet(Set<PlayerInMatchEntity> playerInMatchEntitySet) {
        this.playerInMatchEntitySet = playerInMatchEntitySet;
    }
}