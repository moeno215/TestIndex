package com.example.testindex.model;

import com.google.gson.annotations.SerializedName;

public class UpdateTandaJadi{

	@SerializedName("tanda_jadi")
	private Object tandaJadi;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	@SerializedName("mod_by")
	private Object modBy;

	public void setTandaJadi(Object tandaJadi){
		this.tandaJadi = tandaJadi;
	}

	public Object getTandaJadi(){
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

	public void setModBy(Object modBy){
		this.modBy = modBy;
	}

	public Object getModBy(){
		return modBy;
	}

	@Override
 	public String toString(){
		return 
			"UpdateTandaJadi{" + 
			"tanda_jadi = '" + tandaJadi + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",mod_by = '" + modBy + '\'' + 
			"}";
		}
}