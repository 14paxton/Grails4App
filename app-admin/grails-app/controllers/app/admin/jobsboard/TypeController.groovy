package app.admin.jobsboard

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class TypeController {

    TypeService typeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond typeService.list(params), model:[typeCount: typeService.count()]
    }

    def show(Long id) {
        respond typeService.get(id)
    }

    def create() {
        respond new Type(params)
    }

    def save(Type type) {
        if (type == null) {
            notFound()
            return
        }

        try {
            typeService.save(type)
        } catch (ValidationException e) {
            respond type.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'type.label', default: 'Type'), type.id])
                redirect type
            }
            '*' { respond type, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond typeService.get(id)
    }

    def update(Type type) {
        if (type == null) {
            notFound()
            return
        }

        try {
            typeService.save(type)
        } catch (ValidationException e) {
            respond type.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'type.label', default: 'Type'), type.id])
                redirect type
            }
            '*'{ respond type, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        typeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'type.label', default: 'Type'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'type.label', default: 'Type'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
