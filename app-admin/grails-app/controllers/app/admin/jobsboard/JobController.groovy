package app.admin.jobsboard

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_OPERATOR'])
class JobController {

    JobService jobService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond jobService.list(params), model:[jobCount: jobService.count()]
    }

    def show(Long id) {
        respond jobService.get(id)
    }

    def create() {
        respond new Job(params)
    }

    def save(Job job) {
        if (job == null) {
            notFound()
            return
        }

        try {
            jobService.save(job)
        } catch (ValidationException e) {
            respond job.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'job.label', default: 'Job'), job.id])
                redirect job
            }
            '*' { respond job, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond jobService.get(id)
    }

    def update(Job job) {
        if (job == null) {
            notFound()
            return
        }

        try {
            jobService.save(job)
        } catch (ValidationException e) {
            respond job.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'job.label', default: 'Job'), job.id])
                redirect job
            }
            '*'{ respond job, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        jobService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'job.label', default: 'Job'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'job.label', default: 'Job'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
