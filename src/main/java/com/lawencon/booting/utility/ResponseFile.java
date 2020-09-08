package com.lawencon.booting.utility;

public class ResponseFile {

//	 private String name;
	  private String url;
//	  private String type;
//	  private long size;

//	  public ResponseFile(String name, String url, String type, long size) {
//	    this.name = name;
//	    this.url = url;
//	    this.type = type;
//	    this.size = size;
//	  }
	  
	  public ResponseFile(String url) {
		    this.url = url;
		  }

	  public String getUrl() {
	    return url;
	  }

	  public void setUrl(String url) {
	    this.url = url;
	  }

}
