/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.infn.ct.GridEngine.JobCollection;

import it.infn.ct.GridEngine.JobResubmission.GEJobDescription;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class JobCollection implements Serializable{
    
        //private static final String OUTPUT_DIR = "/jobOutput/";

//	@Id
//	@GeneratedValue
//	@Column (name="id")
    private int id; // primary key

//	@Column (name="common_name")
    private String commonName;

//	@Column (name="description")
    private String description;

//	@Column (name="task_counter")
    private int taskCounter;

//	@Column (name="user_email")
    private String userEmail;

//	@Column (name="status")
    private String collectionStatus;

//	@Column (name="start_timestamp")
    private Timestamp startTimestamp;

//	@Column (name="end_timestamp")
    private Timestamp endTimestamp;

//	@Column (name="output_path")
    private String outputPath;

//	@Transient
    private ArrayList<GEJobDescription> subJobDescriptions;

    /**
     * Constructs a JobCollection object without a user email.
     *
     * @param commonName a string that identifies the user
     * @param description a string that gives a collection description
     * @param outputPath a string that specifies the path where the output file
     * for the collection are stored
     * @param subJobDescriptions an {@link ArrayList} of
     * {@link _GEJobDescription} that represents the entire sub-job
     * descriptions.
     */
    public JobCollection(String commonName, String description,
            String outputPath, ArrayList<GEJobDescription> subJobDescriptions) {
        this(commonName, description, null, outputPath, subJobDescriptions);
    }
    
     /**
     * Constructs a JobCollection object with the specified user email to notify
     * that this job collection is completed.
     *
     * @param commonName a string that identifies the user
     * @param description a string that gives a collection description
     * @param userEmail email address to notify completed collection
     * @param outputPath a string that specifies the path where the output file
     * for the collection are stored
     * @param subJobDescriptions an {@link ArrayList} of
     * {@link _GEJobDescription} that represents the entire sub-job
     * descriptions.
     */
    public JobCollection(String commonName, String description,
            String userEmail, String outputPath,
            ArrayList<GEJobDescription> subJobDescriptions) {
        // this(commonName, description, outputPath, subJobDescriptions);
        this.commonName = commonName;
        this.description = description;
        this.outputPath = outputPath;
        this.subJobDescriptions = subJobDescriptions;
        this.taskCounter = subJobDescriptions.size();
        this.userEmail = userEmail;

//        saveJobCollection();
    }
    
    /**
     * Returns the JobCollection id.
     *
     * @return an integer that is the JobCollection id.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the JobCollection user description.
     *
     * @return JobCollection user description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets JobCollection user description
     *
     * @param description user description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the parallel tasks number.
     *
     * @return parallel tasks number.
     */
    public int getTaskCounter() {
        return taskCounter;
    }

    /**
     * Returns the user email, if it was specified in JobCollection constructor.
     *
     * @return the user email.
     */
    public String getUserEmail() {
        return userEmail;
    }
    
    /**
     * Returns the user email, if it was specified in JobCollection constructor.
     *
     * @return the user email.
     */
    public String getCollectionStatus() {
        return collectionStatus;
    }
    
    /**
     * Returns the JobCollection submission Timestamp.
     *
     * @return submission Timestamp
     */
    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Returns the timestamp when this JobCollection has been successfully
     * completed.
     *
     * @return timestamp when this JobCollection has been successfully
     * completed.
     */
    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * Returns the user common-name who submitted this JobCollection.
     *
     * @return the user common-name who submitted this JobCollection.
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Returns the path where output files of this JobCollection are stored.
     *
     * @return the path where output files of this JobCollection are stored.
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * Returns a list of _GEJobDescription objects that represent descriptions
     * for all sub-jobs of this job collection.
     *
     * @return a list of {@link _GEJobDescription} objects that represent
     * descriptions for all sub-jobs of this job collection.
     */
    public ArrayList<GEJobDescription> getSubJobDescriptions() {
        return subJobDescriptions;
    }

}
