@db
Feature:

    #  Find out the customer id, name, surname, the city and the
    # country of the customers who make
    # the most purchases and how much money they have spent ?

  Scenario: Verify the homework first question's answer with DB
    Given "148" "Eleanor" "Hunt" "Saint-Denis" "Runion" "211.55" should match with the result

@q02
  Scenario Outline: Verify the homework second question's answer with DB
  Given "<month>" "<num_of_sales_of_staff_1>" "<num_of_sales_of_staff_2>" should match with the result

    Examples:

      | month    | num_of_sales_of_staff_1 | num_of_sales_of_staff_2 |
      | May      | 95                      | 87                      |
      | March    | 2817                    | 2827                    |
      | April    | 3364                    | 3390                    |
      | February | 1016                    | 1000                    |