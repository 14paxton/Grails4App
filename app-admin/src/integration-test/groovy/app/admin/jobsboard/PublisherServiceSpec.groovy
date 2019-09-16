package app.admin.jobsboard

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PublisherServiceSpec extends Specification {

    PublisherService publisherService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Publisher(...).save(flush: true, failOnError: true)
        //new Publisher(...).save(flush: true, failOnError: true)
        //Publisher publisher = new Publisher(...).save(flush: true, failOnError: true)
        //new Publisher(...).save(flush: true, failOnError: true)
        //new Publisher(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //publisher.id
    }

    void "test get"() {
        setupData()

        expect:
        publisherService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Publisher> publisherList = publisherService.list(max: 2, offset: 2)

        then:
        publisherList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        publisherService.count() == 5
    }

    void "test delete"() {
        Long publisherId = setupData()

        expect:
        publisherService.count() == 5

        when:
        publisherService.delete(publisherId)
        sessionFactory.currentSession.flush()

        then:
        publisherService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Publisher publisher = new Publisher()
        publisherService.save(publisher)

        then:
        publisher.id != null
    }
}
