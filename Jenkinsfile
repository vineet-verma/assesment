pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
                    steps {
                        bat 'docker build -t assesment .'
                        bat 'docker run -p 8080:8080 assesment'
                    }
                }
    }
}