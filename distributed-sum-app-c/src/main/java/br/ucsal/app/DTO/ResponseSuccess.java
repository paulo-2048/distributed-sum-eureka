package br.ucsal.app.DTO;

import lombok.Getter;
import lombok.Setter;

public class ResponseSuccess extends ApiResponse {

  @Getter
  @Setter
  private Object data;
  
  public ResponseSuccess(String message) {
    super(message);
  }

  public ResponseSuccess(String message, Object data) {
    super(message);
    this.data = data;
  }

  public ApiResponse OnlyMessage() {
    return new ApiResponse(getMessage());
  }
}
