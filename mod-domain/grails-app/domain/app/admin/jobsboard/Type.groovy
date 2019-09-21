package app.admin.jobsboard

class Type {

    String name
    String description

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
