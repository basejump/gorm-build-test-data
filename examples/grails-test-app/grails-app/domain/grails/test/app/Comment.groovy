package grails.test.app

class Comment {

    String text

    static belongsTo = [parentComment: Comment]

    static hasMany = [replies: Comment] //circular toMany

    static constraints = {
        parentComment nullable: true
    }
}
