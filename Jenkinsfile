pipeline {
    agent any
    environment {
        CONTAINER_NAME = 'demo'
        SPRING_PROFILES_ACTIVE = 'dev'
        IMAGE_NAME = 'demo'
    }

    stages {
        stage('Build with Maven') {
            steps {
                bat './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([string(credentialsId: 'StudentDetailsID', variable: 'StudentDetailsPwd')]) {
                    bat "docker login -u studentdetails -p ${StudentDetailsPwd}"
                }
            }
        }

        stage('Docker Push') {
            steps {
                bat "docker push ${IMAGE_NAME}:${BUILD_NUMBER}"
            }
        }

        stage('Start up docker compose') {
            steps {
                bat 'docker-compose down || true'
                bat 'docker-compose up -d --build'
            }
        }
    }
}
