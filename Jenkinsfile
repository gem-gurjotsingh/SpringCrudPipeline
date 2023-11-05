pipeline {
    agent any
    tools{
        maven 'maven_3_9_2'
    }
    stages{
        stage('Build Maven & Test'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/gem-gurjotsingh/SpringCrudPipeline']])
                bat 'mvn clean verify'
            }
        }
    }
}