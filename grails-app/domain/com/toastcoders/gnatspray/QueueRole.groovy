package com.toastcoders.gnatspray

import org.apache.commons.lang.builder.HashCodeBuilder

class QueueRole implements Serializable {

	private static final long serialVersionUID = 1

	Queue queue
	Role role

	boolean equals(other) {
		if (!(other instanceof QueueRole)) {
			return false
		}

		other.queue?.id == queue?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (queue) builder.append(queue.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static QueueRole get(long queueId, long roleId) {
		QueueRole.where {
			queue == Queue.load(queueId) &&
			role == Role.load(roleId)
		}.get()
	}

	static QueueRole create(Queue queue, Role role, boolean flush = false) {
		new QueueRole(queue: queue, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(Queue u, Role r, boolean flush = false) {

		int rowCount = QueueRole.where {
			queue == Queue.load(q.id) &&
			role == Role.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(Queue u) {
		QueueRole.where {
			queue == Queue.load(q.id)
		}.deleteAll()
	}

	static void removeAll(Role r) {
		QueueRole.where {
			role == Role.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['role', 'queue']
		version false
	}
}
