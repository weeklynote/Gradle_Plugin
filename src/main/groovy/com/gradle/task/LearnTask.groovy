package com.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
/**
 *
 * @see com.gradle.bean.LearnExtension
 *
 */
class LearnTask extends DefaultTask{

    LearnTask(){
        super()
        // 默认是other
        group = 'learn'
    }

    @TaskAction
    def main(){
        println("LearnTask main method invoked---" + project.learner.toString())
        println("LearnTask main has property android---" + project.hasProperty("android"))
    }
}
