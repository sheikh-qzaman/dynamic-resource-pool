package com.cisco.pool.manager;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class Resource extends AbstractDescribableImpl<Resource>{
	private String name;
	private String description;
	private String type;
	private String ipAddress;
	private boolean reserverd;

	@DataBoundConstructor
	public Resource(String name, String description, String type, String ipAddress) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	
	public boolean isReserverd() {
		return reserverd;
	}

	public void setReserverd(boolean reserverd) {
		this.reserverd = reserverd;
	}

	@Extension
    public static class DescriptorImpl extends Descriptor<Resource> {
        public String getDisplayName() { return ""; }
    }
}
