package app.admin

import app.admin.security.Role
import app.admin.security.User
import app.admin.security.UserRole


class BootStrap {

    def init = { servletContext ->

       /* def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withTransaction { status ->
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1
*/
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def operatorRole = new Role(authority: 'ROLE_OPERATOR').save()
        def customerRole = new Role(authority: 'ROLE_CUSTOMER').save()

        def adminUser = new User(username: 'admin', password: 'Password123').save()
        def operatorUser = new User(username: 'operator', password: 'Password123').save()
        def customerUser = new User(username: 'customer', password: 'Password123').save()


        UserRole.create adminUser, adminRole
        UserRole.create operatorUser, operatorRole
        UserRole.create customerUser, customerRole

        UserRole.withTransaction { status ->
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }

        def x = UserRole.findAll()

        assert User.count() == 3
        assert  Role.count() == 3
        assert UserRole.count() == 3

    }
    def destroy = {
    }
}
