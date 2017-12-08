package com.tangshengbo.tutorial.java8.collect;

import com.tangshengbo.tutorial.java8.stream.Dish;

import java.util.*;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by Tangshengbo on 2017/12/8.
 */
public class GroupingTransactions {

    public static void main(String... args) {
        groupImperatively();
        System.out.println("===================================================");
        groupFunctionally();
        System.out.println("===================================================");
        joinFunctionally();
        System.out.println("===================================================");
        partitionedFunctionally();
    }

    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions
                .stream()
                .collect(groupingBy(Transaction::getCurrency));
        Map<Dish.Type, List<Dish>> dishesByType = menu
                .stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);
        System.out.println("===================================================");
        System.out.println(transactionsByCurrencies);
    }

    private static void joinFunctionally() {
        String shortMenu = menu.stream().map(Dish::getName).collect(joining("-"));
        System.out.println(shortMenu);
    }

    private static void partitionedFunctionally() {
        Map<Boolean, List<Dish>> partitionedMenu = menu
                .stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);
        System.out.println("===================================================");
        List<Dish> dishList = partitionedMenu.get(true);
        System.out.println(dishList);
        System.out.println("===================================================");
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu
                .stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);
        System.out.println("===================================================");
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu
                .stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);
    }

    public static class Transaction {

        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }


    private static List<Transaction> transactions = Arrays.asList(new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0));

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
