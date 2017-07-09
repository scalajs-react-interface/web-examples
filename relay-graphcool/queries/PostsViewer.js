
graphql` fragment PostsViewer on Viewer {
                                ...PostViewer
                              allPosts(last: 100, orderBy: createdAt_DESC) @connection(key: "ListPage_allPosts", filters: []) {
                                edges {
                                  node {
                                   id
                                    ...PostData
                                  }
                                }
                              }
                          } `
       