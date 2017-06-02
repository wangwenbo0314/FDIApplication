package com.example.fdi.fdiapplication.bean;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public class Contracts {
    private String iContractID ;//合约id
    private String contractName;//合约名称
    private String prevClose;//收盘价
    private String todayOpen;//开盘价
    private String highestPrice;//最高价
    private String lowestPrice;//最低价
    private String newestPrice;//现价
    private String holdVol;//持仓量
    private String turnover;//成交量
    private String tradeTotal;//今天交易总额
    private String todayBalance;//今日交易总额
    private String prevBalance;//昨日结算价
    private String currentVol;//现量
    private String buyPrice;//买价
    private String buyVol;//买量
    private String sellPrice;//卖价
    private String sellVol;//卖量
    private String upperLimit;//涨停价
    private String lowerLimit;//跌停价
    private String market;//合约市场
    private String difference;//涨跌
    private String differenceRatio;//涨跌百分比
    private String updateTime;//更新时间

    public String getiContractID() {
        return iContractID;
    }

    public void setiContractID(String iContractID) {
        this.iContractID = iContractID;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(String prevClose) {
        this.prevClose = prevClose;
    }

    public String getTodayOpen() {
        return todayOpen;
    }

    public void setTodayOpen(String todayOpen) {
        this.todayOpen = todayOpen;
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getNewestPrice() {
        return newestPrice;
    }

    public void setNewestPrice(String newestPrice) {
        this.newestPrice = newestPrice;
    }

    public String getHoldVol() {
        return holdVol;
    }

    public void setHoldVol(String holdVol) {
        this.holdVol = holdVol;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(String tradeTotal) {
        this.tradeTotal = tradeTotal;
    }

    public String getTodayBalance() {
        return todayBalance;
    }

    public void setTodayBalance(String todayBalance) {
        this.todayBalance = todayBalance;
    }

    public String getPrevBalance() {
        return prevBalance;
    }

    public void setPrevBalance(String prevBalance) {
        this.prevBalance = prevBalance;
    }

    public String getCurrentVol() {
        return currentVol;
    }

    public void setCurrentVol(String currentVol) {
        this.currentVol = currentVol;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyVol() {
        return buyVol;
    }

    public void setBuyVol(String buyVol) {
        this.buyVol = buyVol;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellVol() {
        return sellVol;
    }

    public void setSellVol(String sellVol) {
        this.sellVol = sellVol;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getDifferenceRatio() {
        return differenceRatio;
    }

    public void setDifferenceRatio(String differenceRatio) {
        this.differenceRatio = differenceRatio;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Contracts{" +
                "iContractID='" + iContractID + '\'' +
                ", contractName='" + contractName + '\'' +
                ", prevClose='" + prevClose + '\'' +
                ", todayOpen='" + todayOpen + '\'' +
                ", highestPrice='" + highestPrice + '\'' +
                ", lowestPrice='" + lowestPrice + '\'' +
                ", newestPrice='" + newestPrice + '\'' +
                ", holdVol='" + holdVol + '\'' +
                ", turnover='" + turnover + '\'' +
                ", tradeTotal='" + tradeTotal + '\'' +
                ", todayBalance='" + todayBalance + '\'' +
                ", prevBalance='" + prevBalance + '\'' +
                ", currentVol='" + currentVol + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", buyVol='" + buyVol + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", sellVol='" + sellVol + '\'' +
                ", upperLimit='" + upperLimit + '\'' +
                ", lowerLimit='" + lowerLimit + '\'' +
                ", market='" + market + '\'' +
                ", difference='" + difference + '\'' +
                ", differenceRatio='" + differenceRatio + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}

