/**
 * *********************************************************************
 * Copyright (c) 2011: Istituto Nazionale di Fisica Nucleare (INFN), Italy
 * Consorzio COMETA (COMETA), Italy
 *
 * See http://www.infn.it and and http://www.consorzio-cometa.it for details on
 * the copyright holders.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * *********************************************************************
 */
package it.infn.ct.GridEngine.Job;

import it.infn.ct.GridEngine.JobResubmission.GEJobDescription;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Marco Fargetta <marco.fargetta@ct.infn.it>
 */
public class MultiInfrastructureJobSubmission {

    private List<InfrastructureInfo> lstInfrastructure;
    private Wrapper wrapper;
//    private InfrastructureInfo infrastructure;
    private GEJobDescription description;
    private SubmissionRemote ejbJobSubmission = null;
//
//    private String userEmail = "";
//    private boolean randomCE = false;
//
//    private String DB = "";
//    private String DBUser = "";
//    private String DBUserPwd = "";
//
//    private boolean checkJobsStatus = true;

    /**
     * Constructs a {@link MultiInfrastructureJobSubmission} object without
     * specify connection parameters to users tracking database.
     */
    public MultiInfrastructureJobSubmission() throws Exception {
        this(null, null);
    }

    /**
     * Constructs a {@link MultiInfrastructureJobSubmission} object specifying
     * the description for the submitting job.
     *
     * @param description a {@link GEJobDescription} object contains description
     * of the submitting job.
     */
    public MultiInfrastructureJobSubmission(GEJobDescription description) throws Exception {
        this(null, description);
    }

    /**
     * Constructs a {@link MultiInfrastructureJobSubmission} specifying a list
     * of possible infrastructures to which submit job.
     *
     * @param infrastructures the possible infrastructures to which submit job.
     */
    public MultiInfrastructureJobSubmission(InfrastructureInfo infrastructure) throws Exception {
        this(infrastructure, null);
    }

    /**
     * Constructs a {@link MultiInfrastructureJobSubmission} object specifying
     * connection parameters to users tracking database and and the description
     * of submitting job.
     *
     * @param db database name
     * @param dbUser database username
     * @param dbUserPwd database password
     * @param description a {@link GEJobDescription} object contains description
     * of the submitting job.
     */
    public MultiInfrastructureJobSubmission(String db, String dbUser, String dbUserPwd, GEJobDescription description) throws Exception {
        this(db, dbUser, dbUserPwd);
        this.wrapper.setJobDescription(description);
    }

    private MultiInfrastructureJobSubmission(String db, String dbUser, String dbUserPwd) throws Exception {
        initEJB();
        this.wrapper = new Wrapper();
        this.wrapper.setDB(db);
        this.wrapper.setDBUser(dbUser);
        this.wrapper.setDbUserPwd(dbUserPwd);
    }

    /**
     * Constructs a {@link MultiInfrastructureJobSubmission} specifying a list
     * of possible infrastructures to which submit job and the description for
     * this job.
     *
     * @param infrastructures list of possible infrastructures to which submit
     * job.
     * @param description a {@link GEJobDescription} object contains description
     * of the submitting job.
     */
    public MultiInfrastructureJobSubmission(InfrastructureInfo infrastructure, GEJobDescription description) throws Exception {
//        try {
//            ejbJobSubmission = (SubmissionRemote) InitialContext.doLookup("java:global/GridEngine/GridEngine-ejb/Submission");
//        } catch (NamingException ex) {
//            throw new Exception("Grid Engine not available");
//        }
        initEJB();
        this.wrapper = new Wrapper();
        if(description!=null)
            wrapper.setJobDescription(description);
        else
            wrapper.setJobDescription(new GEJobDescription());
        wrapper.setInfra(infrastructure);

    }

    public void addInfrastructure(InfrastructureInfo infra) {
        if (lstInfrastructure == null) {
            lstInfrastructure = new ArrayList<InfrastructureInfo>();
        }

        lstInfrastructure.add(infra);
    }

    public InfrastructureInfo getInfrastructure() {
        if (this.wrapper.getInfra() != null) {
            return this.wrapper.getInfra();
        } else {
            if (lstInfrastructure != null && !lstInfrastructure.isEmpty()) {
                return lstInfrastructure.get((int) (Math.random() * lstInfrastructure.size()));
            }
        }
        return null;
    }

    public void setInfrastructure(InfrastructureInfo infrastructure) {
        this.wrapper.setInfra(infrastructure);
    }

    /**
     * If true starts after job submission starts a thread that checks job
     * status. By default it is true.
     *
     * @param value if true starts a check job status thread, else not starts
     * thread.
     */
    public void setCheckJobsStatus(boolean value) {
        this.wrapper.setCheckJobStatus(value);
    }

    /**
     * Returns true if a check job status thread was started after job
     * submission, false otherwise.
     *
     * @return true if a check job status thread was started after job
     * submission, false otherwise.
     */
    public boolean getCheckJobsStatus() {
        return this.wrapper.isCheckJobStatus();
    }

    /**
     * Sets if a random CE will be chosen from given CEs list or from a bdii
     * service.
     *
     * @param value true if a random CE will be chosen, false otherwise. By
     * default it is false.
     */
    public void setRandomCE(boolean value) {
        this.wrapper.setRandomCE(value);
    }

    /**
     * Returns true if a random CE will be chosen from given CEs list or from a
     * bdii service, false otherwise.
     *
     * @return true if a random CE will be chosen from given CEs list or from a
     * bdii service, false otherwise
     */
    public boolean getRandomCE() {
        return this.wrapper.isRandomCE();
    }

    public GEJobDescription getDescription() {
        return this.wrapper.getJobDescription();
    }

    public void setDescription(GEJobDescription description) {
        this.wrapper.setJobDescription(description);
    }

    /**
     * Sets the executable in this description.
     *
     * @param executable a string that represents the executable file name.
     */
    public void setExecutable(String executable) {
        this.wrapper.getJobDescription().setExecutable(executable);
    }

    /**
     * Sets the arguments for this description.
     *
     * @param arguments a comma separated string containing the list of
     * arguments.
     */
    public void setArguments(String arguments) {
        this.wrapper.getJobDescription().setArguments(arguments);
    }

    /**
     * Sets job queue for this job.
     *
     * @param value job queue for this job.
     */
    public void setJobQueue(String value) {
//		jobQueue = value;
        this.wrapper.getJobDescription().setQueue(value);
    }

    /**
     * Returns job queue for this job.
     *
     * @return job queue for this job.
     */
    public String getJobQueue() {
//		return jobQueue;
        return this.wrapper.getJobDescription().getQueue();
    }

    /**
     * Returns the grid job identifier.
     *
     * @return the grid job identifier.
     */
    public String getjobId() {
        return this.wrapper.getJobDescription().getjobId();
    }

    /**
     * Sets the grid job identifier for the job specified by this description.
     *
     * @param activeJobId string containing grid job identifier for the job
     * specified by this description.
     */
    public void setjobId(String activeJobId) {
        this.wrapper.getJobDescription().setjobId(activeJobId);
    }

    /**
     * Sets the output file name.
     *
     * @param output the output file name.
     */
    public void setOutput(String output) {
        this.wrapper.getJobDescription().setOutput(output);
    }

    /**
     * Sets the error file name.
     *
     * @param error the error file name.
     */
    public void setError(String error) {
        this.wrapper.getJobDescription().setError(error);
    }

    /**
     * Sets the queue name for the job specified by this description.
     *
     * @param queue Queue name.
     */
    public void setQueue(String queue) {
        this.wrapper.getJobDescription().setQueue(queue);
    }

    public void setFileTransfer(String fileTransfer) {
        this.wrapper.getJobDescription().setFileTransfer(fileTransfer);
    }

    /**
     * Sets the total number of CPUs required to execute the job specified by
     * this description.
     *
     * @param totalCPUCount total number of CPUs required to execute the job.
     */
    public void setTotalCPUCount(String totalCPUCount) {
        this.wrapper.getJobDescription().setTotalCPUCount(totalCPUCount);
    }

    public void setSPDMVariation(String sPDMVariation) {
        this.wrapper.getJobDescription().setSPDMVariation(sPDMVariation);
    }

    /**
     * Sets the total number of processes required by the job specified by this
     * description.
     *
     * @param numberOfProcess total number of processes required by the job.
     */
    public void setNumberOfProcesses(String numberOfProcess) {
        this.wrapper.getJobDescription().setNumberOfProcesses(numberOfProcess);
    }

    public void setJDLRequirements(String[] jDLRequirements) {
        String s = "";
        for (int i = 0; i < jDLRequirements.length; i++) {
            if (i != 0) {
                s += ";";
            }
            s += jDLRequirements[i];
        }
        this.wrapper.getJobDescription().setJDLRequirements(s);
    }

    public void setSPMDVariation(String value) {
//		SPMDVariation = value;
        this.wrapper.getJobDescription().setSPDMVariation(value);
    }

    /**
     * Sets output path where output files of the job specified by this
     * description are stored. This method appends jobOutput folder to the
     * specified path where files are really stored.
     *
     * @param outputPath prefix of the path of the folder followed by
     * <b>/jobOuput/</b>
     * folder where output files are located..
     */
    public void setOutputPath(String outputPath) {
        this.wrapper.getJobDescription().setOutputPath(outputPath);
    }

    /**
     * Sets output file name of this Job.
     *
     * @param value a String that represents output file name of this Job.
     */
    public void setJobOutput(String value) {
//		jobOutput = value;
        this.wrapper.getJobDescription().setOutput(value);
    }

    /**
     * Sets error file name of this Job.
     *
     * @param value a String that represents error file name of this Job.
     */
    public void setJobError(String value) {
//		jobError = value;
        this.wrapper.getJobDescription().setError(value);
    }

    /**
     * Sets the input files list for the job specified by this description.
     *
     * @param inputFiles a comma separated string representing the input files
     * list.
     */
    public void setInputFiles(String inputFiles) {
        this.wrapper.getJobDescription().setInputFiles(inputFiles);
    }

    public void setOutputFiles(String value) {
        this.wrapper.getJobDescription().setOutputFiles(value);

    }

    /**
     * Sets true if the robot proxy is renewable false otherwise.
     *
     * @param proxyRenewal true if the robot proxy is renewable false otherwise
     */
    public void setProxyRenewal(boolean proxyRenewal) {
        this.wrapper.getJobDescription().setProxyRenewal(proxyRenewal);
    }

    /**
     * Sets the remaining re-submission attempts for the job represented by this
     * description
     *
     * @param resubmitCount the remaining re-submission attempts.
     */
    public void setResubmitCount(int resubmitCount) {
        this.wrapper.getJobDescription().setResubmitCount(resubmitCount);
    }

    /**
     * Sets user email to notify that the job has been successfully completed.
     *
     * @param userEmail user email to notify that the job has been successfully
     * completed.
     */
    public void setUserEmail(String userEmail) {
        this.wrapper.setUserEmail(userEmail);
    }

//    public String submitJob(String commonName, String tcpAddress, int GridInteractionId, String userDescription) {
//        return ejbJobSubmission.submitJob(infrastructure, description, commonName, tcpAddress, GridInteractionId, userDescription);
//    }
    /**
     * This method allows to submit a job in an asynchronous way to a specified
     * infrastructure It starts a separated thread responsible for the
     * submission of this job.
     *
     * @param commonName a String representing user name
     * @param tcpAddress user's IP address
     * @param GridInteractionId an identifier of application in a specified
     * portal
     * @param userDescription a description for this job.
     */
    public void submitJobAsync(String commonName, String tcpAddress, int GridInteractionId, String userDescription) {
//        ejbJobSubmission.submitJobASync(getInfrastructure(), description, commonName, tcpAddress, GridInteractionId, userDescription, this.userEmail, this.DB, this.DBUser, this.DBUserPwd, this.randomCE, this.checkJobsStatus);
        submitJobAsync(getInfrastructure(), commonName, tcpAddress, GridInteractionId, userDescription);
    }

    /**
     * This method allows to submit a job in an asynchronous way to a specified
     * infrastructure It starts a separated thread responsible for the
     * submission of this job.
     *
     * @param infrastructure infrastructure to which the job will be submitted
     * @param commonName a String representing user name
     * @param tcpAddress user's IP address
     * @param GridInteractionId an identifier of application in a specified
     * portal
     * @param userDescription a description for this job.
     */
    public void submitJobAsync(InfrastructureInfo infrastructure, String commonName, String tcpAddress, int GridInteractionId, String userDescription) {
//        ejbJobSubmission.submitJobASync(infrastructure, description, commonName, tcpAddress, GridInteractionId, userDescription, this.userEmail, this.DB, this.DBUser, this.DBUserPwd, this.randomCE, this.checkJobsStatus);
        this.wrapper.setInfra(infrastructure);
        this.wrapper.setCommonName(commonName);
        this.wrapper.setTcpAddress(tcpAddress);
        this.wrapper.setInteractionId(GridInteractionId);
        this.wrapper.setUserDescription(userDescription);
        ejbJobSubmission.submitJobASync(wrapper);
    }

    private void initEJB() throws Exception {
        try {
            ejbJobSubmission = (SubmissionRemote) InitialContext.doLookup("java:global/GridEngine/GridEngine-ejb/Submission");
        } catch (NamingException ex) {
            throw new Exception("Grid Engine not available");
        }
    }

}
