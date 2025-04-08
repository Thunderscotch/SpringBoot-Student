pipeline {
    agent any
    environment {
        CONTAINER_NAME = 'demo'
        SPRING_PROFILES_ACTIVE = 'qa'
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
                bat "docker build -t thunderscotch23/${IMAGE_NAME}:${BUILD_NUMBER} ."
            }
        }
//
//         stage('Docker Login') {
//             steps {
//                 withCredentials([usernamePassword(credentialsId: '05c2f389-259a-4696-b6f7-f4b184ae71fa', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
//                     bat """
//                         echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin
//                     """
//                 }
//             }
//         }
//
//         stage('Docker Push') {
//             steps {
//                 bat "docker push thunderscotch23/demo:${BUILD_NUMBER}"
//             }
//         }
stage('Docker deploy'){
            steps {
              script {
                // Stop and remove the existing container if it exists
                def containerExists = bat(script: "docker ps -aq -f name=${CONTAINER_NAME}", returnStdout: true).trim()
//                 if (containerExists) {
//                   echo "Stopping and removing existing container: ${CONTAINER_NAME}"
//                   bat "docker stop ${CONTAINER_NAME}"
//                   bat "docker rm ${CONTAINER_NAME}"
//                 } else {
//                   echo "No existing container found with name ${CONTAINER_NAME}"
//                 }
              }

                // Remove old image if it exists
//                 bat """
//                     # Find the old image ID (excluding the latest build)
//                     OLD_IMAGE_ID=\$(docker images -q thunderscotch23/demo | tail -n +2)
//
//                     if [ -n "\$OLD_IMAGE_ID" ]; then
//                         echo "Removing old images..."
//                         docker rmi -f \$OLD_IMAGE_ID || true
//                     else
//                         echo "No old images found."
//                     fi
//                 """
              // Run a new container
              echo "Deploying new container: ${CONTAINER_NAME}"
              //sh 'docker run -itd -p  8092:8080 mentorbridge/stupro:${BUILD_NUMBER}'
              bat 'bat 'docker run -d --name %CONTAINER_NAME% -e SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE% -p 8086:8096 thunderscotch23/demo:%BUILD_NUMBER%''
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
