package com.code402;

public class Response {
  String[] hits;
  String error;

  public String[] getHits() {
    return hits;
  }

  public String getError() {
    return error;
  }

  public void setHits(String[] hits) {
    this.hits = hits;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Response(String[] hits, String error) {
    this.hits = hits;
    this.error = error;
  }
}
