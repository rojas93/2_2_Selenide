package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PageCardDelivery {

    public static String subInputXpath = "//*[@class='input__sub']";

    public static String fieldCityXpath = "//*[@data-test-id='city']";
    public SelenideElement inputCity = $(By.xpath(fieldCityXpath + "//input"));
    public SelenideElement subInputCity = $(By.xpath(fieldCityXpath + subInputXpath));

    public static String fieldDateXpath = "//*[@data-test-id='date']";
    public SelenideElement inputDate = $(By.xpath(fieldDateXpath + "//input"));
    public SelenideElement subInputDate = $(By.xpath(fieldDateXpath + subInputXpath));

    public static String fieldNameXpath = "//*[@data-test-id='name']";
    public SelenideElement inputName = $(By.xpath(fieldNameXpath + "//input"));
    public SelenideElement subInputName = $(By.xpath(fieldNameXpath + subInputXpath));

    public static String fieldPhoneXpath = "//*[@data-test-id='phone']";
    public SelenideElement inputPhone = $(By.xpath(fieldPhoneXpath + "//input"));
    public SelenideElement subInputPhone = $(By.xpath(fieldPhoneXpath + subInputXpath));

    public static String fieldAgreementXpath = "//*[@data-test-id='agreement']";
    public SelenideElement fieldAgreement = $(By.xpath( fieldAgreementXpath));
    public SelenideElement inputAgreement = $(By.xpath( fieldAgreementXpath + "/span"));
    public SelenideElement buttonSubmit = $(By.xpath("//button//*[text()='Забронировать']"));

    public SelenideElement blockSucsess = $(By.xpath("//*[@data-test-id='notification']"));

}
