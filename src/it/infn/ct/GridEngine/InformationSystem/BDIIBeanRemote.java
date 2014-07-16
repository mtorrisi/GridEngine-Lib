/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.infn.ct.GridEngine.InformationSystem;

import java.net.URI;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author mario
 */
@Remote
public interface BDIIBeanRemote {

    public List<String> queryCEForSWTag(URI location, String swTag);

    public List<String> queryCEQueues(URI location, String vo);

    public List<URI> queryWMSURIs(URI location, String vo);

    public String getRandomWMS(URI location, String vo);

    public String getRandomCE(URI location, String vo);

    public String[] getCECoordinate(URI location, String ce);

    public String getRandomCEForSWTag(URI location, String tag);

    public List<String> queryCEFromSWTag_MaxCPUTime(URI location, String TAG, String VO, Integer MaxCPUTime);

    public String getRandomCEFromSWTag_MaxCPUTime(URI location, String TAG, String VO, Integer MaxCPUTime);

    public String getGlueSEImplementationName(URI location, String seHostname);
    
}
