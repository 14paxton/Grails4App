package app.admin

import app.admin.jobsboard.Job
import grails.gorm.transactions.Transactional

@Transactional
class StatisticsService {

    def serviceMethod() {

    }

    def getTopPublishers() {

         Job.list().countBy { it.publisher }

         }




}
