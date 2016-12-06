package com.cisco.pool.config.system;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class ResourcePool extends AbstractDescribableImpl<ResourcePool>{
	private String name;
	private String description;
	private String type;
	private List<Resource> resources = new ArrayList<Resource>();

	@DataBoundConstructor
	public ResourcePool(String name, String description, String type, List<Resource> resources) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.resources = resources;
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

	public List<Resource> getResources() {
		return resources;
	}

	@Extension
    public static class DescriptorImpl extends Descriptor<ResourcePool> {
        public String getDisplayName() { return ""; }
    }
}
