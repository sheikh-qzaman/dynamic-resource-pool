package com.cisco.pool.runner;

import com.cisco.pool.Utils;
import com.cisco.pool.config.project.JobResourceAllocation;
import com.cisco.pool.config.project.ResourcePoolRequirement;
import com.cisco.pool.config.system.Resource;
import com.cisco.pool.config.system.ResourcePoolManager;
import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.Job;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by squmruzz on 12/6/16.
 */
@Extension
public class PoolJobListener extends RunListener<Run<?, ?>> {

    @Override
    public void onStarted(Run<?, ?> build, TaskListener listener) {
        Job<?, ?> project = Utils.getProject(build);
        List<Resource> requiredResources = new ArrayList<Resource>();
        EnvVars env = new EnvVars();
        List<ResourcePoolRequirement> resourcePoolRequirements = project.getProperty(JobResourceAllocation.class).getResourcePools();
        List<Resource> allocatedResources = ResourcePoolManager.get().allocateResources(resourcePoolRequirements);
    }

    @Override
    public void onCompleted(Run<?, ?> build, TaskListener listener) {

    }

    @Override
    public void onDeleted(Run<?, ?> build) {

    }
}
