package app.admin.jobsboard

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class JobController {

    JobService jobService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond jobService.list(params), model:[jobCount: jobService.count()]
    }

    def show(Long id) {
        respond jobService.get(id)
    }

    @Transactional
    def save(Job job) {
        if (job == null) {
            render status: NOT_FOUND
            return
        }
        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors
            return
        }

        try {
            jobService.save(job)
        } catch (ValidationException e) {
            respond job.errors
            return
        }

        respond job, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Job job) {
        if (job == null) {
            render status: NOT_FOUND
            return
        }
        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors
            return
        }

        try {
            jobService.save(job)
        } catch (ValidationException e) {
            respond job.errors
            return
        }

        respond job, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        jobService.delete(id)

        render status: NO_CONTENT
    }
}
