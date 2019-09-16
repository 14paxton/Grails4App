package app.admin.jobsboard

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_OPERATOR'])
class PublisherController {

    PublisherService publisherService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond publisherService.list(params), model:[publisherCount: publisherService.count()]
    }

    def show(Long id) {
        respond publisherService.get(id)
    }

    def create() {
        respond new Publisher(params)
    }

    def save(Publisher publisher) {
        if (publisher == null) {
            notFound()
            return
        }

        try {
            publisherService.save(publisher)
        } catch (ValidationException e) {
            respond publisher.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'publisher.label', default: 'Publisher'), publisher.id])
                redirect publisher
            }
            '*' { respond publisher, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond publisherService.get(id)
    }

    def update(Publisher publisher) {
        if (publisher == null) {
            notFound()
            return
        }

        try {
            publisherService.save(publisher)
        } catch (ValidationException e) {
            respond publisher.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'publisher.label', default: 'Publisher'), publisher.id])
                redirect publisher
            }
            '*'{ respond publisher, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        publisherService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'publisher.label', default: 'Publisher'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'publisher.label', default: 'Publisher'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
