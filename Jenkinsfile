pipeline {
    agent any

    environment {
        CONTAINER_NAME = 'demo'
        SPRING_PROFILES_ACTIVE = 'dev'   // Fixed typo in variable name
        SHELL = 'C:\\Program Files\\Git\\bin\\bash.exe'
    }

    stages {
        stage('Compile and Clean') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Deploy') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Start Docker Compose') {
            steps {
                sh 'docker-compose down || true'
                sh 'docker-compose up -d --build'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([string(credentialsId: 'StudentDetailsID', variable: 'StudentDetailsPwd')]) {
                    sh 'docker login -u studentdetails -p ${StudentDetailsPwd}'
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker tag demo:latest demo:${BUILD_NUMBER}'
                sh 'docker push demo:${BUILD_NUMBER}'
            }
        }

        stage('Clean & Redeploy') {
            steps {
                script {
                    def containerExists = sh(script: "docker ps -aq -f name=${CONTAINER_NAME}", returnStdout: true).trim()
                    if (containerExists) {
                        echo "Stopping and removing existing container: ${CONTAINER_NAME}"
                        sh "docker stop ${CONTAINER_NAME}"
                        sh "docker rm ${CONTAINER_NAME}"
                    } else {
                        echo "No existing container found with name ${CONTAINER_NAME}"
                    }

                    // Remove old image (except the current one)
                    sh '''
                        OLD_IMAGE_ID=$(docker images demo -q | tail -n +2)
                        if [ -n "$OLD_IMAGE_ID" ]; then
                            echo "Removing old image..."
                            docker rmi -f $OLD_IMAGE_ID || true
                        else
                            echo "No old images to remove."
                        fi
                    '''

                    echo "Deploying new container..."
                    sh 'docker run -d --name ${CONTAINER_NAME} -e SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE} -p 8086:8096 demo:${BUILD_NUMBER}'
                }
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            }
        }
    }
}
