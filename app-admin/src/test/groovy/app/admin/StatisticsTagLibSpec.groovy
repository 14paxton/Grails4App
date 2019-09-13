package app.admin

import grails.buildtestdata.BuildDataTest
import grails.testing.gorm.DataTest
import grails.testing.spring.AutowiredTest
import grails.testing.web.taglib.TagLibUnitTest

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import spock.lang.Specification

import grails.buildtestdata.mixin.Build

@Build([Job, Tag, Type, Publisher])
class StatisticsTagLibSpec extends Specification implements AutowiredTest, DataTest, BuildDataTest, TagLibUnitTest<StatisticsTagLib> {

    def setupSpec(){
        mockDomain Job
        mockDomain Tag
    }

    def setup() {
        def statisticsService = new StatisticsService()
        tagLib.statisticsService = statisticsService
    }

    def cleanup() {
    }


    void "top for no publishers"() {
        expect:
        applyTemplate('<s:top type="publishers" />') == ""
    }

    void "top for multiple publishers, tags and types"() {
        given:
        def tag = Tag.build()
        def type = Type.build()
        def publisher = Publisher.build()
        Job.build(publisher: publisher, type: type, tags: [tag])
        Job.build(publisher: publisher, type: type, tags: [tag])
        expect:
        applyTemplate('<s:top type="publishers" />') == "<strong>Publishers (1)</strong> <ul>[name:2]</ul>"
        applyTemplate('<s:top type="types" />') == "<strong>Types (1)</strong> <ul>[name:2]</ul>"
        applyTemplate('<s:top type="tags" />') == "<strong>Tags (1)</strong> <ul>[[name]:2]</ul>"
    }
}
