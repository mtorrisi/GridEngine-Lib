/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.GridEngine.Job;

import javax.ejb.Remote;

/**
 *
 * @author Marco Fargetta <marco.fargetta@ct.infn.it>
 */
@Remote
public interface SubmissionRemote {

    //String submitJob(it.infn.ct.GridEngine.Job.InfrastructureInfo infra, it.infn.ct.GridEngine.JobResubmission.GEJobDescription jobDescription, String commonName, String tcpAddress, int interactionId, String userDescription);

//    void submitJobASync(it.infn.ct.GridEngine.Job.InfrastructureInfo infra, it.infn.ct.GridEngine.JobResubmission.GEJobDescription jobDescription, String commonName, String tcpAddress, int interactionId, String userDescription, String userEmail, String DB, String DBUser, String DbUserPwd, boolean randomCE, boolean checkJobStatus);

    
    void submitJobASync(Wrapper w);
}
