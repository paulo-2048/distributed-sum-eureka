package br.ucsal.app.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

import br.ucsal.app.DTO.ApiResponse;
import br.ucsal.app.DTO.ResponseFail;
import br.ucsal.app.DTO.ResponseSuccess;
import br.ucsal.app.entity.OperationNumbers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

@RestController
public class SumAppController {

  @Autowired
  @Lazy
  private EurekaClient eurekaClient;

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping("/health")
  public ResponseEntity<ApiResponse> healthy() {
    return ResponseEntity
        .ok(new ResponseSuccess("Estou vivo e bem! Sou a app " + appName + " - " + LocalDateTime.now()));
  }

  @GetMapping("/discover")
  public ResponseEntity<ApiResponse> discover() {
    Applications otherApps = eurekaClient.getApplications();

    return ResponseEntity.ok(new ResponseSuccess("Aplicações registradas: ", otherApps.getRegisteredApplications()));
  }

    @GetMapping("/actuator/info")
  public ResponseEntity<ApiResponse> info() {
    Application thisApp = eurekaClient.getApplications().getRegisteredApplications(appName);

    return ResponseEntity.ok(new ResponseSuccess("Informações da aplicação", thisApp));
  }

  @PostMapping("/receiveCall/{name}")
  public ResponseEntity<ApiResponse> receiveCall(@PathVariable String name, @RequestBody String message) {
    return ResponseEntity
        .ok(new ResponseSuccess(message + "\nOlá " + name + ". Aqui é " + appName + " e recebi sua mensagem."));
  }

  @GetMapping("/makeCall/{name}")
  public ResponseEntity<ApiResponse> makeCall(@PathVariable String name) throws URISyntaxException {
    String message = "Olá, tem alguem ai?!";

    List<InstanceInfo> instances = eurekaClient.getInstancesById(name);

    InstanceInfo instance = instances.getFirst();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://" + instance.getIPAddr() + ":" + instance.getPort() + "/receiveCall/" + appName))
        .POST(HttpRequest.BodyPublishers.ofString(message))
        .build();
    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      return ResponseEntity.ok(new ResponseSuccess(response.body().toString()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/randomCall")
  public ResponseEntity<ApiResponse> randomCall() throws URISyntaxException {

    String instancePath = "192.168.0.191:eureka-client-app-c:8084";

    List<InstanceInfo> instances = eurekaClient.getInstancesById(instancePath);

    if (instances.isEmpty()) {
      return ResponseEntity.badRequest()
          .body(new ResponseFail("Instância C(" + instancePath + ") não encontrada.", -2));
    }

    InstanceInfo instance = instances.getFirst();

    Random random = new Random();
    int min = 1;
    int max = 10;
    int value = random.nextInt(max - min + 1) + min;

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://" + instance.getIPAddr() + ":" + instance.getPort() + "/randomCall"))
        .GET()
        .build();
    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      Gson gson = new Gson();

      if (response.statusCode() >= 400) {
        ResponseFail apiResponse = gson.fromJson(response.body().toString(), ResponseFail.class);
        
        return ResponseEntity.badRequest().body(apiResponse);
      }

      ResponseSuccess apiResponse = gson.fromJson(response.body().toString(), ResponseSuccess.class);


      OperationNumbers operationNumber = new OperationNumbers((LinkedTreeMap) apiResponse.getData());

      operationNumber.sumNumber(value);
      
      // operationNumber.sumNumber(value * 4);
      // operationNumber.subtractNumber(value);

      return ResponseEntity.ok(new ResponseSuccess("Dados recuperados com sucesso", operationNumber));
    } catch (IOException e) {
      return ResponseEntity.badRequest().body(new ResponseFail("Ocorreu uma erro na aplicação", e.getMessage()));
    } catch (InterruptedException e) {
      return ResponseEntity.badRequest().body(new ResponseFail("Ocorreu uma erro na aplicação", e.getMessage()));
    }

  }

}
