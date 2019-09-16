package app.admin.jobsboard

import grails.gorm.services.Service

@Service(Type)
interface TypeService {

    Type get(Serializable id)

    List<Type> list(Map args)

    Long count()

    void delete(Serializable id)

    Type save(Type type)

}