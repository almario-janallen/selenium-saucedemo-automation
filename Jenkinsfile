pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/almario-janallen/selenium-saucedemo-automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Test Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
