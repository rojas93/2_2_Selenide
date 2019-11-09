package page;

import helper.BaseHelper;

import static com.codeborne.selenide.Selenide.open;

public class ActionsCardDelivery extends PageCardDelivery {

    public void openCardDelivery() {
        open("http://localhost:9999");
    }

    public void clickButtonSubmit(){
        buttonSubmit.click();
    }

    public void clickAgreement() {
        inputAgreement.click();
    }

    public void setName(String value){
        BaseHelper.clearInput(inputName);
        inputName.setValue(value);
    }

    public void setPhone(String value){
        BaseHelper.clearInput(inputPhone);
        inputPhone.sendKeys(value);
    }

    public void setCity(String value){
        BaseHelper.clearInput(inputCity);
        inputCity.setValue(value);
    }

    public void setDate(String value){
        BaseHelper.clearInput(inputDate);
        inputDate.sendKeys(value);
    }
}
