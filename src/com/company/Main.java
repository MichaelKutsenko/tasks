package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(countLuckyTickets(0, 1000));
    }



    /**
     * @param start первый номерок билета
     * @param end последний номерок билета
     * @return возвращает количество билетов сумма первых трех цыфр которых равняется сумме последних трех
     */
    public static int countLuckyTickets(int start, int end) {
        int numberQuantity = 6; //количество номеро в билете

        int counter = 0; //счетчик счастливых билетов
        for (int ticketNumber = start; ticketNumber <= end; ticketNumber++) {
            //каждый элемент массива - порядковый номер билета. При рассмотре нового билета - массив обнуляется
            int[] numberInTicket = new int[numberQuantity];

            //временное число, отображающее ту часть номера билета, которая в данный момент рассматривается (вначеле - все 6 цыфр)
            //потом первая цыфра отбрасывается - остается пять и так до последней
            int currentNumber = ticketNumber;


            for (int step = 100_000, j = 0; j < 5; step = step/10, j++) {
                //ПОДСКАЗКА:
                //6 цыфр - это 000_000 - 999_999 т.е. если билет содержит 6 цыфр, то первую цыфру можно определить разделив
                //номер билета на 100_000 и округлив цыфру к меньшему. если получим 0 - значит в билете меньше 6 цыфр и
                // мы принимает эту цыфру за "0".
                // Чтобы получить вторую цыфру нужно от номера всего билета отбросить предыдущую цыфру,
                // если такая присуствует. Для этого используем "деление с остатком". Делим на шаг в 10 раз меньше (10_000 - для второй цыфры билета) и тд...
                // Пятая цыфра(000099-000010) - разделив на 10
                numberInTicket[j] = currentNumber/step;
                if (numberInTicket[j] != 0) {
                    currentNumber = currentNumber % step;
                }
            }
            //в итоге у нас остается в любом случае одно число, которое есть последней цыфрой билета
            // (или единственной, если билет имел порядковый номер меньше 10). Опеределяем это 6-е число
            numberInTicket[numberQuantity-1] = currentNumber;

            int sumOfFirst3Numbers = numberInTicket[0] + numberInTicket[1] + numberInTicket[2];
            int sumOfLast3Numbers = numberInTicket[3] + numberInTicket[4] + numberInTicket[5];
            if (sumOfFirst3Numbers == sumOfLast3Numbers) {
                counter++;
            }
        }
        return counter;
    }
}
