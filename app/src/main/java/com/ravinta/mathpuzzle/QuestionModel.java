package com.ravinta.mathpuzzle;

import java.util.Random;

public class QuestionModel {
    public static int EASY_RANGE=10;
    public static int MEDIUM_RANGE=50;
    public static int HARD_RANGE=100;
    int num1,num2, numResult,range=0;
    String operation[]={"+","-","X","/"};
    String answer;



   public QuestionModel getQuestion(String level){
       QuestionModel questionModel =new QuestionModel();
       if(level.equals("easy")){
           range=EASY_RANGE;
       }
       else if(level.equals("medium")){
           range=MEDIUM_RANGE;
       }
       else {
           range=HARD_RANGE;
       }

       int op=new Random().nextInt(4);

       switch (op){
           case 0:
               questionModel.answer=operation[0];
               break;
           case 1:
               questionModel.answer = operation[1];
               break;
           case 2:
               questionModel.answer=operation[2];
               break;
           case 3:
               questionModel.answer=operation[3];
               break;
       }

       questionModel.num1=getNum();

       if(questionModel.answer.equals("-") || questionModel.answer.equals("/")||questionModel.answer.equals("X"))
       {
           questionModel.num2=getNum(questionModel.num1,questionModel.answer);
       }
       else {
           questionModel.num2=getNum();
       }
       questionModel.numResult=getNumResult(questionModel.num1,questionModel.num2,questionModel.answer);


       return questionModel;
   }



    private int getNum(){
        int num;
       if(range==EASY_RANGE) {
          num = new Random().nextInt(range);
       }
       else if(range==MEDIUM_RANGE){
           num = new Random().nextInt(range-EASY_RANGE)+EASY_RANGE;

       }
       else {
           num=new Random().nextInt(range-MEDIUM_RANGE)+MEDIUM_RANGE;
       }

        return num;
    }
    private int getNum(int num,String operator){

           int newNum = new Random().nextInt(num+1);

      if (operator.equals("/")) {
          if(newNum!=0 && num % newNum!=0){
              newNum=0;
          }

          while (newNum==0){
              newNum = new Random().nextInt(num)+1;
              if(num % newNum!=0){
                  newNum=0;
              }
          }

      }
      else if(operator.equals("X")){
          if(range!=EASY_RANGE) {
              newNum = new Random().nextInt(11)+10;
          }else {
              newNum=new Random().nextInt(range);
          }
      }
          return newNum;
    }
    private int getNumResult(int a,int b,String operator){

        switch (operator){
            case "+":

                numResult =a+b;
                break;
            case "-":
                numResult =a- b;


                break;
            case "X":
                numResult =a * b;


                break;
            case "/":
                numResult =a / b;


                break;
        }

        return numResult;
    }


}
