package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RecheckAppCardDeliveryTest {
    @Test
    void shouldCardDeliveryWithAllData() {
        String DateToEnter = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(DateToEnter);
        $("[name='name']").setValue(DataGenerator.Registration.generateData("ru").getName());
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".button_view_extra").click();
        $("[data-test-id=success-notification] .notification__content")
                .waitUntil(visible, 15000)
                .shouldHave(text(DateToEnter));
    }

    @Test
    void shouldCardDeliveryWithoutCity(){
        open("http://localhost:9999");
        $("[name='name']").setValue(DataGenerator.Registration.generateData("ru").getName());
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".button_view_extra").click();
        $("[data-test-id=city] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryWithoutName(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".button_view_extra").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryWithEngName(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[name='name']").setValue("Cavendish Henry");
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".button_view_extra").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldCardDeliveryWithRusName(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[name='name']").setValue("Семёнов Роман");
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".button_view_extra").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldCardDeliveryWithoutPhone(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[name='name']").setValue(DataGenerator.Registration.generateData("ru").getName());
        $("[class=checkbox__box]").click();
        $(".button_view_extra").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryWithoutCheckbox(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[name='name']").setValue(DataGenerator.Registration.generateData("ru").getName());
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $(".button_view_extra").click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));
    }
}
