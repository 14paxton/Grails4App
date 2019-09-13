package app.admin

import app.admin.jobsboard.Job
import grails.gorm.transactions.Transactional

@Transactional
class StatisticsService {

    def getTopPublishers() {

        Job.list().countBy { it.publisher}
    }

    def getTopTypes() {
        Job.list().countBy { it.type }
    }

    def getTopTags() {
        Job.list().countBy { it.tags }
    }


}
