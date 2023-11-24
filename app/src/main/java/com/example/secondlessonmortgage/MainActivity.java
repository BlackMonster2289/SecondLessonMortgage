package com.example.secondlessonmortgage;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // зададние полей
    float astronomicalTelescopePrice = 14_000; // стоимости астрономического телескопа
    float scholarship = 2_500; // ежемесячная степендия
    float account = 1_000; // счёт пользователя
    float percentBank = 5; // годовая процентная ставка за накопительный счёт
    float[] monthlySavings = new float[12]; // создание массива ежемесячных откладываний на 1 год

    // создание дополнительных полей для вывода на экран полученных значений
    private TextView countOut; // поле вывода колечества месяцев накопления накопительного счёта
    private TextView manyMonthOut; // поле выписки по ежемесячным откладываниям

    // вывод на экран полученных значений
    @Override
    protected void onCreate(Bundle savedInstanceState) { // создание жизненного цикла активности
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // присваивание жизненному циклу активити представления activity_main

        // присваивание переменным активити элементов представления activity_main
        countOut = findViewById(R.id.countOut); // вывод информации количества месяцны накопления накопительного счёта
        manyMonthOut = findViewById(R.id.manyMonthOut); // вывод информации выписки по ежемесячным откладываниям

        // заполнение экрана
        // 1) вывод количествв месяцев откладываний накопительного счёт
        countOut.setText(countMonth(percentBank, scholarship, astronomicalTelescopePrice, account, monthlySavings) + "месяцев");
        // 2) подготовка выписка
        String monthlySavingsList = ""; // строка для записи выписки
        for (float list : monthlySavings) { // цикл заполнения строки выпиской
            if (list > 0) {
                monthlySavingsList += Float.toString(list) + ", ";
            } else {
                break;
            }
        }
        // 3) вывод выписки ежемесячных накоплений по накопительному счёту
        manyMonthOut.setText("Первоночальный взнос " + account + " монет, ежемесячные откладывания (монет): " + monthlySavingsList);
    }

    // метод подсчёта времени накопления (годовой процент, степендия, свободные траты, стоимость астрономического телескопа, накопления, массив для откладываний на счёт)
    public int countMonth(float percentBankYear, float scholarship, float astronomicalTelescope, float accumulation, float[] arraySavings) {

        float percentBankMonth = percentBankYear / 5; // подсчёт ежемесячного процента банка за накопительный счёт
        float savingsExpenses = scholarship / 100; // подсчёт ежемесячных откладываний на накопительный счёт
        float total = astronomicalTelescope - accumulation; // подсчёт стоимости астрономического телескопа с учётом первоначального взноса
        int count = 0; // счёт месяцев накопления накопительного счёта

        // алгоритм расчёта накопительного счёта
        while (total > 0) {
            count++; // добавления нового месяца взноса
            total = (total + (total * percentBankMonth) / 100) - savingsExpenses; // вычесления накоплений с учётом степендии и процента
            // заполнение массива ежемесячными откладываниями за накопительный счёт
            if (total > savingsExpenses) { // если сумма накопления больше ежемесячной степендии, то
                arraySavings[count - 1] = savingsExpenses; // в массив добавляется целое откладавыние
            } else { // иначе
                arraySavings[count - 1] = total; // в массив добавляется откладывание равное остатку накоплению
            }
        }

        return count;
    }
}