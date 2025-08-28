def call(Map args = [:]) {
    // Initialize Utils with the current pipeline context
    def utils = new org.example.Utils(this)

    // Required params
    def image    = args.get('image', null)
    def tag      = args.get('tag', 'latest')
    def registry = args.get('registry', 'docker.io')

    if (!image) {
        error "You must provide 'image' param to dockerBuildAndPush"
    }

    stage("Docker Build & Push") {
        if (isUnix()) {
            // Linux/Mac agents
            sh """
                docker build -t ${registry}/${image}:${tag} .
                docker push ${registry}/${image}:${tag}
            """
        } else {
            // Windows agents
            bat """
                docker build -t ${registry}\\${image}:${tag} .
                docker push ${registry}\\${image}:${tag}
            """
        }

        echo "Successfully built and pushed ${registry}/${image}:${tag}"
    }

    // Example using helper class
    utils.notify("Docker image ${registry}/${image}:${tag} built & pushed.")
}
