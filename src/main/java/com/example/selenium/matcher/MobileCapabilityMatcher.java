package com.example.selenium.matcher;

import org.openqa.grid.internal.utils.DefaultCapabilityMatcher;
import java.util.logging.Logger;
import java.util.Map;

public class MobileCapabilityMatcher extends DefaultCapabilityMatcher {

	private static final Logger LOG = Logger.getLogger(MobileCapabilityMatcher.class.getName());
	private static String DEVICE_NAME = "deviceName";
	private static String PLATFORM_VERSION = "platformVersion";
	private static String PLATFORM_NAME = "platformName";
	
	public MobileCapabilityMatcher() {
        super();
   
        
    }
	
	@Override
	public boolean matches(Map<String, Object> nodeCapability, Map<String, Object> requestedCapability) {
		

		if (!requestedCapability.containsKey(DEVICE_NAME)) {
			LOG.info("Request received don't contains deviceName! It isn't a mobile request");
			boolean basicChecks = super.matches(nodeCapability, requestedCapability);
			LOG.info("basicChecks: " + basicChecks);
			return (basicChecks);
		} else {
		     // Appium specific considerations
	        this.addToConsider(PLATFORM_NAME);
	        this.addToConsider(PLATFORM_VERSION);
	        this.addToConsider(DEVICE_NAME);
	        
			LOG.info("Mobile request received! deviceName capability it's present.");
			String listOfNodeCap="";
			String listOfReqCap="";
			
			for (String key : nodeCapability.keySet()) {
				listOfNodeCap=listOfNodeCap.concat(" | "+nodeCapability.get(key));
			}
			LOG.info("NODE CAPABILITY:" + listOfNodeCap);
			
			for (String key : requestedCapability.keySet()) {
				listOfReqCap=listOfReqCap.concat(" | "+requestedCapability.get(key));
			}
			LOG.info("REQUESTED CAPABILITY:" + listOfReqCap);
			boolean advancedCheck = super.matches(nodeCapability, requestedCapability);
			LOG.info("RESULT CHECK: "+advancedCheck);
			return(advancedCheck);
		}
	}
}
