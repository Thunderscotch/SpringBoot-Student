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
                withCredentials([usernamePassword(credentialsId: '05c2f389-259a-4696-b6f7-f4b184ae71fa', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin
                    """
                }
            }
        }

        stage('Docker Push') {
            steps {
                bat "docker push thunderscotch23/demo:${BUILD_NUMBER}"
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
