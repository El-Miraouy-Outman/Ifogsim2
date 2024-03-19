package org.fog.mobilitydata;

import org.cloudbus.cloudsim.core.CloudSim;
import org.fog.entities.FogDevice;
import org.fog.placement.LocationHandler;
import org.fog.test.perfeval.TranslationServiceFog_RandomMobility_Clustering_kmeans;
import org.fog.utils.Config;
import org.json.simple.JSONObject;
import org.variable.Variable;

import java.util.*;


/**
 * @author Hamza ELHAOU : hamzaelhaou.2000@gmail.com
 */
public class Clustering_SOM {
	static double distance1=0 ;
	static double distance2=0 ;
    
    public void createClusterMembers(int parentId, int nodeId, JSONObject locatorObject) {
    	 int numClusters = 10;
         int numIterations = 1000;
         double learningRate = 0.1;
         double decayRate = 0.5;

    	LocationHandler locatorTemp = new LocationHandler();
    	   locatorTemp = (LocationHandler) locatorObject.get("locationsInfo");
    	   double[][] data = new double[200][2];
    	   int[] clusters = new int[data.length];
         // changer borne final avec 3 pour chaqiue user
        // avant changer  117 157
        // pas =3 pour chaque user(+1 user = +3 dans   debut )
        //debut pour
        int debut=16 + 3 * TranslationServiceFog_RandomMobility_Clustering_kmeans.numberOfMobileUser;;
    	for(int i =0 ;i<=117; i++ ) {

    		 int id=i+debut ;
    		  double fogNodePositionX =
                      locatorTemp.dataObject.resourceLocationData.get(locatorTemp.instanceToDataId.get(id)).latitude;
    	        double fogNodePositionY =
                        locatorTemp.dataObject.resourceLocationData.get(locatorTemp.instanceToDataId.get(id)).longitude;
                 //System.out.println("res : "+locatorTemp.instanceToDataId.get(id)+"    latitude : "+fogNodePositionX +"   longitud : "+fogNodePositionX);
    	      data[i][0]=fogNodePositionX;
    	      data[i][1]=fogNodePositionY;
    	}
        /*System.out.println("data ====================================================================");
        for ( double[] d: data) {
            System.out.println(Arrays.toString(d));
        }*/
    	  Kohonen network = new Kohonen(data[0].length, numClusters, learningRate, decayRate);
          network.train(data, numIterations);
          for (int i = 0; i < data.length; i++) {
              clusters[i] = network.classify(data[i]);
          }
          
          
          List<Integer> clusterMemberList = new ArrayList<>();
          int fogId = nodeId;
 		   int groupe=clusters[fogId-debut] ;
 		for(int j=0 ;j<clusters.length;j++) {
 			
 	    	int groupeFog=clusters[j];
 	    	
 	    	if(groupe==groupeFog)  clusterMemberList.add(j+debut);
 	    	
 	    	}
	
		
     

        if (clusterMemberList.isEmpty() || clusterMemberList.size() < 1) {
            ((FogDevice) CloudSim.getEntity(fogId)).setSelfCluster(true);
            ((FogDevice) CloudSim.getEntity(fogId)).setIsInCluster(true);
        } else {
            ((FogDevice) CloudSim.getEntity(fogId)).setIsInCluster(true);
            ((FogDevice) CloudSim.getEntity(fogId)).setSelfCluster(false);
            ((FogDevice) CloudSim.getEntity(fogId)).setClusterMembers(clusterMemberList);
            Map<Integer, Double> latencyMapL2 = new HashMap<>();
            for (int id : clusterMemberList) {
                latencyMapL2.put(id, Config.clusteringLatency);
            }
            ((FogDevice) CloudSim.getEntity(fogId)).setClusterMembersToLatencyMap(latencyMapL2);

        }
        //System.out.println("The Fog Device: " + locatorTemp.instanceToDataId.get(fogId) + " with id: " + fogId + " and parent id: " + parentId +
          //      " has these cluster members: " + ((FogDevice) CloudSim.getEntity(fogId)).getClusterMembers());
        
        
        return;
    }  
}
