package com.cisco.pool.config.system;

import java.util.ArrayList;
import java.util.List;

import com.cisco.pool.config.project.ResourcePoolRequirement;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import jenkins.model.Jenkins;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Extension
public class ResourcePoolManager extends GlobalConfiguration{

	private List<ResourcePool> resourcePools;

	public ResourcePoolManager() {
		this.resourcePools = new ArrayList<ResourcePool>();
		load();
	}

	public List<ResourcePool> getResourcePools() {
		return this.resourcePools;
	}

	@Override
	public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
		try {
			List<ResourcePool> newResoucePools = req.bindJSONToList( ResourcePool.class, json.get("resourcePools"));
			/*for (Resource r : newResouces) {
				Resource old = fromName(r.getName());
				if (old != null) {
					r.setBuild(old.getBuild());
					r.setQueued(r.getQueueItemId(), r.getQueueItemProject());
				}
			}*/
			this.resourcePools = newResoucePools;
			save();
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	public List<Resource> allocateResources(List<ResourcePoolRequirement> resourcePoolRequirements) {
		List<Resource> allocatedResources = new ArrayList<Resource>();

		for(ResourcePoolRequirement requiredPool : resourcePoolRequirements) {
			String poolName = requiredPool.getName();
			for(ResourcePool pool : resourcePools) {
				if(pool.getName().equals(poolName)) {
					List<Resource> resources = pool.getAllocateResources(requiredPool.getCount());
					allocatedResources.addAll(resources);
				}
			}
		}
		return allocatedResources;
	}

	@Override
	public String getDisplayName() {
		return "External Resources";
	}
	
	public static ResourcePoolManager get() {
		return (ResourcePoolManager) Jenkins.getInstance().getDescriptorOrDie(ResourcePoolManager.class);
	}
}
