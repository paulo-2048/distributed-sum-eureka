package br.ucsal.app.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.internal.LinkedTreeMap;

import br.ucsal.app.DTO.OperationsEnum;
import br.ucsal.app.util.OperationIdManager;

@Getter
public class OperationNumbers {

  private int id;

  private int initNumber;

  private int finalNumber;

  private ArrayList<OperationsEnum> trackOperations;

  private ArrayList<Integer> trackOperationsNumbers;

  private ArrayList<Integer> trackFinalNumber;

  private LocalDateTime createAt;


  public OperationNumbers(int initNumber) {
    this.id = OperationIdManager.generateId();
    this.initNumber = initNumber;
    this.finalNumber = initNumber;
    this.trackOperations = new ArrayList<>();
    this.trackOperationsNumbers = new ArrayList<>();
    this.trackFinalNumber = new ArrayList<>();
    this.createAt = LocalDateTime.now();
  }

  public OperationNumbers(LinkedTreeMap operationNumbers) {
    this.id = ((Double) operationNumbers.get("id")).intValue();
    this.initNumber = ((Double) operationNumbers.get("initNumber")).intValue();
    this.finalNumber = ((Double) operationNumbers.get("finalNumber")).intValue();
    this.trackOperations = new ArrayList<>();
    this.trackOperationsNumbers = new ArrayList<>();
    this.trackFinalNumber = new ArrayList<>();
    this.createAt = LocalDateTime.parse((CharSequence) operationNumbers.get("createAt"));

    ArrayList<String> operations = (ArrayList<String>) operationNumbers.get("trackOperations");
    for (String operation : operations) {
      this.trackOperations.add(OperationsEnum.valueOf(operation));
    }

    ArrayList<Double> operationsNumbers = (ArrayList<Double>) operationNumbers.get("trackOperationsNumbers");
    for (Double operationNumber : operationsNumbers) {
      this.trackOperationsNumbers.add(operationNumber.intValue());
    }

    ArrayList<Double> finalNumbers = (ArrayList<Double>) operationNumbers.get("trackFinalNumber");
    for (Double finalNumber : finalNumbers) {
      this.trackFinalNumber.add(finalNumber.intValue());
    }
  }

  public int sumNumber(int numberSum) {

    this.finalNumber += numberSum;

    this.trackOperations.add(OperationsEnum.SUM);
    this.trackOperationsNumbers.add(numberSum);
    this.trackFinalNumber.add(this.finalNumber);

    return this.finalNumber;
  }

  public int subtractNumber(int numberSubtract) {

    this.finalNumber -= numberSubtract;

    this.trackOperations.add(OperationsEnum.SUBTRACT);
    this.trackOperationsNumbers.add(numberSubtract);
    this.trackFinalNumber.add(this.finalNumber);

    return this.finalNumber;
  }

  public int multiplyNumber(int numberMultiply) {

    this.finalNumber *= numberMultiply;

    this.trackOperations.add(OperationsEnum.MULTIPLY);
    this.trackOperationsNumbers.add(numberMultiply);
    this.trackFinalNumber.add(this.finalNumber);

    return this.finalNumber;
  }

  public int divideNumber(int numberDivide) {

    this.finalNumber /= numberDivide;

    this.trackOperations.add(OperationsEnum.DIVIDE);
    this.trackOperationsNumbers.add(numberDivide);
    this.trackFinalNumber.add(this.finalNumber);

    return this.finalNumber;
  }

  public int sumNumbers(int[] numbers) {

    for (int number : numbers) {
      this.finalNumber += number;

      this.trackOperations.add(OperationsEnum.SUM);
      this.trackOperationsNumbers.add(number);
      this.trackFinalNumber.add(this.finalNumber);
    }

    return this.finalNumber;
  }

  public int subtractNumbers(int[] numbers) {

    for (int number : numbers) {
      this.finalNumber -= number;
      
      this.trackOperations.add(OperationsEnum.SUBTRACT);
      this.trackOperationsNumbers.add(number);
      this.trackFinalNumber.add(this.finalNumber);
    }

    return this.finalNumber;
  }

  public int multiplyNumbers(int[] numbers) {

    for (int number : numbers) {
      this.finalNumber *= number;

      this.trackOperations.add(OperationsEnum.MULTIPLY);
      this.trackOperationsNumbers.add(number);
      this.trackFinalNumber.add(this.finalNumber);
    }

    return this.finalNumber;
  }

  public int divideNumbers(int[] numbers) {

    for (int number : numbers) {
      this.finalNumber /= number;

      this.trackOperations.add(OperationsEnum.DIVIDE);
      this.trackOperationsNumbers.add(number);
      this.trackFinalNumber.add(this.finalNumber);
    }

    return this.finalNumber;
  }

  public int restartProcessNewInitNumber(int newInitNumber) {

    this.initNumber = newInitNumber;
    this.finalNumber = newInitNumber;
    this.trackFinalNumber.clear();

    for (int i = 0; i < this.trackOperations.size(); i++) {
      switch (trackOperations.get(i)) {
        case OperationsEnum.SUM:
          sumNumber(trackOperationsNumbers.get(i));
          this.trackOperationsNumbers.removeLast();
          this.trackOperations.removeLast();
          break;

        case OperationsEnum.SUBTRACT:
          subtractNumber(trackOperationsNumbers.get(i));
          this.trackOperationsNumbers.removeLast();
          this.trackOperations.removeLast();
          break;

        case OperationsEnum.MULTIPLY:
          multiplyNumber(trackOperationsNumbers.get(i));
          this.trackOperationsNumbers.removeLast();
          this.trackOperations.removeLast();
          break;

        case OperationsEnum.DIVIDE:
          divideNumber(trackOperationsNumbers.get(i));
          this.trackOperationsNumbers.removeLast();
          this.trackOperations.removeLast();
          break;
      
        default:
          break;
      }
    }

    return this.finalNumber;
  }

}
