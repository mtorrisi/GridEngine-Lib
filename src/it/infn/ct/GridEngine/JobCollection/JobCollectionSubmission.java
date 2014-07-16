/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.GridEngine.JobCollection;

import it.infn.ct.GridEngine.Job.InfrastructureInfo;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mario
 */
public class JobCollectionSubmission {

    // oggetto che rappresenta la collezione da sottomettere
    private JobCollection jobCollection;
    private String DB;
    private String DBUser;
    private String DBUserPwd;
    private boolean inAppServer;
    private CollectionsSubmitterBeanRemote ejbCollectionsSubmitter;

    /**
     * Constructs a CollectionSubmission object that can be used for submission,
     * accepts a {@link Collection} object that is the submitting collection.
     *
     * @param jobCollection the submitting collection.
     * @throws java.lang.Exception
     */
    public JobCollectionSubmission(JobCollection jobCollection) throws Exception {
        // Costruttore richiamato se ci troviamo su application server.
        initEJB();
        this.jobCollection = jobCollection;
    }

    /**
     * Constructs a CollectionSubmission object that can be used for submission,
     * accepts the local database connection parameters and a {@link Collection}
     * object that is the submitting collection.
     *
     * @param dB database name
     * @param dBUser database username
     * @param dBUserPwd database password
     * @param jobCollection the submitting collection.
     * @throws java.lang.Exception
     */
    public JobCollectionSubmission(String dB, String dBUser, String dBUserPwd,
            JobCollection jobCollection) throws Exception {

        this(jobCollection); // Chiamo il costruttore di default
        // Setto i parametri di connessione locali
        this.DB = dB;
        this.DBUser = dBUser;
        this.DBUserPwd = dBUserPwd;

        this.inAppServer = false;

    }

    /**
     * Returns the submitting {@link Collection} object.
     *
     * @return the submitting collection.
     */
    public JobCollection getJobCollection() {
        return jobCollection;
    }

    /**
     * Sets the submitting {@link Collection} object.
     *
     * @param jobCollection the submitting collection.
     */
    public void setJobCollection(JobCollection jobCollection) {
        this.jobCollection = jobCollection;
    }

    /**
     * Returns <b>true</b> if the application is running on a application
     * server.
     *
     * @return <b>true</b> if the application is running on a application
     * server, <b>false</b> otherwise.
     */
    public boolean isInAppServer() {
        return inAppServer;
    }

    public void submitJobCollection(InfrastructureInfo[] infrastructures,
            String tcpAddress, int gridInteractionId) {
        this.ejbCollectionsSubmitter.submitJobCollection(infrastructures, tcpAddress, gridInteractionId, this.jobCollection ,this.DB, this.DBUser, this.DBUserPwd);
    }

    private void initEJB() throws Exception {
        try {
            this.ejbCollectionsSubmitter = (CollectionsSubmitterBeanRemote) InitialContext.doLookup("java:global/GridEngine/GridEngine-ejb/CollectionsSubmitterBean");
        } catch (NamingException ex) {
            throw new Exception("Grid Engine not available");
        }
    }
}
