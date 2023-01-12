package com.nalsee.test;

import org.springframework.stereotype.Component;

@Component
public class VO {
    private int locationCode;
    private String date;
    private String time;
    private String TMP;
    private String PTY;
    private String POP;
    private String PCP;
    private String UUU;
    private String SKY;
    private String VVV;
    private String VEC;
    private String WAV;
    private String WSD;

	



	public String getTime() {
		return time;
	}





	public void setTime(String time) {
		this.time = time;
	}





	public String getTMP() {
		return TMP;
	}





	public void setTMP(String tMP) {
		TMP = tMP;
	}





	public int getLocationCode() {
		return locationCode;
	}





	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}





	public String getDate() {
		return date;
	}





	public void setDate(String date) {
		this.date = date;
	}





	public String getPTY() {
		return PTY;
	}





	public void setPTY(String pTY) {
		PTY = pTY;
	}





	public String getPOP() {
		return POP;
	}





	public void setPOP(String pOP) {
		POP = pOP;
	}





	public String getPCP() {
		return PCP;
	}





	public void setPCP(String pCP) {
		PCP = pCP;
	}





	public String getUUU() {
		return UUU;
	}





	public void setUUU(String uUU) {
		UUU = uUU;
	}





	public String getSKY() {
		return SKY;
	}





	public void setSKY(String sKY) {
		SKY = sKY;
	}





	public String getVVV() {
		return VVV;
	}





	public void setVVV(String vVV) {
		VVV = vVV;
	}





	public String getVEC() {
		return VEC;
	}





	public void setVEC(String vEC) {
		VEC = vEC;
	}





	public String getWAV() {
		return WAV;
	}





	public void setWAV(String wAV) {
		WAV = wAV;
	}





	public String getWSD() {
		return WSD;
	}





	public void setWSD(String wSD) {
		WSD = wSD;
	}





	@Override
    public String toString() {
        return "locationCode = " + locationCode +
                "\ndate = " + date +
                "\ntime = " + time +
                "\nTMP = " + TMP +
                "\nPTY = " + PTY +
                "\nPOP = " + POP +
                "\nPCP = " + PCP +
                "\nSKY = " + SKY +
                "\nUUU = " + UUU +
                "\nVEC = " + VEC +
                "\nVVV = " + VVV +
                "\nWAV = " + WAV +
                "\nWSD = " + WSD;
    }
}
