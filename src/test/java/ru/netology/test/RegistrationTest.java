package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Registration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.test.Generator.*;


public class RegistrationTest {

    @Test
    void SubmitActiveUser() {
        open("http://localhost:9999");
        Registration activeUser = getActiveUser();
        $("[name=login]").setValue(activeUser.getLogin());
        $("[name=password]").setValue(activeUser.getPassword());
        $(".button__text").click();
        $(".App_appContainer__3jRx1 h2.heading").waitUntil(exist, 5000);
        $(".App_appContainer__3jRx1 h2.heading").shouldHave(matchesText("Личный кабинет"));
    }

    @Test
    void submitBlockedUser() {
        open("http://localhost:9999");
        Registration blockedUser = getBlockedUser();
        $("[name=login]").setValue(blockedUser.getLogin());
        $("[name=password]").setValue(blockedUser.getPassword());
        $(".button__text").click();
        $(".notification_status_error").waitUntil(visible, 5000);
        $(".notification_visible[data-test-id=error-notification]").shouldHave(Condition.matchesText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void submitFalsePassword() {
        open("http://localhost:9999");
        Registration userWithFalsePassword = getUserWithFalsePassword();
        $("[name=login]").setValue(userWithFalsePassword.getLogin());
        $("[name=password]").setValue(userWithFalsePassword.getPassword());
        $(".button__text").click();
        $(".notification_status_error").waitUntil(visible, 5000);
        $(".notification_visible[data-test-id=error-notification]").shouldHave(Condition.matchesText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void submitWithFalseLogin() {
        open("http://localhost:9999");
        Registration userWithFalseLogin = getUserWithFalseLogin();
        $("[name=login]").setValue(userWithFalseLogin.getLogin());
        $("[name=password]").setValue(userWithFalseLogin.getPassword());
        $(".button__text").click();
        $(".notification_status_error").waitUntil(visible, 5000);
        $(".notification_visible[data-test-id=error-notification]").shouldHave(Condition.matchesText("Ошибка! Неверно указан логин или пароль"));
    }

}
