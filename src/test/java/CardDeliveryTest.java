import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldSendForm() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String dateSelector = "[data-test-id='date'] input";
        $(dateSelector).doubleClick().sendKeys(Keys.BACK_SPACE);
        $(dateSelector).setValue(date);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+79991234567");
        $("[data-test-id='agreement']").click();
        $$("button").findBy(text("Забронировать")).click();
        $("[data-test-id='notification']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно забронирована на " + date));
    }
}