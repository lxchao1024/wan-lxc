package com.guagua.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


public class MyPlugin implements Plugin<Project> {
    void apply(Project project) {
        System.out.println("********************************");
        System.out.println("******hello, plugin by diy!*****");
        System.out.println("********************************");
    }
}