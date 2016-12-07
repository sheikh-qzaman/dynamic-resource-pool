package com.cisco.pool;

import hudson.model.Job;
import hudson.model.Run;

public class Utils {
	public static Job<?, ?> getProject(Run<?, ?> build) {
		Object p = build.getParent();
		return (Job<?, ?>) p;
	}
}
