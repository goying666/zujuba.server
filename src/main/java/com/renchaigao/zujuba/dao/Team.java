package com.renchaigao.zujuba.dao;

import java.util.Date;

public class Team {
    private Integer id;

    private String playerinfoId;

    private String gameId;

    private String addressId;

    private String spendId;

    private String messageId;

    private String filterId;

    private String playerMax;

    private String playerMin;

    private String playerNow;

    private Integer createrId;

    private Integer ownerId;

    private String createTime;

    private String startDate;

    private String startTime;

    private Date endTime;

    private String createrStyle;

    private String note;

    private String teamstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayerinfoId() {
        return playerinfoId;
    }

    public void setPlayerinfoId(String playerinfoId) {
        this.playerinfoId = playerinfoId == null ? null : playerinfoId.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }

    public String getSpendId() {
        return spendId;
    }

    public void setSpendId(String spendId) {
        this.spendId = spendId == null ? null : spendId.trim();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId == null ? null : filterId.trim();
    }

    public String getPlayerMax() {
        return playerMax;
    }

    public void setPlayerMax(String playerMax) {
        this.playerMax = playerMax == null ? null : playerMax.trim();
    }

    public String getPlayerMin() {
        return playerMin;
    }

    public void setPlayerMin(String playerMin) {
        this.playerMin = playerMin == null ? null : playerMin.trim();
    }

    public String getPlayerNow() {
        return playerNow;
    }

    public void setPlayerNow(String playerNow) {
        this.playerNow = playerNow == null ? null : playerNow.trim();
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreaterStyle() {
        return createrStyle;
    }

    public void setCreaterStyle(String createrStyle) {
        this.createrStyle = createrStyle == null ? null : createrStyle.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getTeamstate() {
        return teamstate;
    }

    public void setTeamstate(String teamstate) {
        this.teamstate = teamstate == null ? null : teamstate.trim();
    }
}