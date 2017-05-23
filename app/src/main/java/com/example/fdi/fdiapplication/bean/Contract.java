package com.example.fdi.fdiapplication.bean;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class Contract {
    private String contract;
    private String variety;
    private String newPic;
    private String upDown;
    private String change;
    private String buy;
    private String buyQuantity;
    private String sell;
    private String sellQuantity;
    private String turnover;
    private String inventory;
    private String transaction;
    private String yesterday;
    private String now;
    private String maxPic;

    public Contract(String contract, String variety, String newPic, String upDown, String change,
                    String buy, String buyQuantity, String sell, String sellQuantity, String turnover,
                    String inventory, String transaction, String yesterday, String now, String maxPic) {
        this.contract = contract;
        this.variety = variety;
        this.newPic = newPic;
        this.upDown = upDown;
        this.change = change;
        this.buy = buy;
        this.buyQuantity = buyQuantity;
        this.sell = sell;
        this.sellQuantity = sellQuantity;
        this.turnover = turnover;
        this.inventory = inventory;
        this.transaction = transaction;
        this.yesterday = yesterday;
        this.now = now;
        this.maxPic = maxPic;
    }

    public String getContract() {
        return contract;
    }

    public String getVariety() {
        return variety;
    }

    public String getNewPic() {
        return newPic;
    }

    public String getUpDown() {
        return upDown;
    }

    public String getChange() {
        return change;
    }

    public String getBuy() {
        return buy;
    }

    public String getBuyQuantity() {
        return buyQuantity;
    }

    public String getSell() {
        return sell;
    }

    public String getSellQuantity() {
        return sellQuantity;
    }

    public String getTurnover() {
        return turnover;
    }

    public String getInventory() {
        return inventory;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getYesterday() {
        return yesterday;
    }

    public String getNow() {
        return now;
    }

    public String getMaxPic() {
        return maxPic;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public void setNewPic(String newPic) {
        this.newPic = newPic;
    }

    public void setUpDown(String upDown) {
        this.upDown = upDown;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public void setBuyQuantity(String buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public void setSellQuantity(String sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setMaxPic(String maxPic) {
        this.maxPic = maxPic;
    }
}
