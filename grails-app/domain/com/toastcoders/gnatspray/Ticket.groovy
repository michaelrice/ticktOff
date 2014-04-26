package com.toastcoders.gnatspray

class Ticket {

    Date dateCreated
    Date lastUpdated
    Date dateClosed
    Date dueDate
    User openedBy
    User closedBy
    boolean isClosed = false
    TaskType taskType
    TaskSeverity taskSeverity
    TaskPriority taskPriority
    int percentComplete
    boolean isPrivate = false
    Ticket parent
    String closureComment
    String detailedDescription

    static belongsTo = [queue: Queue]

    static hasMany = [
        children: Ticket,
        relatives: Ticket,
    ]

    static constraints = {
        dateClosed nullable: true
        dueDate    nullable: true
        closedBy   nullable: true
        parent     nullable: true
    }

    static mapping = {
        closureComment type: 'text'
        detailedDescription type: 'text'
    }
}
