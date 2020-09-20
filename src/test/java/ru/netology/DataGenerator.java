package ru.netology;

import com.github.javafaker.Faker;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {}
    public static class Registration {
        private Registration() {}
        public static RegistrationByCardInfo generateData(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationByCardInfo(
                    faker.name().lastName()+" "+faker.name().firstName(),
                    getRandomCity(),
                    faker.phoneNumber().cellPhone()
            );
        }
    }
    private static String getRandomCity(){
        List<String> cities = Arrays.asList("Благовещенск", "Владивосток", "Абакан", "Саратов", "Великий Новгород", "Воронеж", "Нарьян-Мар", "Омск");
        Random rnd = new Random();
        return cities.get(rnd.nextInt(cities.size()-1));
    }
}
