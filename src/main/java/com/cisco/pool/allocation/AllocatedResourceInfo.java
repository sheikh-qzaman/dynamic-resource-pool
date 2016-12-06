package com.cisco.pool.allocation;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.Serializable;

public class AllocatedResourceInfo extends AbstractDescribableImpl<AllocatedResourceInfo> implements Serializable{
    private String name;
    private int count;

    @DataBoundConstructor
    public AllocatedResourceInfo(String name, int count) {
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
    public static class DescriptorImpl extends Descriptor<AllocatedResourceInfo> {
        public String getDisplayName() { return ""; }
    }
}
