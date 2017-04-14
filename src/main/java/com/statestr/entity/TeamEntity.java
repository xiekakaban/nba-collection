package com.statestr.entity;

import com.statestr.util.GenerateId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@Entity
@Table(name="nba_team")
public class TeamEntity extends AbstractEntity{
    @Id
    private String id;
    @Column(name="name_chinese",length = 50,nullable = false)
    private String nameCh;
    @Column(name="name_english",length = 50,nullable = false)
    private String nameEn;
    @Column(name="chimpion",length = 200)
    private String chimpion;
    @Column(name="retire_cloth",columnDefinition = "TEXT")
    private String retireCloth;
    @Column(name="logo",length = 50)
    private String logo;
    @Column(name="short_name",length = 10,nullable = false)
    private String shortName;
    @Column(name="detail_url",length = 100,nullable = false)
    private String detailUrl;

    @Transient
    private String logoStore = "";

    @OneToMany(mappedBy = "teamEntity",cascade = CascadeType.ALL)
    private Set<TeamInMatchEntity> teamInMatchEntitySet = new HashSet<TeamInMatchEntity>();

    public TeamEntity() {
        id = GenerateId.generate();
    }

    public TeamEntity(String nameCh, String nameEn, String chimpion, String retireCloth, String logo,String shortName,String detailUrl) {
        this.nameCh = nameCh;
        this.nameEn = nameEn;
        this.chimpion = chimpion;
        this.retireCloth = retireCloth;
        this.logo = logo;
        this.detailUrl = detailUrl;
        this.shortName = shortName;
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

    public String getChimpion() {
        return chimpion;
    }

    public void setChimpion(String chimpion) {
        this.chimpion = chimpion;
    }

    public String getRetireCloth() {
        return retireCloth;
    }

    public void setRetireCloth(String retireCloth) {
        this.retireCloth = retireCloth;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getLogoStore() {
        return logoStore;
    }

    public void setLogoStore(String logoStore) {
        this.logoStore = logoStore;
    }
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<TeamInMatchEntity> getTeamInMatchEntitySet() {
        return teamInMatchEntitySet;
    }

    public void setTeamInMatchEntitySet(Set<TeamInMatchEntity> teamInMatchEntitySet) {
        this.teamInMatchEntitySet = teamInMatchEntitySet;
    }
}
