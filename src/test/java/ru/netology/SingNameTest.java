package ru.netology;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class SingNameTest {
    @Test
    void shouldCardDeliveryWithName() {
        String DateToEnter = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(DateToEnter);
        $("[name='name']").setValue(DataGenerator.Registration.generateData("ru").getName());
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".form-field .button_view_extra").click();
        $(".notification_status_ok .notification__content").waitUntil(visible, 15000).shouldHave(text(DateToEnter));
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        DateToEnter = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input.input__control").setValue(DateToEnter);
        $(".form-field .button_view_extra").click();
        $(".notification_status_error .notification__content").waitUntil(visible, 15000).shouldHave(text("Перепланировать"));
        $(".notification_status_error .button_view_extra").click();
        $(".notification_status_ok .notification__content").waitUntil(visible, 15000).shouldHave(text(DateToEnter));
    }
}
