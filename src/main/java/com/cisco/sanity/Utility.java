package com.cisco.sanity;

import hudson.model.Job;
import hudson.model.Run;

public class Utility {
	public static Job<?, ?> getProject(Run<?, ?> build) {
		Object p = build.getParent();
		return (Job<?, ?>) p;
	}
}
