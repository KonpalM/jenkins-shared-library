def call(Map args = [:]) {
    def utils = new org.example.Utils()

    // Required params
    def image = args.get('image', null)
    def tag   = args.get('tag', 'latest')
    def registry = args.get('registry', 'docker.io')

    if (!image) {
        error "You must provide 'image' param to dockerBuildAndPush"
    }

    stage("Docker Build & Push") {
        sh """
            docker build -t ${registry}/${image}:${tag} .
            docker push ${registry}/${image}:${tag}
        """

        echo "Successfully built and pushed ${registry}/${image}:${tag}"
    }

    // Example using helper class
    utils.notify("Docker image ${registry}/${image}:${tag} built & pushed.")
}
