package app.admin.jobsboard

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TagSpec extends Specification implements DomainUnitTest<Tag> {

    def setup() {
    }

    def cleanup() {
    }

    void "completed tag creation"() {
         given: "a completely new tag"
         def tag = new Tag(name: "Remote")
         expect: "we can save a complete job tag"
         true == tag.validate()
         }

     void "can't save a tag without a name"() {
         given: "a job tag tag without a name"
         def tag = new Tag(name: "")
         expect: "we can't save the tag"
         false == tag.validate()
         }
}
