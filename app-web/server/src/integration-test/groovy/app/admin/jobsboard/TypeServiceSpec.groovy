package app.admin.jobsboard

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TypeServiceSpec extends Specification {

    TypeService typeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Type(...).save(flush: true, failOnError: true)
        //new Type(...).save(flush: true, failOnError: true)
        //Type type = new Type(...).save(flush: true, failOnError: true)
        //new Type(...).save(flush: true, failOnError: true)
        //new Type(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //type.id
    }

    void "test get"() {
        setupData()

        expect:
        typeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Type> typeList = typeService.list(max: 2, offset: 2)

        then:
        typeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        typeService.count() == 5
    }

    void "test delete"() {
        Long typeId = setupData()

        expect:
        typeService.count() == 5

        when:
        typeService.delete(typeId)
        sessionFactory.currentSession.flush()

        then:
        typeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Type type = new Type()
        typeService.save(type)

        then:
        type.id != null
    }
}
