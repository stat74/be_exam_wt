/*
 ****************************************************************************
 *
 * Copyright (c)2019 The Vanguard Group of Investment Companies (VGI)
 * All rights reserved.
 *
 * This source code is CONFIDENTIAL and PROPRIETARY to VGI. Unauthorized
 * distribution, adaptation, or use may be subject to civil and criminal
 * penalties.
 *
 ****************************************************************************
 Module Description:

 $HeadURL:$
 $LastChangedRevision:$
 $Author:$
 $LastChangedDate:$
*/
package score.data;

public class Person {

	private Integer id;
	private Name name;
	private String phone;

	public Person() {
		
	}
	
	public Person(Integer i, Name n, String p) {
		setId(i);
		setName(n);
		setPhone(p);
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
