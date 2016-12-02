package com.cisco.sanity;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class ResourcePool extends AbstractDescribableImpl<ResourcePool>{
	private List<Resource> resources = new ArrayList<Resource>();
	private String type;

	@DataBoundConstructor
	public ResourcePool(List<Resource> resources, String type) {
		this.resources = resources;
		this.type = type;
	}
	
	public List<Resource> getResources() {
		return resources;
	}

	public String getType() {
		return type;
	}

	@Extension
    public static class DescriptorImpl extends Descriptor<Resource> {
        public String getDisplayName() { return ""; }
    }
}
