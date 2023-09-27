import java.util.*;

interface ShopItem {
  String getName();
  double getPrice();
  double getWeight();
}

class FoodItems implements ShopItem {
  private String name;
  private double price;
  private double weight;
  public FoodItems(String name, double price, double weight) {
    this.name = name;
    this.price = price;
    this.weight = weight;
  }
  @Override
  public String getName() {
    return name;
  }
  @Override
  public double getPrice() {
    return price;
  }
  @Override
  public double getWeight() {
    return weight;
  }
}

class ShoppingCart {
  private FoodItems[] items;
  private int bagCapacity;
  public ShoppingCart(int N, int bagCapacity) {
    this.items = new FoodItems[N];
    this.bagCapacity = bagCapacity;
  }
  public void addItem(int index, FoodItems item) {
    items[index] = item;
  }
  public int computeMaxValue() {
    int[][] dp = new int[items.length + 1][bagCapacity + 1];
    for (int i = 1; i <= items.length; i++) {
      for (int w = 0; w <= bagCapacity; w++) {
        if (items[i - 1].getWeight() <= w) {
          dp[i][w] = Math.max(dp[i - 1][w], (int) items[i - 1].getPrice() + dp[i - 1][(int)(w - items[i - 1].getWeight())]);
        } else {
          dp[i][w] = dp[i - 1][w];
        }
      }
    }
    return dp[items.length][bagCapacity];
  }
}
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int bagCapacity = sc.nextInt();
    double[] prices = new double[N];
    double[] weights = new double[N];
    for (int i = 0; i < N; i++) {
      prices[i] = sc.nextDouble();
    }
    for (int i = 0; i < N; i++) {
      weights[i] = sc.nextDouble();
    }

    ShoppingCart cart = new ShoppingCart(N, bagCapacity);

    for (int i = 0; i < N; i++) {
      FoodItems item = new FoodItems("Item " + (i + 1), prices[i], weights[i]);
      cart.addItem(i, item);
    }
    int maxValue = cart.computeMaxValue();
    System.out.println(maxValue);

  }
}
