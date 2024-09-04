package hr.tis.academy.service.impl;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.IsWeekendResponse;
import hr.tis.academy.service.HelloService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public DaysOfWeekResponse daysOfWeek() {
        DayOfWeek today = LocalDateTime.now().getDayOfWeek();

        boolean isweekend = today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;

        List<DayOfWeek> oddDays = Arrays.stream(DayOfWeek.values()).filter(day -> day.ordinal() % 2 == 0).toList();
        List<DayOfWeek> evenDays = Arrays.stream(DayOfWeek.values()).filter(day -> day.ordinal() % 2 != 0).toList();
        return new DaysOfWeekResponse(today, isweekend, oddDays, evenDays);
    }
    @Override
    public String greet(List<String> namesList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name: namesList){
            stringBuilder.append("Dobrodošao ");
            stringBuilder.append(name);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public IsWeekendResponse isWeekend(DayOfWeek day) {
        return new IsWeekendResponse(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
    }

    @Override
    public byte[] createImage(String text, int width, int height, int red, int green, int blue) {
        try {

            if (!validRGB(red,green,blue)) throw new IllegalArgumentException("Invalid RGB");

            if(!validDimensions(width, height)) throw new IllegalArgumentException("Invalid dimensions");

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();

            graphics.setColor(new Color(red, green, blue));
            graphics.fillRect(0, 0, width, height);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            FontMetrics fontMetrics = graphics.getFontMetrics();
            int x = (width - fontMetrics.stringWidth(text)) / 2;
            int y = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent(); // da bude centriran

            graphics.drawString(text, x, y);
            graphics.dispose();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image,"png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error");
        }
    }

    private boolean validRGB(int red, int green, int blue) {
        return (red >= 0 && red <= 255) && (green >= 0 && green <= 255) && (blue >= 0 && blue <= 255);
    }

    private boolean validDimensions(int width, int height) {
        return width > 0 && height > 0;
    }
}
