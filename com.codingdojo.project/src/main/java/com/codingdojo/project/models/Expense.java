package com.codingdojo.project.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="expense")
public class Expense {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min=2,max=100,message="Expense needs a name")
	private String type;
	
	@NotNull(message="Cost cannot be empty")
	@Min(0)
	private double cost;
	
	private String tag;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
//    private SimpleDateFormat month =  new SimpleDateFormat("MM"); 
    
    // ================================ RELATIPNSHIPS ================================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="budget_id")
    private Budget budget;
    
    // ================================ CONSTRUCTORS ================================
    public Expense() {
		super();
	}
	public Expense(Long id, @NotNull @Size(min = 2, max = 100, message = "Expense needs a name") String type,
			@NotEmpty(message = "Cost cannot be empty") double cost, Date createdAt, Date updatedAt, Budget budget,String tag) {
		super();
		this.id = id;
		this.type = type;
		this.cost = cost;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.budget = budget;
		this.tag = tag;
	}
	public Expense(@NotNull @Size(min = 2, max = 100, message = "Expense needs a name") String type,
			@NotEmpty(message = "Cost cannot be empty") double cost, Budget budget, String tag) {
		super();
		this.type = type;
		this.cost = cost;
		this.budget = budget;
		this.tag = tag;
	}
	public Expense(@NotNull @Size(min = 2, max = 100, message = "Expense needs a name") String type,
			@NotEmpty(message = "Cost cannot be empty") double cost, String tag) {
		super();
		this.type = type;
		this.cost = cost;
		this.tag = tag;
	}
	public Expense(@NotNull @Size(min = 2, max = 100, message = "Expense needs a name") String type,
			@NotEmpty(message = "Cost cannot be empty") double cost) {
		super();
		this.type = type;
		this.cost = cost;
	}
    
    // ================================ GETTERS / SETTERS ================================
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Budget getBudget() {
		return budget;
	}
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	 public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	}
	@PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	}
	
	
}
