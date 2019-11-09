package helper;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

public class BaseHelper {

    public static void clearInput(SelenideElement element){
        int sizeText = element.getValue().length();
        for (int i = 0; i < sizeText; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
}