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
class TypeController {

    TypeService typeService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond typeService.list(params), model:[typeCount: typeService.count()]
    }

    def show(Long id) {
        respond typeService.get(id)
    }

    @Transactional
    def save(Type type) {
        if (type == null) {
            render status: NOT_FOUND
            return
        }
        if (type.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond type.errors
            return
        }

        try {
            typeService.save(type)
        } catch (ValidationException e) {
            respond type.errors
            return
        }

        respond type, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Type type) {
        if (type == null) {
            render status: NOT_FOUND
            return
        }
        if (type.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond type.errors
            return
        }

        try {
            typeService.save(type)
        } catch (ValidationException e) {
            respond type.errors
            return
        }

        respond type, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        typeService.delete(id)

        render status: NO_CONTENT
    }
}
