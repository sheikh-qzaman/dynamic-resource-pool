package com.cisco.sanity;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class ResourcePool extends AbstractDescribableImpl<ResourcePool>{
	private List<Resource> resources = new ArrayList<Resource>();
	private String name;
	private String description;
	private String type;

	@DataBoundConstructor
	public ResourcePool(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}
	
	public List<Resource> getResources() {
		return resources;
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

	@Extension
    public static class DescriptorImpl extends Descriptor<ResourcePool> {
        public String getDisplayName() { return ""; }
    }
}
