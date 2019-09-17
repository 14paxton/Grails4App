package app.admin.jobsboard

import grails.gorm.services.Service

@Service(Publisher)
interface PublisherService {

    Publisher get(Serializable id)

    List<Publisher> list(Map args)

    Long count()

    void delete(Serializable id)

    Publisher save(Publisher publisher)

}
