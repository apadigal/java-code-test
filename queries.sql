-- These queries used Oracle dialect
-- 1.Create a query to return the unique rows in a table
select distinct (column1)
  from table1;

-- 2.Write a command to insert values into a table
-- Eg1.
insert
  into table1(col1, col2, col3, col4, col5)
values (1, 'test', 'sld', 12.3, sysdate);
-- Eg2.
insert into table1(col1, col2, col3, col4, col5)
select col1, col2, col3, col4, sysdate
  from v_table1;


-- 3.Create a query that joins two tables together. Note, all rows from the first table must be in the result-set
-- (e.g. given customer and order tables, return all customers and any orders for each customer
select c.*, o.*
--       ,count(o.customer_id) OVER (PARTITION BY c.customer_id) AS total_orders
  from customer c
  left outer join order o
    on c.customer_id = o.customer_id;
