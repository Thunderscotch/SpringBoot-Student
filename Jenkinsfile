pipeline {
    agent any
    environment {
        CONTAINER_NAME = 'demo'
        SPRING_PROFILES_ACTIVATE = 'dev'
    }

    stages {
        stage('Clone Repository'){
            steps{
                git credentialsId: 'Jade-Admin', url:  'git@github.com:Thunderscotch/SpringBoot-StudentDetails.git'  //repo name

            }
        }
        stage('Compile and Clean'){
            steps{
                sh "mvn clean compile"
            }
        }
        stage('deploy'){
            steps{
                sh "mvn package"
            }
        }

        stage('Start up docker compose'){
            steps{
                sh 'docker-compose down || true'
                sh 'docker-compose up -d --build'
            }
        }

//         stage('Build Application'){
//             steps{
//             sh 'mvn clean package -DskipTests'
//             }
//         }

//         stage('Build Docker Image'){
//             steps{
//                 sh "docker build -t $IMAGE_NAME : ${BUILD_NUMBER} ."
//             }
//         }
        stage('Docker Login'){
            steps{
                withCredentials([string(credentialsId: 'StudentDetailsID', variable: 'StudentDetailsPwd') ]){
                    sh "docker login -u studentdetails -p ${StudentDetailsPwd}"
                }
            }
        }
        stage('Docker Push'){
            steps{
                sh 'docker push demo:${BUILD_NUMBER}'
            }
        }
//         stage('Stop & Remove Old Repository'){
//             steps{
//                 sh """
//                     docker stop $IMAGE_NAME || true
//                     docker rm $IMAGE_NAME || true
//                     """
//             }
//         }
// stage('Docker deploy'){
//             steps {
//               script {
//                 // Stop and remove the existing container if it exists
//                 def containerExists = sh(script: "docker ps -aq -f name=${CONTAINER_NAME}", returnStdout: true).trim()
//                 if (containerExists) {
//                   echo "Stopping and removing existing container: ${CONTAINER_NAME}"
//                   sh "docker stop ${CONTAINER_NAME}"
//                   sh "docker rm ${CONTAINER_NAME}"
//                 } else {
//                   echo "No existing container found with name ${CONTAINER_NAME}"
//                 }
//               }
//
//                 // Remove old image if it exists
//                 sh """
//                     # Find the old image ID (excluding the latest build)
//                     OLD_IMAGE_ID=\$(docker images -q demo | tail -n +2)
//
//                     if [ -n "\$OLD_IMAGE_ID" ]; then
//                         echo "Removing old images..."
//                         docker rmi -f \$OLD_IMAGE_ID || true
//                     else
//                         echo "No old images found."
//                     fi
//                 """
//               // Run a new container
//               echo "Deploying new container: ${CONTAINER_NAME}"
//               //sh 'docker run -itd -p  8092:8080 mentorbridge/stupro:${BUILD_NUMBER}'
//               sh 'docker run -d --name ${CONTAINER_NAME} -e SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE} -p  8086:8096 demo:${BUILD_NUMBER}'
//             }
//         }
//         stage('Archving') {
//                     steps {
//                          archiveArtifacts '**/target/*.jar'
//                     }
//                     }
//         stage('Run Docker Container') {
//             steps{
//                 sh"""
//                     docker run -d --name $IMAGE_NAME -p $HOST_PORT:$CONTAINER_PORT $IMAGE_NAME
//                     """
//                 }
//         }
}
}

