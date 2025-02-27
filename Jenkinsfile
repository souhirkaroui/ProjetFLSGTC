pipeline {
    agent any

    stages {
        //Continuous Integration
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        //Continuous Delivery
        stage('Docker Build & Push') {
            steps {
                withDockerRegistry(credentialsId: 'docker', url: "") {
                    script {
                        def imageName = "souhirkaroui/ProjetFLSGTC/Authentifcation_Verif_Email"
                        def imageTag = "souhirks/verifmail"
                        
                        dir('Authentifcation_Verif_Email') { // Construire l'image à partir du backend uniquement
                            sh "docker build -t ${imageName}:${imageTag} ."
                            sh "docker push ${imageName}:${imageTag}"
                        }
                    }
                   // sh 'docker build -t souhirkaroui/ProjetFLSGTC/Authentifcation_Verif_Email .'
                   // sh 'docker tag souhirkaroui/ProjetFLSGTC/Authentifcation_Verif_Email souhirks/verifmail'
                   // sh 'docker push souhirks/verifmail'
               }
            }
          }
      
    // Continuous Deployment
       stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig']) { 
                    script {
                       
                       // sh 'kubectl apply -f mysql-deployment.yml'
                       sh 'kubectl apply -f verifmail-deployment.yml'
                      //  sh 'kubectl apply -f frontend-deployment.yml'

                       // sh 'kubectl apply -f compare-app-service.yml'
                      //  sh 'kubectl apply -f compare-app-ingress.yml'
                    }
                }
          }
      }   
         
    }
} 
