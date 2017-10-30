package com.gradle.viewer

import com.gradle.bean.LearnExtension
import com.gradle.task.LearnTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class LauncherPlugin implements Plugin<Project>{

    private final String EXTENSION_PATCHER = "learner"

    @Override
    void apply(Project project) {
        project.gradle.addListener(new TimeListener())
        println(project.name)
        addExtensions(project)
        addTasks(project)
        project.afterEvaluate {
            println("LauncherPlugin afterEvaluate method invoked---" + project.learner.toString())

        }
        /**
         * 如果执行如下的代码，需要将apply plugin添加到Project的根目录的build.gradle文件中
         */
        project.subprojects {
            println("LauncherPlugin subprojects project ---" + it.name)
            if(it.name == 'app'){
                // 应用Plugin插件
                it.apply plugin: OtherPlugin
            }
        }
    }

    private void addExtensions(Project project){
        project.extensions.create(EXTENSION_PATCHER, LearnExtension)
    }

    private void addTasks(Project project){
        // 使用TaskContainer的create方法创建Task
        project.tasks.create("LearnTaskTest", LearnTask)
    }
}
