/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.infn.ct.GridEngine.JobCollection;

import it.infn.ct.GridEngine.Job.InfrastructureInfo;
import javax.ejb.Remote;

/**
 *
 * @author mario
 */
@Remote
public interface CollectionsSubmitterBeanRemote {

//    public void submitJobCollection(InfrastructureInfo[] infrastructures, String tcpAddress, int gridInteractionId, String DB, String DBUser, String DBUserPwd);

    public void submitJobCollection(InfrastructureInfo[] infrastructures, String tcpAddress, int gridInteractionId, JobCollection jobCollection, String DB, String DBUser, String DBUserPwd);
    
}
