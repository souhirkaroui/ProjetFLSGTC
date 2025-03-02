pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'souhirks'  // Ex: Docker Hub, GitHub Container Registry, etc.
        FRONTEND_IMAGE = 'frontend-application'
        BACKEND_IMAGE = 'Authentifcation-Verif-Email'
        K8S_NAMESPACE = 'my-app'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/souhirkaroui/ProjetFLSGTC.git'
            }
        }
        
        stage('Build & test') {
            steps {
                sh 'mvn clean package -DskipTests=true'
                sh 'mvn test'
            }
        }
        stage('Build Frontend') {
            steps {
                script {
                    dir('frontend-application') {
                        sh 'docker build -t $DOCKER_REGISTRY/$FRONTEND_IMAGE:latest .'
                    }
                }
            }
        }

        stage('Build Backend') {
            steps {
                script {
                    dir('Authentifcation-Verif-Email') {
                        sh 'docker build -t $DOCKER_REGISTRY/$BACKEND_IMAGE:latest .'
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker', url: "")  {
                        sh 'docker push $DOCKER_REGISTRY/$FRONTEND_IMAGE:latest'
                        sh 'docker push $DOCKER_REGISTRY/$BACKEND_IMAGE:latest'
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
                    kubectl apply -f verifmaildeploy.yml
                    kubectl apply -f ingress.yaml
                    """
                }
            }
        }
    }
  }
}
