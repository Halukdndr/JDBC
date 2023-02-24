package stepDefs;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class HomeworkStepDef {



    @Given("{string} {string} {string} {string} {string} {string} should match with the result")
    public void should_match_with_the_result1(String customer_id, String first_name,String last_name, String city, String country, String amount) {

        int count = 0;
        String query = "SELECT cu.customer_id, cu.first_name, cu.last_name, ci.city, co.country, SUM(p.amount) AS amount\n" +
                "FROM payment p\n" +
                "JOIN customer cu ON cu.customer_id = p.customer_id\n" +
                "JOIN address a ON cu.address_id = a.address_id\n" +
                "JOIN city ci ON ci.city_id = a.city_id\n" +
                "JOIN country co ON ci.country_id = co.country_id\n" +
                "WHERE cu.customer_id IN (\n" +
                "  SELECT customer_id\n" +
                "  FROM payment\n" +
                "  GROUP BY customer_id\n" +
                "  HAVING SUM(amount) = (\n" +
                "    SELECT SUM(amount)\n" +
                "    FROM payment\n" +
                "    GROUP BY customer_id\n" +
                "    ORDER BY SUM(amount) DESC\n" +
                "    LIMIT 1\n" +
                "  )\n" +
                ")\n" +
                "GROUP BY cu.customer_id,ci.city, co.country\n" +
                "ORDER BY SUM(p.amount) DESC;";

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(query);
        Map<String, Object> rowMap = queryResultMap.get(count++);
        Assert.assertEquals(rowMap.get("customer_id") + "", customer_id);
        Assert.assertEquals(rowMap.get("first_name"), first_name);
        Assert.assertEquals(rowMap.get("last_name"), last_name);
        Assert.assertEquals(rowMap.get("city"), city);
        Assert.assertEquals(rowMap.get("country"), country);
        Assert.assertEquals(rowMap.get("amount") + "", amount);
    }


    @Given("{string} {string} {string} should match with the result")
    public void shouldMatchWithTheResult(String month, String num_of_sales_of_staff_1, String num_of_sales_of_staff_2) {
        int count = 0;
        String query = "SELECT \n" +
                "    TO_CHAR(payment_date, 'Month') AS month, \n" +
                "    COUNT(CASE WHEN staff_id = 1 THEN amount END) AS num_of_sales_of_staff_1, \n" +
                "    COUNT(CASE WHEN staff_id = 2 THEN amount END) AS num_of_sales_of_staff_2\n" +
                "FROM payment\n" +
                "GROUP BY TO_CHAR(payment_date, 'Month')";

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(query);
        Map<String, Object> rowMap = queryResultMap.get(count++);
        Assert.assertEquals(rowMap.get("month"), month);
        Assert.assertEquals(rowMap.get("num_of_sales_of_staff_1") + "", num_of_sales_of_staff_1);
        Assert.assertEquals(rowMap.get("num_of_sales_of_staff_2") + "", num_of_sales_of_staff_2);

    }
}
