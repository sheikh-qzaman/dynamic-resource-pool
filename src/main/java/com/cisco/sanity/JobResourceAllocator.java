package com.cisco.sanity;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.Util;
import hudson.model.AbstractProject;
import hudson.model.AutoCompletionCandidates;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.model.Descriptor.FormException;
import net.sf.json.JSONObject;

public class JobResourceAllocator extends JobProperty<Job<?,?>>{
	private String name;
	
	@DataBoundConstructor
	public  JobResourceAllocator(String name) {
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
		public JobResourceAllocator newInstance(StaplerRequest req, JSONObject formData) throws FormException {
			if (formData.isNullObject()) {
				return null;
			}
			JSONObject json = formData.getJSONObject("required-resources");
			if (json.isNullObject()) {
				return null;
			}
			String poolName = json.getString("name");
			
			return new JobResourceAllocator(poolName);
		}
		
		public static AutoCompletionCandidates doAutoCompleteName(@QueryParameter String value) {
			AutoCompletionCandidates c = new AutoCompletionCandidates();

			if (value != null) {
				for (Resource r : ResourcePoolManager.get().getResources()) {
					if (r.getName().startsWith(value))
						c.add(r.getName());
				}
			}

			return c;
		}
	}
}
