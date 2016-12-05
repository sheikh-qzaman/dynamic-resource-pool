package com.cisco.pool.allocation;

import com.cisco.pool.manager.ResourcePool;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.cisco.pool.manager.Resource;
import com.cisco.pool.manager.ResourcePoolManager;

import hudson.Extension;
import hudson.Util;
import hudson.model.AbstractProject;
import hudson.model.AutoCompletionCandidates;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.model.Descriptor.FormException;
import net.sf.json.JSONObject;

public class JobResourceAllocation extends JobProperty<Job<?,?>>{
	private String name;
	
	@DataBoundConstructor
	public  JobResourceAllocation(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Extension
	public static class DescriptorImpl extends JobPropertyDescriptor {

		@Override
		public String getDisplayName() {
			// TODO Auto-generated method stub
			return "This build require Resource Pool";
		}

		@Override
		public boolean isApplicable(Class<? extends Job> jobType) {
			return true;
		}
		
		@Override
		public JobResourceAllocation newInstance(StaplerRequest req, JSONObject formData) throws FormException {
			if (formData.isNullObject()) {
				return null;
			}
			JSONObject json = formData.getJSONObject("required-resources");
			if (json.isNullObject()) {
				return null;
			}
			String poolName = json.getString("name");
			
			return new JobResourceAllocation(poolName);
		}
		
		public static AutoCompletionCandidates doAutoCompleteName(@QueryParameter String value) {
			AutoCompletionCandidates c = new AutoCompletionCandidates();

			if (value != null) {
				for (ResourcePool r : ResourcePoolManager.get().getResourcePools()) {
					if (r.getName().startsWith(value))
						c.add(r.getName());
				}
			}

			return c;
		}
	}
}
