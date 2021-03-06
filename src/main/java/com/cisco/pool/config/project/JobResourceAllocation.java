package com.cisco.pool.config.project;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.cisco.pool.config.system.ResourcePool;
import com.cisco.pool.config.system.ResourcePoolManager;

import hudson.Extension;
import hudson.model.AutoCompletionCandidates;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JobResourceAllocation extends JobProperty<Job<?,?>>{
	private List<ResourcePoolRequirement> resourcePools = new ArrayList<ResourcePoolRequirement>();
	
	@DataBoundConstructor
	public  JobResourceAllocation(List<ResourcePoolRequirement> resourcePools) {
		super();
		this.resourcePools = resourcePools;
	}

	public List<ResourcePoolRequirement> getResourcePools() {
		return this.resourcePools;
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
			JSONObject json = formData.getJSONObject("required-resource-pools");
			if (json.isNullObject()) {
				return null;
			}

			List<ResourcePoolRequirement> requiredResourcePools = req.bindJSONToList( ResourcePoolRequirement.class, json.get("resourcePools"));
			return new JobResourceAllocation(requiredResourcePools);
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
