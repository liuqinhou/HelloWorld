package com.plugin;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class MyTask extends DefaultTask {

    @TaskAction
    void action() {
        println("my task run");
    }

}
