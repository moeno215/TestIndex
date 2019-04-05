package com.example.testindex.model;

import com.google.gson.annotations.SerializedName;

public class InsertTandaJadi{

	@SerializedName("create_by")
	private String createBy;

	@SerializedName("tanda_jadi")
	private String tandaJadi;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setTandaJadi(String tandaJadi){
		this.tandaJadi = tandaJadi;
	}

	public String getTandaJadi(){
		return tandaJadi;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"InsertTandaJadi{" + 
			"create_by = '" + createBy + '\'' + 
			",tanda_jadi = '" + tandaJadi + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}