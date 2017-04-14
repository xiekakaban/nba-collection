package com.statestr.entity;

import com.statestr.util.GenerateId;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

/**
 * Created by ruantianbo on 2017/4/11.
 */
@MappedSuperclass
public abstract class AbstractInMatchEntity {

    @Id
    protected String id;



    /**投篮命中率*/
    @Column(name="shoot_in_percent",length = 10)
    protected double shootInPercent;
    /**命中次数*/
    @Column(name="hit",length = 5)
    protected Integer hit;
    /**出手次数*/
    @Column(name="shot",length = 5)
    protected Integer shot;
    /**三分命中率*/
    @Column(name="three_point_shoot_in_percent",length = 5)
    protected double threePointShootInPercent;
    /**三分命中次数*/
    @Column(name="three_point_hit",length = 5)
    protected Integer threePointHit;
    /**三分出手次数*/
    @Column(name="three_point_shot",length = 5)
    protected Integer threePointShot;
    /**罚球命中率*/
    @Column(name="penalty_shoot_in_percent",length = 5)
    protected double penaltyShootInPercent;
    /**罚球命中次数*/
    @Column(name="penalth_hit",length = 5)
    protected Integer penaltyHit;
    /**罚球次数*/
    @Column(name="penalty_shot",length = 5)
    protected Integer penaltyShot;
    /**真是命中率*/
    @Column(name="real_shot_percent",length = 5)
    protected double realShotPercent;
    /**篮板*/
    @Column(name="backboard",length = 5)
    protected Integer backboard;
    /**前场*/
    @Column(name="front_court",length = 5)
    protected Integer frontCourt;
    /**后场*/
    @Column(name="back_court",length = 5)
    protected Integer backCourt;
    /**助攻*/
    @Column(name="assist",length = 5)
    protected Integer assist;
    /**抢断*/
    @Column(name="grab",length = 5)
    protected Integer grab;
    /**盖帽*/
    @Column(name = "block",length = 5)
    protected Integer block;
    /**失误*/
    @Column(name = "fault",length = 5)
    protected Integer fault;
    /**犯规*/
    @Column(name="foul",length = 5)
    protected Integer foul;
    /**得分*/
    @Column(name="score",length = 5)
    protected Integer score;
    /**是否是主场*/
    @Column(name="is_home",length = 5)
    protected Boolean isHome;


    public AbstractInMatchEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public double getShootInPercent() {
        return shootInPercent;
    }

    public void setShootInPercent(double shootInPercent) {
        this.shootInPercent = shootInPercent;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Integer getShot() {
        return shot;
    }

    public void setShot(Integer shot) {
        this.shot = shot;
    }

    public double getThreePointShootInPercent() {
        return threePointShootInPercent;
    }

    public void setThreePointShootInPercent(double threePointShootInPercent) {
        this.threePointShootInPercent = threePointShootInPercent;
    }

    public Integer getThreePointHit() {
        return threePointHit;
    }

    public void setThreePointHit(Integer threePointHit) {
        this.threePointHit = threePointHit;
    }

    public Integer getThreePointShot() {
        return threePointShot;
    }

    public void setThreePointShot(Integer threePointShot) {
        this.threePointShot = threePointShot;
    }

    public double getPenaltyShootInPercent() {
        return penaltyShootInPercent;
    }

    public void setPenaltyShootInPercent(double penaltyShootInPercent) {
        this.penaltyShootInPercent = penaltyShootInPercent;
    }

    public Integer getPenaltyHit() {
        return penaltyHit;
    }

    public void setPenaltyHit(Integer penaltyHit) {
        this.penaltyHit = penaltyHit;
    }

    public Integer getPenaltyShot() {
        return penaltyShot;
    }

    public void setPenaltyShot(Integer penaltyShot) {
        this.penaltyShot = penaltyShot;
    }

    public double getRealShotPercent() {
        return realShotPercent;
    }

    public void setRealShotPercent(double realShotPercent) {
        this.realShotPercent = realShotPercent;
    }

    public Integer getBackboard() {
        return backboard;
    }

    public void setBackboard(Integer backboard) {
        this.backboard = backboard;
    }

    public Integer getFrontCourt() {
        return frontCourt;
    }

    public void setFrontCourt(Integer frontCourt) {
        this.frontCourt = frontCourt;
    }

    public Integer getBackCourt() {
        return backCourt;
    }

    public void setBackCourt(Integer backCourt) {
        this.backCourt = backCourt;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Integer getGrab() {
        return grab;
    }

    public void setGrab(Integer grab) {
        this.grab = grab;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getFault() {
        return fault;
    }

    public void setFault(Integer fault) {
        this.fault = fault;
    }

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getHome() {
        return isHome;
    }

    public void setHome(Boolean home) {
        isHome = home;
    }
}
