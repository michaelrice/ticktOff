package com.toastcoders.gnatspray

class Comment {

    Date dateCreated
    Date lastUpdated
    User commenter
    String comment

    static belongsTo = [ticket: Ticket]

    static constraints = {
        comment blank: false, widget: 'textArea'
    }

    static mapping = {
        comment type: 'text'
    }
}
