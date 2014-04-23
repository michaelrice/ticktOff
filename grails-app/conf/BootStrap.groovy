import com.toastcoders.gnatspray.User
import com.toastcoders.gnatspray.Role
import com.toastcoders.gnatspray.UserRole


class BootStrap {

    def init = { servletContext ->
        grails.util.Environment environment = grails.util.Environment.getCurrentEnvironment()
        if(environment == grails.util.Environment.DEVELOPMENT) {
            Role adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            Role userRole = new Role(authority: 'ROLE_USER').save(flush: true)
            User admin = new User(
                    username: "hero",
                    password: "secure",
                    firstName: "Michael",
                    lastName: "Rice",
                    displayName: "mike",
                    isAdmin: true,
                    enabled: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false
            ).save(flush: true)
            User user = new User(
                    username: "user",
                    password: "secure",
                    firstName: "Mike",
                    lastName: "Rice",
                    displayName: "michael",
                    isAdmin: false,
                    enabled: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false
            ).save(flush: true)
            UserRole.create(admin,adminRole,true)
            UserRole.create(user,userRole,true)
        }
    }
    def destroy = {
    }
}
