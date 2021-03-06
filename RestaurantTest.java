import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    //REFACTOR ALL THE REPEATED LINES OF CODE
    Restaurant restaurant;
    public void addRestaurantDetails() {

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        addRestaurantDetails();
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        LocalTime currentTime = LocalTime.parse("13:00:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(currentTime);
        boolean status = restaurant.isRestaurantOpen();
        assertTrue(status);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        addRestaurantDetails();
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        LocalTime currentTime = LocalTime.parse("07:00:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(currentTime);
        boolean status = restaurant.isRestaurantOpen();
        assertFalse(status);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        //LocalTime openingTime = LocalTime.parse("10:30:00");
       // LocalTime closingTime = LocalTime.parse("22:00:00");
       // restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
      //  restaurant.addToMenu("Sweet corn soup",119);
      //  restaurant.addToMenu("Vegetable lasagne", 269);
        addRestaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
      //  LocalTime openingTime = LocalTime.parse("10:30:00");
       // LocalTime closingTime = LocalTime.parse("22:00:00");
       // restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
       // restaurant.addToMenu("Sweet corn soup",119);
      //  restaurant.addToMenu("Vegetable lasagne", 269);
        addRestaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
       // LocalTime openingTime = LocalTime.parse("10:30:00");
      //  LocalTime closingTime = LocalTime.parse("22:00:00");
      //  restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
      //  restaurant.addToMenu("Sweet corn soup",119);
      //  restaurant.addToMenu("Vegetable lasagne", 269);
        addRestaurantDetails();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<ORDERVALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void selecting_item_price_calculation_should_be_correct() {

        addRestaurantDetails();
        List<String>selectedItems = Arrays.asList("Sweet corn soup","Vegetable lasagne");
        assertEquals(388,restaurant.getItemSelectAmount(selectedItems));
    }


    //<<<<<<<<<<<<<<<<<<<<<<<ORDERVALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}