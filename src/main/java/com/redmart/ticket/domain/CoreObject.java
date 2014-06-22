package com.redmart.ticket.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * The Class CoreObject.
 */
@MappedSuperclass
public abstract class CoreObject implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6248404638836365120L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	/** The created by. */
	@ManyToOne
	@JoinColumn(name = "create_user_id", insertable = true, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	private User createdBy;

	/** The updated by. */
	@ManyToOne
	@JoinColumn(name = "update_user_id", insertable = false, updatable = true)
	@NotFound(action=NotFoundAction.IGNORE)
	private User updatedBy;

	/** The created date. */
	@Column(name = "create_dt_tm", insertable = true, updatable = false)
	private Date createdDate;

	/** The updated date. */
	@Column(name = "update_dt_tm", insertable = false, updatable = true)
	private Date updatedDate;
	
	/** The delete_flag. */
	@Column(name = "delete_flag")
	private boolean deleteFlag;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the created by
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public User getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the updated by
	 */
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate the updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Checks if is delete_flag.
	 *
	 * @return true, if checks if is delete_flag
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * Sets the delete_flag.
	 *
	 * @param delete_flag the delete_flag
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
}
