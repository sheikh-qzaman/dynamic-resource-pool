package com.cisco.sanity;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import jenkins.model.Jenkins;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Extension
public class ResourcePoolManager extends GlobalConfiguration{

	private List<Resource> resources; 

	public ResourcePoolManager() {
		this.resources = new ArrayList<Resource>();
		load();
		System.out.println(resources.toString());
	}

	public List<Resource> getResources() {
		return resources;
	}

	@Override
	public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
		try {
			List<Resource> newResouces = req.bindJSONToList( Resource.class, json.get("resources"));
			/*for (Resource r : newResouces) {
				Resource old = fromName(r.getName());
				if (old != null) {
					r.setBuild(old.getBuild());
					r.setQueued(r.getQueueItemId(), r.getQueueItemProject());
				}
			}*/
			resources = newResouces;
			save();
			return true;
		} catch (JSONException e) {
			return false;
		}
	}
	@Override
	public String getDisplayName() {
		return "External Resources";
	}
	
	public static ResourcePoolManager get() {
		return (ResourcePoolManager) Jenkins.getInstance().getDescriptorOrDie(ResourcePoolManager.class);
	}
}
