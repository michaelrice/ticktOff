package com.toastcoders.gnatspray

class Queue {

    String name
    Collection tickets
    List categories
    List statuses

    static hasMany = [
        tickets: Ticket,
        categories: Category,
        statuses: Status,
    ]

    static constraints = {
        name blank: false, unique: true, pattern: /[-\.a-zA-Z0-9 ]+/
    }
}
