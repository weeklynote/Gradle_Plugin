package com.gradle.viewer


import com.gradle.bean.LearnExtension

import com.gradle.task.GreetingToFileTask
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

            /**
             * 如果需要执行如下的代码，需要将apply plugin添加到Project的根目录的build.gradle文件中
             *
             * 因为apply plugin: 'net.gradle.learn'在本例子中是在根目录的build.gradle文件中引用的
             *
             * 因此在这里打印LearnExtension的值是空的，需要在Module的Project中才能有值
            */
            project.subprojects { p ->
                printProject(p)
                if(p.name == 'app'){
                    // 应用Plugin插件
                    p.apply plugin: OtherPlugin
                }
                p.afterEvaluate {
                    /**
                     *
                     * 可以通过这种方式获取Module中的Gradle配置信息
                     * 也可以通过在Module对应的Project中创建Task去获取，如在这里调用addTasks方法也可以获取到对应的Gradle配置
                     *
                     */
                    if(p.hasProperty("android")){
                        printAndroid(p.android)
                    }
                    println("LauncherPlugin afterEvaluate method invoked---" + p.learner.toString())
                }
            }
        }
        interactWithFile(project)
        println "${System.properties['os.name']} ${System.properties['os.version']} (${System.properties['os.arch']})"
    }

    /**
     * 操作文件
     */
    private void interactWithFile(Project project){
        project.tasks.create("LearnFileTest", GreetingToFileTask)
    }

    private void printProject(Project project){
        println("LauncherPlugin printProject name ---" + project.name)
        println("LauncherPlugin printProject version:" + project.version)
        println("LauncherPlugin printProject group:" + project.group)
        println("LauncherPlugin printProject gradleVersion:" + project.gradle.gradleVersion)

    }

    private void printAndroid(Object android){
        println("LauncherPlugin printAndroid class---" + android.getClass().getSimpleName())
        println("LauncherPlugin printAndroid compileSdkVersion---" + android.compileSdkVersion)
        println("LauncherPlugin printAndroid buildToolsVersion---" + android.buildToolsVersion)
        /**  defaultConfig    */
        println("LauncherPlugin printAndroid applicationId---" + android.defaultConfig.applicationId)
        println("LauncherPlugin printAndroid minSdkVersion---" + android.defaultConfig.minSdkVersion)
        println("LauncherPlugin printAndroid targetSdkVersion---" + android.defaultConfig.targetSdkVersion)
        println("LauncherPlugin printAndroid versionCode---" + android.defaultConfig.versionCode)
        println("LauncherPlugin printAndroid versionName---" + android.defaultConfig.versionName)
        /**
         * 更多的内容请查看
         * http://google.github.io/android-gradle-dsl/current/index.html
         */
        if(android.hasProperty('applicationVariants')){
            android.applicationVariants.all { variant ->
                println("variant.buildType.name " + variant.buildType.name)
                println("variant.buildType.minifyEnabled " + variant.buildType.minifyEnabled)
            }
        }
    }

    /**
     * 设置build.gradle配置信息的结构体
     * @param project
     */
    private void addExtensions(Project project){
        project.extensions.create(EXTENSION_PATCHER, LearnExtension)
    }

    private void addTasks(Project project){
        /**
         *
         * 使用TaskContainer的create方法创建Task
         * 可以使用project的hasProperty方法来判断是否存在该属性(如下的打印语句所示)
         * 然后使用project的tasks来获取对应属性的Task
         *
         * project.tasks['buildLib']
         *
         *
        */
        project.tasks.create("LearnTaskTest", LearnTask)
        println("LauncherPlugin addTasks has LearnTaskTest property ---" + project.hasProperty('LearnTaskTest'))
        /**
         * doFirst与doLast请查看Groovy的语法
         */
        project.tasks["LearnTaskTest"].doLast {
            println("LearnTaskTest doLast method invoked!!!")
        }

    }
}
