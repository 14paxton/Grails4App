package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.buildtestdata.BuildDataTest
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import grails.testing.spring.AutowiredTest
import grails.testing.web.GrailsWebUnitTest
import spock.lang.Specification

import grails.buildtestdata.mixin.Build

@Build([Job, Tag, Type, Publisher])
class StatisticsServiceSpec extends Specification implements AutowiredTest, DataTest, BuildDataTest, ServiceUnitTest<StatisticsService>, GrailsWebUnitTest{

    def setupSpec(){
        mockDomain Job
        mockDomain Tag
        mockDomain Type
        mockDomain Publisher
    }
    def setup() {
    }

    def cleanup() {
    }

    void "get top publishers when we don't have nothing in our system"() {
         given: "when we don't have any job published"

         when: "we get top publishers"
         def publishers = service.getTopPublishers()
         then:"we will see 0 publishers"
         publishers.size() == 0
         }

    void "get top publishers when we have multiple jobs published by the same publisher"() {
        given: "when we have one 2 jobs published by the same publisher"
        def tag = Tag.build()
        def type = Type.build()
        def publisher = Publisher.build()
        Job.build(publisher: publisher, type: type, tags: [tag])
        Job.build(publisher: publisher, type: type, tags: [tag])

        when: "we get top publishers"
        def publishers = service.getTopPublishers()
        def pair = publishers.find { key, value -> key.name.equals(publisher.name) }
        then:"we will see 2 publishers"
        publishers.size() == 1
        pair?.value == 2
    }

}
