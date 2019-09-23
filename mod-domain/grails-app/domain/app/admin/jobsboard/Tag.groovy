package app.admin.jobsboard
import grails.rest.Resource

@Resource(uri ='/tag')
class Tag {

    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
    }


    static mapping = {
        cache true
    }

    @Override
    String toString() {
        return name
    }


}
