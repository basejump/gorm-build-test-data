package grails.test.app

class User {

    Integer addedNumbers //addedNumbers is calculated based on additional input properties

    User manager //self referencing toOne

    Address address //embedded domain class

    static constraints = {
        manager nullable: true
    }

    static embedded = ['address', 'profile']

}
