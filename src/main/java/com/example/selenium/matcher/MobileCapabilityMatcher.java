package com.example.selenium.matcher;

import org.openqa.grid.internal.utils.DefaultCapabilityMatcher;
import java.util.logging.Logger;
import java.util.Map;

public class MobileCapabilityMatcher extends DefaultCapabilityMatcher {

	private static final Logger LOG = Logger.getLogger(MobileCapabilityMatcher.class.getName());
	private static String DEVICE_NAME = "deviceName";
	private static String PLATFORM_VERSION = "platformVersion";
	private static String VERSION = "version";

	@Override
	public boolean matches(Map<String, Object> nodeCapability, Map<String, Object> requestedCapability) {
		if (!requestedCapability.containsKey(DEVICE_NAME)) {
			LOG.info("Request received don't contains deviceName! It isn't a mobile request");
			boolean basicChecks = super.matches(nodeCapability, requestedCapability);
			LOG.info("basicChecks: " + basicChecks);
			return (basicChecks);
		} else {
			LOG.info("Mobile request received! deviceName capability it's present.");
			LOG.info("************************************************************************************");
			LOG.info("NODE CAPABILITY:" + nodeCapability.size());
			nodeCapability.forEach((key, value) -> {
				LOG.info("Key : " + key + " Value : " + value);
			});
			LOG.info("######### END");

			LOG.info("REQUESTED CAPABILITY:" + requestedCapability.size());
			requestedCapability.forEach((key, value) -> {
				LOG.info("Key : " + key + " Value : " + value);
			});
			LOG.info("######### END");
			LOG.info("************************************************************************************");
			
			String platformRequested = "";
			LOG.info("Check the match with this node:" + nodeCapability.get(DEVICE_NAME) + "|"+ nodeCapability.get(PLATFORM_VERSION));
			String deviceRequested = String.valueOf(requestedCapability.get(DEVICE_NAME));
			
			
			if(requestedCapability.get(PLATFORM_VERSION)!=null && !String.valueOf(requestedCapability.get(PLATFORM_VERSION)).isEmpty()){
				platformRequested = String.valueOf(requestedCapability.get(PLATFORM_VERSION));
			}else{
				platformRequested = String.valueOf(requestedCapability.get(VERSION));	
			}
			LOG.info("Requested capabilities:" + deviceRequested + "|" + platformRequested);

			
			String platformNode="";
			if(nodeCapability.get(PLATFORM_VERSION)!=null && !String.valueOf(nodeCapability.get(PLATFORM_VERSION)).isEmpty()){
				platformNode = String.valueOf(nodeCapability.get(PLATFORM_VERSION));
			}else{
				platformNode = String.valueOf(nodeCapability.get(VERSION));	
			}
			String deviceNameNode=String.valueOf(nodeCapability.get(DEVICE_NAME));
			LOG.info("Node capabilities:" + deviceNameNode + "|" + platformNode);
			
			
			boolean isDeviceCorrect =deviceRequested.equalsIgnoreCase(deviceNameNode);
			boolean isPlatformVersionCorrect = platformRequested.equalsIgnoreCase(platformNode);
			LOG.info("isDeviceCorrect:" + isDeviceCorrect + " | isPlatformVersionCorrect:" + isPlatformVersionCorrect);
			return isDeviceCorrect && isPlatformVersionCorrect;

		}
	}
}
