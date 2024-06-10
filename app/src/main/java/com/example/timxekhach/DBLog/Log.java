package com.example.timxekhach.DBLog;


public class Log {
    private int LogID;
    private String Account;
    private int IdXe;
    private String TenXe;

    public Log() {
    }

    public Log(int logID, String account, int idXe, String tenXe) {
        LogID = logID;
        Account = account;
        IdXe = idXe;
        TenXe = tenXe;
    }

    public Log(String account, int idXe, String tenXe) {
        Account = account;
        IdXe = idXe;
        TenXe = tenXe;
    }

    public int getLogID() {
        return LogID;
    }

    public void setLogID(int logID) {
        LogID = logID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public int getIdXe() {
        return IdXe;
    }

    public void setIdXe(int idXe) {
        IdXe = idXe;
    }

    public String getTenXe() {
        return TenXe;
    }

    public void setTenXe(String tenXe) {
        TenXe = tenXe;
    }

}
