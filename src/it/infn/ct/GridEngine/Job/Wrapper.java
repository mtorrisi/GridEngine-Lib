/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.GridEngine.Job;

import it.infn.ct.GridEngine.JobResubmission.GEJobDescription;

/**
 *
 * @author mario
 */
public class Wrapper {

    private InfrastructureInfo infra;
    private GEJobDescription jobDescription;
    private String commonName;
    private String tcpAddress;
    private int interactionId;
    private String userDescription;
    private String userEmail;
    private String DB;
    private String DBUser;
    private String DbUserPwd;
    private boolean randomCE;
    private boolean checkJobStatus = true;

    public InfrastructureInfo getInfra() {
        return infra;
    }

    public void setInfra(InfrastructureInfo infra) {
        this.infra = infra;
    }

    public GEJobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(GEJobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getTcpAddress() {
        return tcpAddress;
    }

    public void setTcpAddress(String tcpAddress) {
        this.tcpAddress = tcpAddress;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDB() {
        return DB;
    }

    public void setDB(String DB) {
        this.DB = DB;
    }

    public String getDBUser() {
        return DBUser;
    }

    public void setDBUser(String DBUser) {
        this.DBUser = DBUser;
    }

    public String getDbUserPwd() {
        return DbUserPwd;
    }

    public void setDbUserPwd(String DbUserPwd) {
        this.DbUserPwd = DbUserPwd;
    }

    public boolean isRandomCE() {
        return randomCE;
    }

    public void setRandomCE(boolean randomCE) {
        this.randomCE = randomCE;
    }

    public boolean isCheckJobStatus() {
        return checkJobStatus;
    }

    public void setCheckJobStatus(boolean checkJobStatus) {
        this.checkJobStatus = checkJobStatus;
    }

    @Override
    public String toString() {
        return "Wrapper{" + "infra=" + infra + ", jobDescription=" + jobDescription + ", commonName=" + commonName + ", tcpAddress=" + tcpAddress + ", interactionId=" + interactionId + ", userDescription=" + userDescription + ", userEmail=" + userEmail + ", DB=" + DB + ", DBUser=" + DBUser + ", DbUserPwd=" + DbUserPwd + ", randomCE=" + randomCE + ", checkJobStatus=" + checkJobStatus + '}';
    }

}
