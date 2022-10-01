pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Build JAR File') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AngelVilchesU/TINGESO_Eval1_Jenkins']]])
                sh './gradlew clean build -x test'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t angelvilches/mueblesstgo .'
            }
        }
        stage('Push docker image') {
            steps {
                withCredentials([string(credentialsId: 'dockerhubpass', variable: 'dockerpass')]) {
                    sh 'docker login -u angelvilches -p ${dockerpass}'
                }
                sh 'docker push angelvilches/mueblesstgo'
            }
        }
    }
}