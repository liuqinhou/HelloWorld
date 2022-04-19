package com.plugin

import org.gradle.api.Plugin;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println

import org.gradle.api.Project;

public class CustomPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        println("apply my plugin");
        //project.tasks.create();
        project.getTasks().create("mytask", MyTask.class);
    }
}