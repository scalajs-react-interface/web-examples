
graphql`
        mutation CreatePostMutation($input: CreatePostInput!) {
          createPost(input: $input) {
            post {
              id
              description
              imageUrl
            }
          }
        }
    `
       