/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.GridEngine.InformationSystem;

import java.net.URI;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mario
 */
public class BDII {

    private final URI location;

    BDIIBeanRemote ejbBdii = null;

    /**
     * Constructs a new {@link BDII} object given a LDAP URI
     *
     * @param location the given URI (e.g.
     * ldap://gridit-bdii-01.cnaf.infn.it:2170).
     */
    public BDII(URI location) throws Exception {
        super();
        try {
            ejbBdii = (BDIIBeanRemote) InitialContext.doLookup("java:global/GridEngine/GridEngine-ejb/BDIIBean");
        } catch (NamingException ex) {
            throw new Exception("Grid Engine not available");
        }
        //ejbBdii.setLocation(location);
        this.location = location;
    }

    /**
     * Returns the LDAP URI.
     *
     * @return LDAP URI.
     */
    public URI getLocation() {
        return location;
    }

    /**
     * Returns a computing element list for a given software tag.
     *
     * @param tag tag identifying software
     * @return a computing element list for a given software tag.
     * @throws NamingException
     */
    public List<String> queryCEForSWTag(String tag) throws NamingException {
        return ejbBdii.queryCEForSWTag(this.location, tag);
    }

    /**
     * Returns a computing element list for a given virtual organization.
     *
     * @param vo virtual organization
     * @return a computing element list for a given virtual organization.
     * @throws NamingException
     */
    public List<String> queryCEQueues(String vo) throws NamingException {
        return ejbBdii.queryCEQueues(this.location, vo);
    }

    /**
     * Returns a WMS list for a specified virtual organization.
     *
     * @param vo the virtual organization name
     * @return WMS list for a specified virtual organization.
     * @throws NamingException
     */
    public List<URI> queryWMSURIs(String vo) throws NamingException {
        return ejbBdii.queryWMSURIs(this.location, vo);
    }

    /**
     * Returns a randomly chosen WMS for a specified virtual organization.
     *
     * @param vo The virtual organization name
     * @return randomly chosen WMS for a specified virtual organization.
     */
    public String getRandomWMS(String vo) {
        return ejbBdii.getRandomWMS(this.location, vo);
    }

    /**
     * Returns a randomly chosen CE for a specified virtual organization.
     *
     * @param vo he virtual organization name
     * @return randomly chosen CE for a specified virtual organization.
     */
    public String getRandomCE(String vo) {
        return ejbBdii.getRandomCE(this.location, vo);
    }

    /**
     * Returns the geographical coordinates for a given computing element.
     *
     * @param ce computing element name
     * @return a String[] containing latitude and longitude for a given
     * computing element.
     * @throws NamingException
     */
    public String[] queryCECoordinate(String ce) throws NamingException {
        return ejbBdii.getCECoordinate(this.location, ce);
    }

    /**
     * Returns a randomly chosen computing element for a given software tag.
     *
     * @param tag tag identifying software
     *
     * @return a randomly chosen computing element for a given software tag.
     * @throws NamingException
     */
    public String getRandomCEForSWTag(String tag) throws NamingException {
        return ejbBdii.getRandomCEForSWTag(location, tag);
    }

    /**
     * Returns a computing element list for a given SWTag, virtual organization
     * and maximum CPU time.
     *
     * @param TAG tag identifying software
     * @param VO virtual organization
     * @param MaxCPUTime required maximum CPU time
     * @return a computing element list for a given SWTag, virtual organization
     * and maximum CPU time.
     * @throws NamingException
     */
    public List<String> queryCEFromSWTag_MaxCPUTime(String TAG, String VO,
            Integer MaxCPUTime) throws NamingException {
        return ejbBdii.queryCEFromSWTag_MaxCPUTime(location, TAG, VO, MaxCPUTime);

    }

    /**
     * Returns a randomly chosen computing element for a given SWTag, virtual
     * organization. and maximum CPU time.
     *
     * @param TAG tag identifying software
     * @param VO virtual organization
     * @param MaxCPUTime required maximum CPU time
     * @return a randomly chosen computing element for a given SWTag, virtual
     * organization
     * @throws NamingException
     */
    public String getRandomCEFromSWTag_MaxCPUTime(String TAG, String VO,
            Integer MaxCPUTime) throws NamingException {
        return ejbBdii.getRandomCEFromSWTag_MaxCPUTime(location, TAG, VO, MaxCPUTime);
    }

    /**
     * Retrieve the GlueSEImplementationName for a given storage resource
     *
     * @param SE_HOSTNAME storage element. (e.g.
     * ldap://iceage-se-01.ct.infn.it:2170)
     * @return the GlueSEImplementationName for a given storage resource
     * @throws NamingException
     */
    public String getGlueSEImplementationName(String SE_HOSTNAME)
            throws NamingException {
        return ejbBdii.getGlueSEImplementationName(this.location, SE_HOSTNAME);
    }
}
