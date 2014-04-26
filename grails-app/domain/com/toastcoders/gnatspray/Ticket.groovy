package com.toastcoders.gnatspray

import groovy.time.Duration
import groovy.time.TimeCategory

class Ticket {

    Date dateCreated
    Date lastUpdated
    Date dateClosed
    Date dueDate
    User openedBy
    User assignee
    User closedBy
    Category category
    Integer impact
    Integer priority
    int percentComplete
    boolean isPrivate = false
    Ticket parent
    SortedSet comments

    static belongsTo = [queue: Queue]

    static hasMany = [
        children: Ticket,
        relatives: Ticket,
        comments: Comment,
        watchers: User,
    ]

    static constraints = {
        dateClosed nullable: true
        dueDate    nullable: true
        assignee   nullable: true
        closedBy   nullable: true
        impact     range: 1..10
        priority   range: 1..10
        parent     nullable: true
        comments   minSize: 1
    }

    static mapping = {
        closureComment type: 'text'
        detailedDescription type: 'text'
    }

    boolean getIsClosed() {
        return (dateClosed != null)
    }

    Boolean getMetDeadline() {
        if (dateClosed) {
            return (dateClosed < dueDate)
        }
        else {
            // Hey, there's still time left! I'll get it done...
            return null
        }
    }

    Duration getAge() {
        use(TimeCategory) {
            return (new Date() - dateCreated)
        }
    }

    Duration getTimeToResolution() {
        if (dateClosed) {
            use(TimeCategory) {
                return (new Date() - dateClosed)
            }
        }
        else {
            // Implies Ticket is unresolved.
            return null
        }
    }

    Duration getIdle() {
        // A closed Ticket cannot be idle.
        if (dateClosed) {
            return null
        }
        else {
            use(TimeCategory) {
                return (new Date() - (lastUpdated ?: dateCreated))
            }
        }
    }
}
