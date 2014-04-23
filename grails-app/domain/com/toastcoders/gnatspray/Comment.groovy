package com.toastcoders.gnatspray

class Comment {

    String comment
    User commenter
    Date dateCreated
    Date lastUpdated
    static belongsTo = [task:Task]

    static constraints = {
    }

    static mapping = {
        comment type: 'text'
    }
}
