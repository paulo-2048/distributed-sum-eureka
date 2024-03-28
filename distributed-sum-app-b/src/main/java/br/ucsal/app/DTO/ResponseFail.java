package br.ucsal.app.DTO;

import lombok.Getter;
import lombok.Setter;


public class ResponseFail extends ApiResponse {

  @Getter
  @Setter
  private Object data;

  @Getter
  private String title;

  @Getter
  private int status;
  
  public ResponseFail(String message) {
    super(message);

    this.title = "Error";
    this.status = 404;
  }

  public ResponseFail(String message, Object data) {
    super(message);
    this.data = data;

    this.title = "Error";
    this.status = 404;
  }

  public ApiResponse OnlyMessage() {
    return new ApiResponse(getMessage());
  }
}
