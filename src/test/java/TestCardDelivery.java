
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.ActionsCardDelivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCardDelivery {

    @ParameterizedTest
    @CsvSource({", Поле обязательно для заполнения",
            "!@#$%^&, Доставка в выбранный город недоступна",
            "test, Доставка в выбранный город недоступна",
            "1234, Доставка в выбранный город недоступна",
            "тест, Доставка в выбранный город недоступна",
            "1т2е3с4т, Доставка в выбранный город недоступна",
            "Москва, Выберите ваш город"})
    public void testInputCity(String value, String message) {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(value == null ? "" : value);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputCity.shouldHave(exactText(message));
    }

    @ParameterizedTest
    @CsvSource({", Неверно введена дата",
            "123, Неверно введена дата",
            "qwerty, Неверно введена дата",
            "!@#$%^&*, Неверно введена дата"})
    public void testInputDateNegative(String value, String message) {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        actionsCardDelivery.setDate(value == null ? "" : value);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputDate.shouldHave(exactText(message));
    }

    @Test
    public void testInputDatePositive() {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        actionsCardDelivery.setDate(date);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputDate.shouldHave(exactText("Выберите дату встречи с представителем банка"));
    }

    @Test
    public void testInputDateInvalidDate() {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        actionsCardDelivery.setDate(date);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputDate.shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @ParameterizedTest
    @CsvSource(value = {"; Поле обязательно для заполнения",
            "123; Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
            "!@#$%^&*(); Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
            "dasdasdsad; Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
            "Тест-тес; Укажите точно как в паспорте",
            "Тест тес; Укажите точно как в паспорте",
            "Тест тес-тем; Укажите точно как в паспорте"}, delimiter = ';')
    public void testInputName(String value, String message){
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        actionsCardDelivery.setName(value == null ? "" : value);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputName.shouldHave(exactText(message));
    }

    @ParameterizedTest
    @CsvSource(value = {"; Поле обязательно для заполнения",
            "фывфывф; Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
            "12345678901; Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
            "!@#$%^&*(); Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
            "+12345678901; На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."
    }, delimiter = ';')
    public void testInputPhone(String value, String message){
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        actionsCardDelivery.setName("Тест");
        actionsCardDelivery.setPhone(value == null ? "" : value);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputPhone.shouldHave(exactText(message));
    }

    @Test
    public void testAgreementNegative(){
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        actionsCardDelivery.setName("Тест");
        actionsCardDelivery.setPhone("+12345678901");
        actionsCardDelivery.clickButtonSubmit();
        assertTrue(actionsCardDelivery.fieldAgreement.getAttribute("class").contains("input_invalid"));
    }

    @Test void testCardDeliverySend() {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity("Москва");
        actionsCardDelivery.setName("Тест");
        actionsCardDelivery.setPhone("+12345678901");
        actionsCardDelivery.clickAgreement();
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.blockSucsess.waitUntil(visible, 15000);
    }
}


