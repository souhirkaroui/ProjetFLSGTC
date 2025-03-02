pipeline {
    agent any

   stages {
        //Continuous Integration
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }       
        stage('Docker Build & Push frontend') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker', url: "")  {
                        sh 'docker build -t souhirkaroui/frontend-application .'
                        sh 'docker tag souhirkaroui/frontend-application souhirks/frontend'
                        sh 'docker push souhirks/frontend'                
                    }
                }
            }
        }
       stage('Docker Build & Push backend') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker', url: "")  {  
                        sh 'docker build -t souhirkaroui/Authentifcation_Verif_Email .'
                        sh 'docker tag souhirkaroui/Authentifcation_Verif_Email souhirks/backend'
                        sh 'docker push souhirks/backend'

                        
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig']) { 
                script {
                    sh """
                    kubectl apply -f namespace.yml
                    kubectl apply -f backenddeploy.yml
                    kubectl apply -f frontdeploy.yml
                    kubectl apply -f ingress.yaml
                    """
                }
            }
        }
    }
  }
}
