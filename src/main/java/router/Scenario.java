package router;

import org.openqa.selenium.By;

public class Scenario {

    private static Scenario instance = null;
    private WebBrowser webBrowser;

    private Scenario() {

        webBrowser = WebBrowser.getInstance();
    }

    public void aantalGbExtra(int aantal) {

        inloggen();
        sendSMS(aantal);
        schoonSMS();
        uitloggen();
    }


    private void inloggen() {

        webBrowser.getChromeDriver().get("http://192.168.10.1/html/home.html");
        wacht(2000);
        webBrowser.getChromeDriver().findElement(By.id("logout_span")).click();
        wacht(5000);
        webBrowser.getChromeDriver().findElement(By.id("username")).clear();
        webBrowser.getChromeDriver().findElement(By.id("username")).sendKeys("admin");
        webBrowser.getChromeDriver().findElement(By.id("password")).clear();
        String wachtwoord = System.getenv("ROUTERWACHTWOORD");
        webBrowser.getChromeDriver().findElement(By.id("password")).sendKeys(wachtwoord);
        webBrowser.getChromeDriver().findElement(By.id("pop_login")).click();
        wacht(2000);

    }

    private void uitloggen() {

        wacht(2000);
        webBrowser.getChromeDriver().findElement(By.id("logout_span")).click();
        wacht(5000);
        webBrowser.getChromeDriver().findElement(By.id("pop_confirm")).click();

    }

    private void sendSMS(int aantal) {

        for (int i = 1; i <= aantal; i++) {

            webBrowser.getChromeDriver().findElement(By.xpath("//a[@id='sms']/span")).click();
            wacht(5000);
            webBrowser.getChromeDriver().findElement(By.id("message")).click();
            wacht(5000);
            webBrowser.getChromeDriver().findElement(By.id("recipients_number")).clear();
            webBrowser.getChromeDriver().findElement(By.id("recipients_number")).sendKeys("1280");
            webBrowser.getChromeDriver().findElement(By.id("message_content")).clear();
            webBrowser.getChromeDriver().findElement(By.id("message_content")).sendKeys("1gb extra");
            webBrowser.getChromeDriver().findElement(By.id("pop_send")).click();
            wacht(15000);
            webBrowser.getChromeDriver().findElement(By.id("pop_OK")).click();
            wacht(2000);

        }
    }

    private void schoonSMS() {

        webBrowser.getChromeDriver().findElement(By.xpath("//a[@id='sms']/span")).click();

        /*inbox verwijderen*/
        wacht(5000);
        webBrowser.getChromeDriver().findElement(By.id("label_inbox")).click();
        webBrowser.getChromeDriver().findElement(By.id("sms_check_all")).click();
        webBrowser.getChromeDriver().findElement(By.id("del_msg_btn")).click();
        wacht(1000);
        webBrowser.getChromeDriver().findElement(By.id("pop_confirm")).click();

        /*verzonden verwijderen*/
        wacht(5000);
        webBrowser.getChromeDriver().findElement(By.id("label_sent")).click();
        webBrowser.getChromeDriver().findElement(By.id("sms_check_all")).click();
        webBrowser.getChromeDriver().findElement(By.id("del_msg_btn")).click();
        wacht(1000);
        webBrowser.getChromeDriver().findElement(By.id("pop_confirm")).click();

    }

    private void wacht(int time) {

        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }

    public static Scenario getInstance() {
        if (instance == null) {
            instance = new Scenario();
        }
        return instance;
    }
}
