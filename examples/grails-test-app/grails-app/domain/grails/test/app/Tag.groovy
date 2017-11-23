package grails.test.app

class Tag {

    String name

    static constraints = {
    }

    // tag::getPosts[]
    Set<Post> getPosts(Map queryArgs) {
        Long tagId = this.id
        Post.where { tags { id == tagId } }.list(queryArgs)
    }
}
