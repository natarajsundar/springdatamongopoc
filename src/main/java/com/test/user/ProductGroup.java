package com.test.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "productgroup")
public class ProductGroup {
		
	@Id
	private String id;
	
	@Indexed
	private String ic;
	
	private String productGroupName;
	

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	public ProductGroup(String ic, String productGroupName, Date createdDate) {
		super();
		this.ic = ic;
		this.productGroupName = productGroupName;
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "ProductGroup [id=" + id + ", ic=" + ic + ", productGroupName=" + productGroupName + ", createdDate=" + createdDate + "]";
	}

}
