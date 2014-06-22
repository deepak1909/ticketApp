package com.redmart.ticket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * The Class Ticket.
 */
@Entity
@Table(name = "ticket")
public class Ticket extends CoreObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4631409202351129030L;
	
	/** The title. */
	@Column(name = "title")
	private String title;
	
	/** The customer. */
	@ManyToOne
    @JoinColumn(name = "reported_by_id")
	private Customer reportedBy;
	
	/** The assigned to. */
	@ManyToOne
    @JoinColumn(name = "assigned_to_id")
    @NotFound(action = NotFoundAction.IGNORE)
	private User assignedTo;
	
	/** The comment. */
	@Column(name = "comment")
	private String comment;
	
	/** The status. */
	@Column(name = "status")
	private String status;

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getReportedBy() {
		return reportedBy;
	}

	/**
	 * Sets the customer.
	 *
	 * @param reportedBy the new reported by
	 */
	public void setReportedBy(Customer reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * Gets the assigned to.
	 *
	 * @return the assigned to
	 */
	public User getAssignedTo() {
		return assignedTo;
	}

	/**
	 * Sets the assigned to.
	 *
	 * @param assignedTo the assigned to
	 */
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
