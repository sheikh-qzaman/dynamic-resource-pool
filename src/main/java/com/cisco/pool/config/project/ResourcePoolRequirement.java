package com.cisco.pool.config.project;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.Serializable;

public class ResourcePoolRequirement extends AbstractDescribableImpl<ResourcePoolRequirement> implements Serializable{
    private String name;
    private int count;

    @DataBoundConstructor
    public ResourcePoolRequirement(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<ResourcePoolRequirement> {
        public String getDisplayName() { return ""; }
    }
}
