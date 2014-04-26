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
    String closureComment
    String detailedDescription

    static belongsTo =
            [project:Project]

    static constraints = {

    }

    static mapping = {
        closureComment type: 'text'
        detailedDescription type: 'text'
    }
}
